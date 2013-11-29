package com.example.medicassist;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class NewAppDataBaseAdapter {

	static final String DATABASE_NAME = "appointments.db";
	static final int DATABASE_VERSION = 1;
	public static final int NAME_COLUMN = 1;
	
	static final String DATABASE_CREATE2 = "create table "+"APPOINTMENTS"+
	                             "( " + "ID"+" integer primary key autoincrement,"+ "  NAME text, time text, date text, notes text); ";
	
	public  SQLiteDatabase db2;
	
	private final Context context;
	
	private DataBaseHelper db2Helper;
	public NewAppDataBaseAdapter(Context _context) 
	{
		context = _context;
		db2Helper = new DataBaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	public NewAppDataBaseAdapter open() throws SQLException 
	{
		db2 = db2Helper.getWritableDatabase();
		db2 = db2Helper.getReadableDatabase();
		return this;
	}
	public void close() 
	{
		db2.close();
	}

	public  SQLiteDatabase getDatabaseInstance()
	{
		return db2;
	}

	public void insertEntry(String name,String time,String date,String notes)
	{
       ContentValues newValues = new ContentValues();
		
       newValues.put("NAME", name);
       newValues.put("TIME", time);
       newValues.put("DATE", date);
       newValues.put("NOTES", notes);
       
		
		
		db2.insert("APPOINTMENTS", null, newValues);
		
	}
	
	 public List<String> getAllLabels(){
	        List<String> labels = new ArrayList<String>();
	         
	       String[]columns=new String[]{"NAME","time","date"};
	        Cursor c=db2.query("APPOINTMENTS", columns, null, null, null, null, null);
	     //   int id=c.getColumnIndexOrThrow("ID");
	       // int id1=id+1;
	        	int name=c.getColumnIndexOrThrow("NAME");
	        	int time=c.getColumnIndexOrThrow("time");
	        	int date=c.getColumnIndexOrThrow("date");
	        	 for(c.moveToFirst(); !c.isAfterLast();c.moveToNext()){
	        		 labels.add("DoctorName: "+c.getString(name)+"                                                          Time: "+c.getString(time)+"              Date: "+c.getString(date));
	        	    }	
	        	 
	        	
			return labels;	
	      
	   
			
	         
	      
	        
	    }
	
	 public String[] getAppDetails(int number)
		{
		 // String ad;
		 
		 String[] all=new String[]{"NAME","time","date","notes"};
		// String all; 
			Cursor cursor11=db2.query("APPOINTMENTS", null, " ID=?", new String[]{String.valueOf(number)}, null, null, null);
	        if(cursor11.getCount()<1) 
	        {
	        	cursor11.close();
	      
	        	
	        }
		    cursor11.moveToFirst();
		 //   ad= cursor11.getString(cursor11.getColumnIndex("NAME"));
		 String name1= cursor11.getString(cursor11.getColumnIndex("NAME"));
		 String time1=cursor11.getString(cursor11.getColumnIndex("time"));
		 String date1=cursor11.getString(cursor11.getColumnIndex("date"));
		 String notes1=cursor11.getString(cursor11.getColumnIndex("notes")); 
			cursor11.close();
			String[] ad = new String[]{name1,time1,date1,notes1};
			return ad;	
		}   
	 public String[] getAppDetailspopup(int number)
		{
		 // String ad;
		 
		 String[] all1=new String[]{"NAME","time","date",};
		// String all; 
			Cursor cursor11=db2.query("APPOINTMENTS", all1, " ID=?", new String[]{String.valueOf(number)}, null, null, null);
	        if(cursor11.getCount()<1) 
	        {
	        	cursor11.close();
	      
	        	
	        }
		    cursor11.moveToFirst();
		 //   ad= cursor11.getString(cursor11.getColumnIndex("NAME"));
		    String name12=cursor11.getString(cursor11.getColumnIndex("NAME"));
		 String time12=cursor11.getString(cursor11.getColumnIndex("time"));
		 String date12=cursor11.getString(cursor11.getColumnIndex("date"));
		
			cursor11.close();
			String uppername=name12.toUpperCase();
			String[] ad1={uppername,"Time:"+time12+"                                                       Date:"+date12} ;
			return ad1;	
		}   
	
	
	
}
