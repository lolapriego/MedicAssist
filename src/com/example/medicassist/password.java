package com.example.medicassist;




import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class password extends Activity {

	CheckBox cb;
	EditText editText;
	Button button;
	Button btnSignIn,btnSignUp;
	LoginDataBaseAdapter loginDataBaseAdapter;
	 public static final String PREFS_NAME = "LoginPrefs";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.password);
		
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        if (settings.getString("logged", "").toString().equals("logged")) {
                Intent intent = new Intent(this, MainScreen.class);
                startActivity(intent);
                finish();
        }
		
		
		loginDataBaseAdapter=new LoginDataBaseAdapter(this);
		loginDataBaseAdapter=loginDataBaseAdapter.open();
		cb = (CheckBox) findViewById(R.id.always);
		editText = (EditText) findViewById(R.id.pass);
		button = (Button) findViewById(R.id.login);
		 TextView txtName = (TextView) findViewById(R.id.name);
		String namegiven=loginDataBaseAdapter.getSinlgeEntry1();
		txtName.setText("Welcome"+" " +namegiven);

			button.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				
				
				String password=editText.getText().toString();
				
				
				String storedPassword=loginDataBaseAdapter.getSinlgeEntry(password);
			
				if(password.equals(storedPassword))
				{
					Toast.makeText(getApplicationContext(), "Congrats: Login Successfull", Toast.LENGTH_SHORT).show();
					cb = (CheckBox) findViewById(R.id.always);
					if(cb.isChecked()==true)
					{
						
						 SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
	                     SharedPreferences.Editor editor = settings.edit();
	                     editor.putString("logged", "logged");
	                     editor.commit();
					}
					
					
					 Intent intent = new Intent(password.this, MainScreen.class);
		                startActivity(intent);
		                
		                finish();
					
					
					
					
				}
				else
				{
					Toast.makeText(getApplicationContext(), "WRONG PASSWORD...!!", Toast.LENGTH_LONG).show();
				}
			}
		});
			Button reset = (Button) findViewById(R.id.reset);
			
			reset.setOnClickListener(new Button.OnClickListener() {
	            
				
				public void onClick(View v) {
			
					editText.setText("");  
				}

			});
	} 
	
	 protected void onDestroy() {
			
			super.onDestroy();
			
			loginDataBaseAdapter.close();
		}
}