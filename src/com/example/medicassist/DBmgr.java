package com.example.medicassist;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBmgr 
{
	// Database fields
	  private SQLiteDatabase database;
	  private MySQLiteHelper dbHelper;
	  
	  public DocData DocRecord;
	  public AppData AppRecord;
	  public DevData DevRecord;
	  
	  private String[] allColumns = { MySQLiteHelper.COLUMN_DocID, MySQLiteHelper.COLUMN_DocName, MySQLiteHelper.COLUMN_DocStatus, MySQLiteHelper.COLUMN_DocDetails};
	  
	  //Constructor
	  public DBmgr(Context context) 
	  {
	    dbHelper = new MySQLiteHelper(context);
	  }
	  
	  public void open() throws SQLException 
	  {
	    database = dbHelper.getWritableDatabase();
	  }
	  
	  public void close() 
	  {
	    dbHelper.close();
	  }
	  
	  public void addDoctor(DocData doc) 
	  {
	    ContentValues values = new ContentValues();
	    values.put(MySQLiteHelper.COLUMN_DocName, doc.DocName);
	    values.put(MySQLiteHelper.COLUMN_DocSpecialization, doc.DocSpecialization);
	    values.put(MySQLiteHelper.COLUMN_DocHospital, doc.DocHospital);
	    values.put(MySQLiteHelper.COLUMN_DocStatus, doc.DocStatus);
	    values.put(MySQLiteHelper.COLUMN_DocDetails, doc.DocDetails);
	    
	    long insertId = database.insert(MySQLiteHelper.TABLE_Doctors, null, values);
	    Cursor cursor = database.query(MySQLiteHelper.TABLE_Doctors, allColumns, MySQLiteHelper.COLUMN_DocID + " = " + insertId, null, null, null, null);
	    cursor.moveToFirst();
	    
	    cursor.close();
	  }
	  
	  public DocData getDoctor(long Doc_ID)
	  {
		  DocRecord = new DocData();
		  Cursor cursor 		= database.query(MySQLiteHelper.TABLE_Doctors, allColumns, MySQLiteHelper.COLUMN_DocID + " = " + Doc_ID, null, null, null, null);
		  DocRecord.DocName 	=  cursor.getString(2);
		  DocRecord.DocSpecialization = cursor.getString(3);
		  DocRecord.DocHospital	=  cursor.getString(4);
		  DocRecord.DocStatus 	=  cursor.getString(5);
		  DocRecord.DocDetails 	=  cursor.getString(6);
		  
		  return DocRecord;
	  }
	  
	  public List<DocData> getAllDoctors() 
	  {
	    List<DocData> records = new ArrayList<DocData>();
	    DocRecord = new DocData();

	    Cursor cursor = database.query(MySQLiteHelper.TABLE_Doctors,
	        allColumns, null, null, null, null, null);

	    cursor.moveToFirst();
	    while (!cursor.isAfterLast()) 
	    {
	    	DocRecord.DocID			=  cursor.getInt(1);
	    	DocRecord.DocName 		=  cursor.getString(2);
	    	DocRecord.DocSpecialization = cursor.getString(3);
	    	DocRecord.DocHospital	=  cursor.getString(4);
	    	DocRecord.DocStatus 	=  cursor.getString(5);
	    	DocRecord.DocDetails 	=  cursor.getString(6);
	    	records.add(DocRecord);
	    	cursor.moveToNext();
	    }
	    // close the cursor, at end
	    cursor.close();
	    return records;
	  }
	  
	  public void addAppointment(AppData NewAppointment)
	  {
		  ContentValues values = new ContentValues();
		  values.put(MySQLiteHelper.COLUMN_AppDocID, NewAppointment.AppDocID);
		  values.put(MySQLiteHelper.COLUMN_AppStatus, NewAppointment.AppStatus);
		  values.put(MySQLiteHelper.COLUMN_AppFrom_Date, NewAppointment.AppFromDate);
		  values.put(MySQLiteHelper.COLUMN_AppFrom_Time, NewAppointment.AppFromTime);
		  values.put(MySQLiteHelper.COLUMN_AppTo_Date, NewAppointment.AppToDate);
		  values.put(MySQLiteHelper.COLUMN_AppTo_Time, NewAppointment.AppToTime);
		  values.put(MySQLiteHelper.COLUMN_AppComments, NewAppointment.AppComments);
		  
		  long insertId = database.insert(MySQLiteHelper.TABLE_Appointments, null, values);
		  Cursor cursor = database.query(MySQLiteHelper.TABLE_Appointments, allColumns, MySQLiteHelper.COLUMN_AppID + " = " + insertId, null, null, null, null);
		  cursor.moveToFirst();
		    
		  cursor.close();
	  }
	  
	  public AppData getAppointment(long App_ID)
	  {
		  AppRecord = new AppData();
		  Cursor cursor 			= 	database.query(MySQLiteHelper.TABLE_Appointments, allColumns, MySQLiteHelper.COLUMN_AppID + " = " + App_ID, null, null, null, null);
		  AppRecord.AppStatus 		=  	cursor.getString(2);
		  AppRecord.AppDocID 		=  	cursor.getInt(3);
		  AppRecord.AppFromDate 	=  	cursor.getString(4);
		  AppRecord.AppFromTime 	=  	cursor.getString(5);
		  AppRecord.AppToDate		=	cursor.getString(6);
		  AppRecord.AppToTime 		=  	cursor.getString(7);
		  AppRecord.AppComments		=	cursor.getString(8);
		  
		  return AppRecord;
	  }
	  
	  public List<AppData> getAllAppointments()
	  {
		  List<AppData> records = new ArrayList<AppData>();
		  AppRecord = new AppData();

		    Cursor cursor = database.query(MySQLiteHelper.TABLE_Doctors,
		        allColumns, null, null, null, null, null);

		    cursor.moveToFirst();
		    while (!cursor.isAfterLast()) 
		    {
		    	AppRecord.AppStatus 	=  	cursor.getString(2);
				AppRecord.AppDocID 		=  	cursor.getInt(3);
				AppRecord.AppFromDate 	=  	cursor.getString(4);
				AppRecord.AppFromTime 	=  	cursor.getString(5);
				AppRecord.AppToDate		=	cursor.getString(6);
				AppRecord.AppToTime		=  	cursor.getString(7);
				AppRecord.AppComments	=	cursor.getString(8);
		    	records.add(AppRecord);
		    	cursor.moveToNext();
		    }
		    // close the cursor, at end
		    cursor.close();
		    return records;
	  }
	  
	  public void addAppointment(DevData NewDevice)
	  {
		  ContentValues values = new ContentValues();
		  values.put(MySQLiteHelper.COLUMN_DevStatus, NewDevice.DevStatus);
		  values.put(MySQLiteHelper.COLUMN_DevDocID, NewDevice.DevDocID);
		  values.put(MySQLiteHelper.COLUMN_DevComments, NewDevice.DevComments);
		  
		  long insertId = database.insert(MySQLiteHelper.TABLE_Decives, null, values);
		  Cursor cursor = database.query(MySQLiteHelper.TABLE_Decives, allColumns, MySQLiteHelper.COLUMN_DevID + " = " + insertId, null, null, null, null);
		  cursor.moveToFirst();
		    
		  cursor.close();
	  }
	  
	  public DevData getDevice(long Dev_ID)
	  {
		  DevRecord = new DevData();
		  Cursor cursor 		= database.query(MySQLiteHelper.TABLE_Decives, allColumns, MySQLiteHelper.COLUMN_DevID + " = " + Dev_ID, null, null, null, null);
		  DevRecord.DevStatus 	=  cursor.getString(2);
		  DevRecord.DevDocID 	=  cursor.getInt(3);
		  DevRecord.DevComments =  cursor.getString(4);
		  
		  return DevRecord;
	  }
	  
	  public List<DevData> getAllDevices()
	  {
		  List<DevData> records = new ArrayList<DevData>();
		  DevRecord = new DevData();

		    Cursor cursor = database.query(MySQLiteHelper.TABLE_Decives,
		        allColumns, null, null, null, null, null);

		    cursor.moveToFirst();
		    while (!cursor.isAfterLast()) 
		    {
		    	DevRecord.DevStatus 	=  cursor.getString(2);
				DevRecord.DevDocID 		=  cursor.getInt(3);
				DevRecord.DevComments 	=  cursor.getString(4);
		    	records.add(DevRecord);
		    	cursor.moveToNext();
		    }
		    // close the cursor, at end
		    cursor.close();
		    return records;
	  }
}