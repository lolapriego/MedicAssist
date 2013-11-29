package com.example.medicassist;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class test extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test);

		ArrayList image_details = getListData();
		final ListView lv1 = (ListView) findViewById(R.id.custom_list);
		lv1.setAdapter(new CustomListAdapter(this, image_details));
		lv1.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> a, View v, int position, long id) {
				Object o = lv1.getItemAtPosition(position);
				Item Data = (Item) o;
				Toast.makeText(test.this, "Selected :" + " " + Data, Toast.LENGTH_LONG).show();
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
		Data.setDoctorName("Rajiv Chandan");
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