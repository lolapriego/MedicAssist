package com.example.medicassist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class ManageMainActivity extends Activity 
{
	
	public DBmgr dataArchive;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_manage_main);
		
		dataArchive = new DBmgr(this);
		dataArchive.open();
		
	}

	
/*	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.manage_main, menu);
		return true;
	}  */
	
	public void startDocMain(View v)
	{
		//Start Doctors main screen
		Intent intent = new Intent(this, Schedule.class);
		startActivity(intent);
	}
	
	public void startDevMain(View v)
	{
		Intent intent = new Intent(this, MainActivityBluetooth.class);
		startActivity(intent);
		
	}
	
	public void startReqMain(View v)
	{
		//Start Requests main screen
		Intent intent = new Intent(this, MainActivityBluetooth.class);
		startActivity(intent);
	}
	
	public void startAccSetsMain(View v)
	{
		Intent intent = new Intent(this, updateAccountSettings.class);
		startActivity(intent);
		
	}
	
	public void startTest(View v)
	{
		//Start Test screen
		Intent intent = new Intent(this, TestMainActivity.class);
		startActivity(intent);
	}
}
