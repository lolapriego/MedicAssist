package com.example.medicassist;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class updateAccountSettings extends Activity {
	Spinner spin;
    String spin_val;
    String[] gender = { "Male", "Female" };
    LoginDataBaseAdapter loginDataBaseAdapter;
   
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		
		
		
		
		loginDataBaseAdapter=new LoginDataBaseAdapter(this);
		loginDataBaseAdapter=loginDataBaseAdapter.open();
		
		   EditText inputA = (EditText) findViewById(R.id.name);
	       EditText inputB = (EditText) findViewById(R.id.phoneno);
	       EditText inputC = (EditText) findViewById(R.id.address);
	       EditText inputD = (EditText) findViewById(R.id.age);
	       EditText inputE = (EditText) findViewById(R.id.contacts);
	       EditText inputF = (EditText) findViewById(R.id.username);
	       
	       EditText inputG = (EditText) findViewById(R.id.password);
	       EditText inputH = (EditText) findViewById(R.id.password1);
	       String[] data=loginDataBaseAdapter.getAllData();
	inputA.setText(data[0]);
	inputB.setText(data[1]);
	inputC.setText(data[2]);
	inputD.setText(data[3]);
	inputE.setText(data[5]);
	
	inputF.setText(data[6]);
	inputF.setEnabled(false);
	inputG.setText("");
	inputH.setText("");
	 Spinner s1 = (Spinner) findViewById(R.id.spinner1);
	 String check=data[4];
	 if (check.matches("MALE"))
	 {
	 s1.setSelection(1);
	 }
	 else if (check.matches("FEMALE"))
	 {
		 s1.setSelection(2);	 
	 }
	 else
	 {
		 s1.setSelection(3);
	 }
final Context context = this;
		
		Button firstbutton = (Button) findViewById(R.id.button1);
		
		
		
		
		firstbutton.setOnClickListener(new Button.OnClickListener() {
            
			
			public void onClick(View v) {
				 EditText inputA = (EditText) findViewById(R.id.name);
			       EditText inputB = (EditText) findViewById(R.id.phoneno);
			       EditText inputC = (EditText) findViewById(R.id.address);
			       EditText inputD = (EditText) findViewById(R.id.age);
			       EditText inputE = (EditText) findViewById(R.id.contacts);
			       EditText inputF = (EditText) findViewById(R.id.username);
			       EditText inputG = (EditText) findViewById(R.id.password);
			       EditText inputH = (EditText) findViewById(R.id.password1);
			       Spinner s1 = (Spinner) findViewById(R.id.spinner1);
				String value1= inputA.getText().toString();
				String value2= inputB.getText().toString();
				String value3= inputC.getText().toString();
				String value4= inputD.getText().toString();
				String value5= inputE.getText().toString();
				String value6= inputF.getText().toString();
				String value7= inputG.getText().toString();
				String value8= inputH.getText().toString();
				String value9= String.valueOf(s1.getSelectedItem());
				String value10 = "SELECT YOUR GENDER";
			//	String check= value1 + value2 + value3 + value4 + value5 + value6 + value7 + value8;
				if(value1.isEmpty())
				{
					Toast.makeText(getApplicationContext(), 
		                    "NAME is not entered", Toast.LENGTH_LONG).show();
				}
				else if(value2.isEmpty())
				{
					Toast.makeText(getApplicationContext(), 
		                    "PHONENO is not entered", Toast.LENGTH_LONG).show();
				}
				else if(value3.isEmpty())
				{
					Toast.makeText(getApplicationContext(), 
		                    "ADDRESS is not entered", Toast.LENGTH_LONG).show();
				}
				else if(value4.isEmpty())
				{
					Toast.makeText(getApplicationContext(), 
		                    "AGE is not entered", Toast.LENGTH_LONG).show();
				}
				else if(value9.matches(value10))
				{
					Toast.makeText(getApplicationContext(), 
		                    "GENDER not selected", Toast.LENGTH_LONG).show();
				}
				else if(value5.isEmpty())
				{
					Toast.makeText(getApplicationContext(), 
		                    "EMERGENCY CONTACT is not entered", Toast.LENGTH_LONG).show();
				}
				else if(value6.isEmpty())
				{
					Toast.makeText(getApplicationContext(), 
		                    "USERNAME is not entered", Toast.LENGTH_LONG).show();
				}
				else if(value7.isEmpty())
				{
					Toast.makeText(getApplicationContext(), 
		                    "PASSWORD is not entered", Toast.LENGTH_LONG).show();
				}
				else if(value8.isEmpty())
				{
					Toast.makeText(getApplicationContext(), 
		                    "REPEAT your password again", Toast.LENGTH_LONG).show();
				}
				else if(!value7.matches(value8))
				{
					Toast.makeText(getApplicationContext(), 
		                    "PASSWORD did not match", Toast.LENGTH_LONG).show();
				}
				else
				{
					 
					 loginDataBaseAdapter.insertEntry(value1,value2,value3,value4,value9,value5,value6,value7);
					   Toast.makeText(getApplicationContext(), "Account Successfully Updated ", Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(context, ManageMainActivity.class);
                startActivity(intent);   
                finish();
				}
			}

		});
		
		
		
		Button firstbutton1 = (Button) findViewById(R.id.button2);
		

		firstbutton1.setOnClickListener(new Button.OnClickListener() {

			
			public void onClick(View v) {

				Toast.makeText(getApplicationContext(), 
                        "RESET", Toast.LENGTH_SHORT).show();
				 EditText inputA = (EditText) findViewById(R.id.name);
			       EditText inputB = (EditText) findViewById(R.id.phoneno);
			       EditText inputC = (EditText) findViewById(R.id.address);
			       EditText inputD = (EditText) findViewById(R.id.age);
			       EditText inputE = (EditText) findViewById(R.id.contacts);
			       EditText inputF = (EditText) findViewById(R.id.username);
			       EditText inputG = (EditText) findViewById(R.id.password);
			       EditText inputH = (EditText) findViewById(R.id.password1);
			       Spinner s1 = (Spinner) findViewById(R.id.spinner1);
				 inputA.setText("");
				 inputB.setText("");
				 inputC.setText("");
				 inputD.setText("");
				 inputE.setText("");
				 inputF.setText("");
				 inputG.setText("");
				 inputH.setText("");
				// s1.setS("SELECT YOUR GENDER");
				 s1.setSelection(0);

			}

		});
		
		
		
	}

	
	 protected void onDestroy() {
			// TODO Auto-generated method stub
			super.onDestroy();
			
			loginDataBaseAdapter.close();
		}
}
