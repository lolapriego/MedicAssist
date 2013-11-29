package com.example.medicassist;

import android.content.ContentValues; 
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class LoginDataBaseAdapter 
{
		static final String DATABASE_NAME = "register.db";
		static final int DATABASE_VERSION = 1;
		public static final int NAME_COLUMN = 1;
		
		static final String DATABASE_CREATE = "create table "+"LOGIN"+
		                             "( " + "NAME text,PHONENUMBER text,ADDRESS text,AGE text,GENDER text,EMERGENCYCONTACT text, USERNAME  text,PASSWORD text); ";
		
		public  SQLiteDatabase db;
		
		private final Context context;
		
		private DataBaseHelper dbHelper;
		public  LoginDataBaseAdapter(Context _context) 
		{
			context = _context;
			dbHelper = new DataBaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
		}
		public  LoginDataBaseAdapter open() throws SQLException 
		{
			db = dbHelper.getWritableDatabase();
			return this;
		}
		public void close() 
		{
			db.close();
		}

		public  SQLiteDatabase getDatabaseInstance()
		{
			return db;
		}

		public void insertEntry(String name,String phoneno,String address,String age,String gender,String emecontact,String username,String password)
		{
	       ContentValues newValues = new ContentValues();
			// Assign values for each row.
	       newValues.put("NAME", name);
	       newValues.put("PHONENUMBER", phoneno);
	       newValues.put("ADDRESS", address);
	       newValues.put("AGE", age);
	       newValues.put("GENDER", gender);
	       newValues.put("EMERGENCYCONTACT", emecontact);
			newValues.put("USERNAME", username);
			newValues.put("PASSWORD",password);
			
			
			db.insert("LOGIN", null, newValues);
			
		}
		public String getSinlgeEntry(String password)
		{
			Cursor cursor=db.query("LOGIN", null, " PASSWORD=?", new String[]{password}, null, null, null);
	        if(cursor.getCount()<1) 
	       {
	       	cursor.close();
	       	String password2="NOT EXIST";
	        	return password2;
	       }
		    cursor.moveToFirst();
			String password1= cursor.getString(cursor.getColumnIndex("PASSWORD"));
			cursor.close();
			return password1;					
		}
		public String getSinlgeEntry1()
		{
			Cursor cursor=db.query("LOGIN", null, null, null, null, null, null);
	        if(cursor.getCount()<1) 
	       {
	       	cursor.close();
	       	String password2="NOT EXIST";
	        	return password2;
	       }
		    cursor.moveToFirst();
			String name= cursor.getString(cursor.getColumnIndex("NAME"));
			cursor.close();
			return name;					
		}
		public String getEmergency()
		{
			Cursor cursor=db.query("LOGIN", null, null, null, null, null, null);
	        if(cursor.getCount()<1) 
	       {
	       	cursor.close();
	       	String password2="NOT EXIST";
	        	return password2;
	       }
		    cursor.moveToFirst();
			String name= cursor.getString(cursor.getColumnIndex("EMERGENCYCONTACT"));
			cursor.close();
			return name;					
		}
		public String[] getAllData()
		{
			Cursor cursor=db.query("LOGIN", null, null, null, null, null, null);
	        if(cursor.getCount()<1) 
	       {
	       	cursor.close();
	       	
	       }
		    cursor.moveToFirst();
		    
			String name= cursor.getString(cursor.getColumnIndex("NAME"));
			String phonenum= cursor.getString(cursor.getColumnIndex("PHONENUMBER"));
			String address= cursor.getString(cursor.getColumnIndex("ADDRESS"));
			String age= cursor.getString(cursor.getColumnIndex("AGE"));
			String gender= cursor.getString(cursor.getColumnIndex("GENDER"));
			String emcontact= cursor.getString(cursor.getColumnIndex("EMERGENCYCONTACT"));
			String username= cursor.getString(cursor.getColumnIndex("USERNAME"));
			cursor.close();
			 String[] alldetails=new String[]{name,phonenum,address,age,gender,emcontact,username}; 
			return alldetails;		
			
			
			
			
			
				
				
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
		}
		
		
}

