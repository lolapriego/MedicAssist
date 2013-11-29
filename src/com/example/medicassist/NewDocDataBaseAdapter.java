package com.example.medicassist;

import java.util.ArrayList; 
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class NewDocDataBaseAdapter {

	static final String DATABASE_NAME = "doctors.db";
	static final int DATABASE_VERSION = 1;
	public static final int NAME_COLUMN = 1;
	
	static final String DATABASE_CREATE1 = "create table "+"DOCTORS"+
	                             "( " + "ID"+" integer primary key autoincrement," + "   NAME text,specialization text,hospitalname text,notes text); ";
	
	public  SQLiteDatabase db1;
	
	private final Context context;
	
	private DataBaseHelper db1Helper;
	public  NewDocDataBaseAdapter(Context _context) 
	{
		context = _context;
		db1Helper = new DataBaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	public  NewDocDataBaseAdapter open() throws SQLException 
	{
		db1 = db1Helper.getWritableDatabase();
		db1 = db1Helper.getReadableDatabase();
		return this;
	}
	public void close() 
	{
		db1.close();
	}

	public  SQLiteDatabase getDatabaseInstance()
	{
		return db1;
	}

	public void insertEntry(String name,String spe,String hospname,String notes)
	{
       ContentValues newValues = new ContentValues();
		
       newValues.put("NAME", name);
       newValues.put("SPECIALIZATION", spe);
       newValues.put("HOSPITALNAME", hospname);
       newValues.put("NOTES", notes);
       
		
		
		db1.insert("DOCTORS", null, newValues);
		
	}
	
	 public List<String> getAllLabels(){
	        List<String> labels = new ArrayList<String>();
	         
	       String[]columns=new String[]{"NAME","specialization"};
	        Cursor c=db1.query("DOCTORS", columns, null, null, null, null, null);
	        
	        	int name=c.getColumnIndexOrThrow("NAME");
	        	int spe=c.getColumnIndex("specialization");
	        	 for(c.moveToFirst(); !c.isAfterLast();c.moveToNext()){
	        		 labels.add("DOCTOR NAME:  "+c.getString(name)+"                   SPECIALIZATION:  "+c.getString(spe));
	        	    }	
	        	
			return labels;	
	      
	   
	      
	         
	      
	        
	    }
	
	public String getDoctorName(int number)
	{
		Cursor cursor1=db1.query("DOCTORS", null, " ID=?", new String[]{String.valueOf(number)}, null, null, null);
        if(cursor1.getCount()<1) 
        {
        	cursor1.close();
        	return "NOT EXIST";
        }
	    cursor1.moveToFirst();
		String docname= cursor1.getString(cursor1.getColumnIndex("NAME"));
		cursor1.close();
		return docname;	
	}   
	
}
