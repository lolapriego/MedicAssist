package com.example.medicassist;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class addappointment extends Activity {

	NewAppDataBaseAdapter nadba;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addappointment);
		nadba=new NewAppDataBaseAdapter(this);
		nadba=nadba.open();
		
		final Context context = this;
		Button addapp= (Button) findViewById(R.id.addapp);
		
		Bundle extras = getIntent().getExtras();
		String abc = extras.getString("doc");
		
		TextView t1=(TextView)findViewById(R.id.docname);
		t1.setText(abc);
             addapp.setOnClickListener(new Button.OnClickListener() {
            
			
			public void onClick(View v) {
				TimePicker time = (TimePicker) findViewById(R.id.time);
				
				
			//	ViewGroup v1 = (ViewGroup) time.getChildAt(0);
			//	ViewGroup numberPicker1 = (ViewGroup) v1.getChildAt(0);
			//	ViewGroup numberPicker2 = (ViewGroup) v1.getChildAt(1);
			//	String hours = ((EditText) numberPicker1.getChildAt(1)).getText().toString();
			//	String mins = ((EditText) numberPicker2.getChildAt(1)).getText().toString();
//
			//	String selectedTime = hours+":"+mins;
				
				
				
				time.clearFocus();
				// re-read the values, in my case i put them in a Time object.
				Integer hour   = time.getCurrentHour();
				Integer minute = time.getCurrentMinute();
				
				
				
				
				String hour1 = checkDigit(hour);
				String min1 = checkDigit(minute);
				
				
				
				
				
				
				
				
				
				
				DatePicker date = (DatePicker) findViewById(R.id.date);
				 int   day  = date.getDayOfMonth();
		           int   month= date.getMonth() + 1;
		           int   year = date.getYear();
			       EditText notes = (EditText) findViewById(R.id.notes);
			      String month1=checkDigit(month);
			      String day1=checkDigit(day);
			       
			      
			      
			      
			       String value1=    hour1+":"+min1  ;                                // time.getContext().toString();
					String value2=month1+"/"+day1+"/"+year;
					String value3= notes.getText().toString();
			       
					if(value3.isEmpty())
					{
						Toast.makeText(getApplicationContext(), 
			                    "Enter Notes or Symptoms", Toast.LENGTH_LONG).show();
					}
					else
					{
						
						Bundle extras = getIntent().getExtras();
						String abc = extras.getString("doc");
						
						
						nadba.insertEntry(abc,value1,value2,value3);	
					
				Toast.makeText(getApplicationContext(), "REQUEST SENT ", Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(context, Appointments.class);
                startActivity(intent);   
                finish();
					}
			}

		});
		
		
	}

	 public String checkDigit(int number)
	    {
	        return number<=9?"0"+number:String.valueOf(number);
	    }
	

}