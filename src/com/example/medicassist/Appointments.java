package com.example.medicassist;

import java.util.List; 



import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;


public class Appointments extends Activity{

	NewAppDataBaseAdapter nadba;
	  


	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.appointments);
		nadba=new NewAppDataBaseAdapter(this);
		nadba=nadba.open();
		
		final Context context = this;
		Button appointments = (Button) findViewById(R.id.schedule);
		
		appointments.setOnClickListener(new Button.OnClickListener() {
            
			
			public void onClick(View v) {
		
				Intent intent = new Intent(context, Schedule.class);
                startActivity(intent);   
			}

		});
		
		final ListView lv2 = (ListView) findViewById(R.id.lv2);
        
        List<String> namegiven=nadba.getAllLabels();
        
        lv2.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, namegiven));
        
     
        lv2.setOnItemClickListener(new OnItemClickListener() {
       	 
       	  @SuppressWarnings("deprecation")
		public void onItemClick(AdapterView<?> parent, View view,
       	    int position, long id) {
       		  int pos=position;
       		 final  String poss=Integer.toString(pos);
       		  int send=pos+1;
       		  
       		  String[] get=nadba.getAppDetailspopup(send);
       		AlertDialog alertDialog = new AlertDialog.Builder(Appointments.this).create();
			alertDialog.setTitle("Appointment Details"+"                        "+get[0]);
			alertDialog.setMessage(get[1]);
			alertDialog.setButton("Detailed Description", new DialogInterface.OnClickListener() {
			   public void onClick(DialogInterface dialog, int which) {
				   Intent intent = new Intent(context, Detailed.class);
		       	      intent.putExtra("positionthatissent", poss);
		 	          startActivity(intent); 
			   }
			});
			// Set the Icon for the Dialog
			alertDialog.setIcon(R.drawable.icon);
			alertDialog.show(); 
       		  
       		  
       		  
       		  
       		  
       		  
       	/*      Intent intent = new Intent(context, Detailed.class);
       	      intent.putExtra("positionthatissent", poss);
 	          startActivity(intent); 
 	    
 	    */
 	    
 	    
       	  }
       	});  
        
}
	
	
	
	
	

	
	
	
	

protected void onDestroy() {
		
		super.onDestroy();
		
		nadba.close();
	}


	
	

}