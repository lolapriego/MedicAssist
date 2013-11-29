package com.example.medicassist;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;



public class MainScreen extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainscreen);
		
		final Context context = this;
		Button appointments = (Button) findViewById(R.id.appointments);
		
		appointments.setOnClickListener(new Button.OnClickListener() {
            
			
			public void onClick(View v) {
		
				Intent intent = new Intent(context, Appointments.class);
                startActivity(intent);   
			}

		});
		final Context context1 = this;
		Button manage = (Button) findViewById(R.id.manage);
		
		manage.setOnClickListener(new Button.OnClickListener() {
            
			
			public void onClick(View v) {
		
				Intent intent = new Intent(context1, ManageMainActivity.class);
                startActivity(intent);   
			}

		});
Button viewrecords = (Button) findViewById(R.id.viewrecords);
		
viewrecords.setOnClickListener(new Button.OnClickListener() {
            
			
			public void onClick(View v) {
				Intent intent = new Intent(context1, ViewRecordActivity.class);
				startActivity(intent);
				  
			}

		});
Button takelogs = (Button) findViewById(R.id.takelogs);

takelogs.setOnClickListener(new Button.OnClickListener() {
            
			
			public void onClick(View v) {
		
				  
			}

		});
Button remainders = (Button) findViewById(R.id.remainders);

remainders.setOnClickListener(new Button.OnClickListener() {
            
			
			public void onClick(View v) {
		
				  
			}

		});

		
		

		
		
		
		
		
		
		ArrayList image_details = getListData();
		final ListView lv1 = (ListView) findViewById(R.id.messages);
		lv1.setAdapter(new CustomListAdapter(this, image_details));
		lv1.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> a, View v, int position, long id) {
				Object o = lv1.getItemAtPosition(position);
				Item Data = (Item) o;
				Toast.makeText(MainScreen.this,  " " + Data, Toast.LENGTH_LONG).show();
			}

		});
		
		
	}

	private ArrayList getListData() {
		ArrayList results = new ArrayList();
		Item Data = new Item();
		Data.setHeadline("abc");
		Data.setDoctorName("abc");
		Data.setDate("May 26, 2013, 13:35");
		results.add(Data);

		Data = new Item();
		Data.setHeadline("abc");
		Data.setDoctorName("abc");
		Data.setDate("May 26, 2013, 13:35");
		results.add(Data);

		Data = new Item();
		Data.setHeadline("abc ");
		Data.setDoctorName("abc");
		Data.setDate("May 26, 2013, 13:35");
		results.add(Data);

		Data = new Item();
		Data.setHeadline("abc");
		Data.setDoctorName("abc");
		Data.setDate("May 26, 2013, 13:35");
		results.add(Data);

		Data = new Item();
		Data.setHeadline("abc");
		Data.setDoctorName("abc");
		Data.setDate("May 26, 2013, 13:35");
		results.add(Data);

		Data = new Item();
		Data.setHeadline("abc");
		Data.setDoctorName("abc");
		Data.setDate("May 26, 2013, 13:35");
		results.add(Data);

		return results;
	}
	

}