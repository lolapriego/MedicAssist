package com.example.medicassist;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class newdoc extends Activity {

	NewDocDataBaseAdapter nddba;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.newdoctor);
		nddba=new NewDocDataBaseAdapter(this);
		nddba=nddba.open();
		
final Context context = this;
		
		Button add = (Button) findViewById(R.id.add);
		
		
		
		
		add.setOnClickListener(new Button.OnClickListener() {
            
			
			public void onClick(View v) {
				 EditText name = (EditText) findViewById(R.id.doctorname);
			       EditText spe = (EditText) findViewById(R.id.Specialization);
			       EditText hospname = (EditText) findViewById(R.id.hospname);
			       EditText notes = (EditText) findViewById(R.id.notes);
		 
			       String value1= name.getText().toString();
					String value2= spe.getText().toString();
					String value3= hospname.getText().toString();
					String value4= notes.getText().toString();
					if(value1.isEmpty())
					{
						Toast.makeText(getApplicationContext(), 
			                    "NAME is not entered", Toast.LENGTH_LONG).show();
					}
					else if(value2.isEmpty())
					{
						Toast.makeText(getApplicationContext(), 
			                    "SPECIALIZATION is not entered", Toast.LENGTH_LONG).show();
					}
					else if(value3.isEmpty())
					{
						Toast.makeText(getApplicationContext(), 
			                    "HOSPITAL NAME is not entered", Toast.LENGTH_LONG).show();
					}
					else
					{
						 
						nddba.insertEntry(value1,value2,value3,value4);
						   Toast.makeText(getApplicationContext(), "New Doctor Added ", Toast.LENGTH_SHORT).show();
					Intent intent = new Intent(context, Schedule.class);
	                startActivity(intent);   
	                finish();
					}
				}

			});
}
	}