package com.example.medicassist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.R.string;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Schedule extends Activity {

	NewDocDataBaseAdapter nddba;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.schedule);
		nddba=new NewDocDataBaseAdapter(this);
		nddba=nddba.open();
		
		final Context context = this;
		Button newdoc= (Button) findViewById(R.id.newdoc);
		
		
             newdoc.setOnClickListener(new Button.OnClickListener() {
            
			
			public void onClick(View v) {
		
				Intent intent = new Intent(context, newdoc.class);
                startActivity(intent);
                finish();
			}

		});
		
             final ListView lv1 = (ListView) findViewById(R.id.doclist);
             
             List<String> namegiven=nddba.getAllLabels();
             
             lv1.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, namegiven));
             
          
             lv1.setOnItemClickListener(new OnItemClickListener() {
            	 
            	  public void onItemClick(AdapterView<?> parent, View view,
            	    int position, long id) {
            		  
            		  
            		        
            		          
            		  int posnum=position+1;   
        		      
            		  String docname=nddba.getDoctorName(posnum);
            		  
            		  
            		  
            		  
            		//  Toast.makeText(getApplicationContext(),"Click ListItem Number " + position + "" +parent+ "" +view+ "                         docname"  + docname, Toast.LENGTH_LONG).show();
            	    Intent i = new Intent(context, addappointment.class);
            	    i.putExtra("doc", docname);
            	    startActivity(i);
	                 
	                finish();
            	  }
            	});
	}

	
	 protected void onDestroy() {
			
			super.onDestroy();
			
			nddba.close();
		}
	

}