package com.example.medicassist;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;

public class DoctorsConnectedActivity extends ListActivity 
{
	
	private DBmgr dataArchive;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_doctors_connected);
		
		String Doctor = "No Connections";
		DocData DoctorRec;
		
		List<String> allDocDetails =  new ArrayList<String>();
		
		//Intent InData = getIntent();
				
		dataArchive = new DBmgr(this);
		dataArchive.open();
		
		List<DocData> allDocs = dataArchive.getAllDoctors();
		Iterator<DocData> docListIterator = allDocs.iterator();
		
		allDocDetails.clear();
		
		while(docListIterator.hasNext())
		{
			DoctorRec = docListIterator.next();
			if(DoctorRec.DocStatus.equals("Connected"))
			{
				Doctor = DoctorRec.DocName + "\n" + DoctorRec.DocDetails;
			}
			allDocDetails.add(Doctor);
		}
		
		if(allDocDetails.size() == 0)
				allDocDetails.add("No Connections");
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, allDocDetails);
		setListAdapter(adapter);
	}

	/*	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.doctors_connected, menu);
		return true;
	}
*/
}
