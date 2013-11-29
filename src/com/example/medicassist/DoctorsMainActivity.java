package com.example.medicassist;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

@SuppressWarnings("deprecation")
public class DoctorsMainActivity extends TabActivity  {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_doctors_main);
		
		// "Current tab
		TabHost tabHost 		= getTabHost();
		TabSpec connectedTab 	= tabHost.newTabSpec("Current");
		
		// setting Title and Icon for the Current Tab
		connectedTab.setIndicator("Current", getResources().getDrawable(R.drawable.ic_launcher));
        Intent connectedTabIntent = new Intent(this, DoctorsConnectedActivity.class);
        connectedTab.setContent(connectedTabIntent);
        
        // "Current tab
     	TabSpec docSearchTab 	= tabHost.newTabSpec("Find");
     		
     	// setting Title and Icon for the Current Tab
     	docSearchTab.setIndicator("Find", getResources().getDrawable(R.drawable.ic_launcher));
        Intent docSearchTabIntent = new Intent(this, SearchDoctorsActivity.class);
        docSearchTab.setContent(docSearchTabIntent);
        
        // show tabs
        tabHost.addTab(connectedTab);
        tabHost.addTab(docSearchTab);
	}

/*	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.doctors_main, menu);
		return true;
	}  */

}
