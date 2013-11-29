package com.example.medicassist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

//OrderNo, Type, Menu, Item, Date-Time, Quantity, Comments, Taken By, Table, Total Price

public class MySQLiteHelper extends SQLiteOpenHelper 
{
	//All Appointments
	public static final String TABLE_Doctors 			= "TabDoctors";
	public static final String TABLE_Appointments 		= "TabAppointments";
	public static final String TABLE_Decives	 		= "TabDevices";

	//Column Definitions for all tables
	public static final String COLUMN_DocID 			= "DocID";
	public static final String COLUMN_DocName 			= "DocName";
	public static final String COLUMN_DocSpecialization = "DocSpecialization";
	public static final String COLUMN_DocHospital 		= "DocHospital";
	public static final String COLUMN_DocStatus 		= "DocStatus";
	public static final String COLUMN_DocDetails		= "DocDetails";
	
	public static final String COLUMN_AppID				= "AppID";
	public static final String COLUMN_AppDocID			= "AppDocID";
	public static final String COLUMN_AppStatus			= "AppStatus";
	public static final String COLUMN_AppFrom_Date		= "AppFromDate";
	public static final String COLUMN_AppFrom_Time		= "AppFromTime";
	public static final String COLUMN_AppTo_Date		= "AppToDate";
	public static final String COLUMN_AppTo_Time		= "AppToTime";
	public static final String COLUMN_AppComments		= "AppComments";
	
	public static final String COLUMN_DevID 		= "DevID";
	public static final String COLUMN_DevStatus		= "DevStatus";
	public static final String COLUMN_DevDocID	 	= "DevDocID";
	public static final String COLUMN_DevComments	= "DevComments";

	private static final String DATABASE_NAME 		= "comCS442App.db";
	private static final int DATABASE_VERSION 		= 1;

	// Doc Table creation sql statement
	private static final String TABLE_DOC_CREATE = "create table "
      + TABLE_Doctors 		+ "(" 
      + COLUMN_DocID 		+ " integer primary key autoincrement, "
      + COLUMN_DocName 		+ " text, "
      + COLUMN_DocSpecialization + " text, "
      + COLUMN_DocHospital	+ " text, "
      + COLUMN_DocStatus 	+ " text, " 
      + COLUMN_DocDetails 	+ " text"
      +	");";
	
	// App Table creation sql statement
	private static final String TABLE_APP_CREATE = "create table "
	  + TABLE_Appointments 	+ "(" 
	  + COLUMN_AppID 		+ " integer primary key autoincrement, "
	  + COLUMN_AppDocID		+ " integer, "
	  + COLUMN_AppStatus 	+ " text, "
	  + COLUMN_AppFrom_Date + " text, "
	  + COLUMN_AppFrom_Time	+ " text, "
	  + COLUMN_AppTo_Date	+ " text, "
	  + COLUMN_AppTo_Time	+ " text, "
	  + COLUMN_AppComments	+ " text"
	  +	");";
	
	// Doc Table creation sql statement
	private static final String TABLE_DEV_CREATE = "create table "
	  + TABLE_Decives 		+ "(" 
	  + COLUMN_DevID 		+ " integer primary key autoincrement, "
	  + COLUMN_DevStatus 	+ " text, "
	  + COLUMN_DevDocID 	+ " integer, " 
	  + COLUMN_DevComments 	+ " text"
	  +	");";
  		
  // Constructor to create the DB
  public MySQLiteHelper(Context context) 
  {
	//Create the DB
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase database) 
  {
	//Create the Tables
    database.execSQL(TABLE_DOC_CREATE);
    database.execSQL(TABLE_APP_CREATE);
    database.execSQL(TABLE_DEV_CREATE);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
  {
    Log.w(MySQLiteHelper.class.getName(), "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_Doctors);
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_Appointments);
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_Decives);
    onCreate(db);
  }
} 

