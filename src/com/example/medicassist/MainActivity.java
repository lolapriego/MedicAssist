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

public class MainActivity extends Activity {
	Spinner spin;
    String spin_val;
    String[] gender = { "Male", "Female" };
    LoginDataBaseAdapter loginDataBaseAdapter;
    public static final String PREFS_NAME = "LoginPrefs";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        if (settings.getString("registered", "").toString().equals("registered")) {
                Intent intent = new Intent(this, password.class);
                startActivity(intent);
                finish();
        }
		
		
		
		
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
		 if (savedInstanceState != null){
		    String p1 = savedInstanceState.getString("t1");
		    
		    inputA.setText(p1);
		    
		    String p2 = savedInstanceState.getString("t2");
		   
		    inputB.setText(p2);
		    
		    String p3 = savedInstanceState.getString("t3");
		    
		    inputC.setText(p3);
		    
		    String p4 = savedInstanceState.getString("t4");
		    
		    inputD.setText(p4);
		    
		    String p5 = savedInstanceState.getString("t5");
		    
		    inputE.setText(p5);
		    
		    String p6 = savedInstanceState.getString("t6");
		    
		    inputF.setText(p6);
		    
		    String p7 = savedInstanceState.getString("t7");
		    
		    inputG.setText(p7);  
		    
		    String p8 = savedInstanceState.getString("t8");
		    
		    inputH.setText(p8); 
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
					 SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                     SharedPreferences.Editor editor = settings.edit();
                     editor.putString("registered", "registered");
                     editor.commit();
					 loginDataBaseAdapter.insertEntry(value1,value2,value3,value4,value9,value5,value6,value7);
					   Toast.makeText(getApplicationContext(), "Account Successfully Created ", Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(context, password.class);
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

	 public void onSaveInstanceState(Bundle savedInstanceState) {
		 EditText inputA = (EditText) findViewById(R.id.name);
		      EditText inputB = (EditText) findViewById(R.id.phoneno);
		      EditText inputC = (EditText) findViewById(R.id.address);
		       EditText inputD = (EditText) findViewById(R.id.age);
		       EditText inputE = (EditText) findViewById(R.id.contacts);
		       EditText inputF = (EditText) findViewById(R.id.username);
		       EditText inputG = (EditText) findViewById(R.id.password);
		        EditText inputH = (EditText) findViewById(R.id.password1);
			
			String value1= inputA.getText().toString();
			String value2= inputB.getText().toString();
			String value3= inputC.getText().toString();
			String value4= inputD.getText().toString();
			String value5= inputE.getText().toString();
			String value6=inputF.getText().toString();
			String value7= inputG.getText().toString();
			String value8= inputH.getText().toString();
			
			
			savedInstanceState.putString("t1", value1);
			savedInstanceState.putString("t2", value2);
			savedInstanceState.putString("t3", value3);
			savedInstanceState.putString("t4", value4);
			savedInstanceState.putString("t5", value5);
			savedInstanceState.putString("t6", value6);
			savedInstanceState.putString("t7", value7);
			savedInstanceState.putString("t8", value8);
			  super.onSaveInstanceState(savedInstanceState);
	 } 
	
	 protected void onDestroy() {
			// TODO Auto-generated method stub
			super.onDestroy();
			
			loginDataBaseAdapter.close();
		}
}
