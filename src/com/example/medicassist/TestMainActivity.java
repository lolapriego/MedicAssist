package com.example.medicassist;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class TestMainActivity extends Activity 
{
	
	public DBmgr dataArchive;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test_main);
		
		dataArchive = new DBmgr(this);
		dataArchive.open();
	}

	public void onClickAdd(View view)
	{
		DocData InData = new DocData();
		
		EditText InField = (EditText)findViewById(R.id.editText1);
		InData.DocName = InField.getText().toString();
		InField.setText(" ");
		
		InField = (EditText)findViewById(R.id.editText2);
		InData.DocStatus = InField.getText().toString();
		InField.setText(" ");
		
		InField = (EditText)findViewById(R.id.editText3);
		InData.DocSpecialization = InField.getText().toString();
		InField.setText(" ");
		
		InField = (EditText)findViewById(R.id.editText4);
		InData.DocDetails = InField.getText().toString();
		InField.setText(" ");
		
		InData.DocHospital = "Dummy Hospital";
		
		dataArchive.addDoctor(InData);
		
		InField.setText(" ");
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.see_records, menu);
		return true;
	}

}
