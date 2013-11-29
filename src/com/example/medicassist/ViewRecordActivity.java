package com.example.medicassist;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.medicassist.http.CustomHttpClient;
import com.example.medicassist.http.UrlBuilder;
import com.example.medicassist.s3.S3Getter;

public class ViewRecordActivity extends ListActivity {
    private static final int CONNECTION_ERROR = 1;
    private static final int CONNECTING = 2;
    private boolean isDoctor;
    
    private List<String> patientsName;
    private URL urlPdf;
    private String userName;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_record);
		
		patientsName = new ArrayList<String>();
		
		isDoctor = true; // must be retrieve from SharedPreferences
		userName = "lolapriego"; // must be retrieve from SharedPreferences
		
		//In order to avoid network android.os.Network error for making connections
		if (android.os.Build.VERSION.SDK_INT > 9) {
		    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		    StrictMode.setThreadPolicy(policy);
		}
    	
		if(isDoctor)
			fillData();
		else
			displayRecord(userName);
	}
	
	
	/*
	 * Method that fills the listView with the name of the patients
	 * Only will be called if a doctor is using the application
	 * It connects with our distributed MongoDB 
	 */
	private void  fillData(){
		Dialog dialog = null;
		String response = null;
		
        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		
		dialog = onCreateDialog(CONNECTING);
        try {
        	dialog.show();
            	String [] params ={"patient_of","lolapriego"};
	            response = CustomHttpClient.executeHttpGet(UrlBuilder.paramsToUrl(params, "system.users"));
	            	            
	            JSONTokener tokener = new JSONTokener( response.toString() );
	            JSONArray res = new JSONArray( tokener );
	            JSONObject obj;
	            patientsName.clear();
	            
	            for(int i = 0; i<res.length(); i++){       	
	            	 obj = res.getJSONObject(i);
	            	 
	                 HashMap<String, String> map = new HashMap<String, String>();
	                 if(obj.has("name")){
	            		 patientsName.add(obj.getString("user"));
	                	 map.put("Patient", obj.getString("name"));
	                 }
	                 
	                 list.add(map);
	            }
	        	ListAdapter adapter = new SimpleAdapter(this, list,
	                    R.layout.row,
	                    new String[] { "Patient" }, new int[] {
	                            R.id.patient_name });
	     
	            setListAdapter(adapter);
	            
	        	dialog.cancel();		        		        
        }
        catch (Exception e) {
        	dialog.cancel();
        	onCreateDialog(CONNECTION_ERROR).show();
            Log.e("CONNECTION ERROR", e.toString());
        }   
	}
	
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        
        displayRecord(patientsName.get(position));
    }
	
	private void displayRecord(String name){
		S3Getter s3 = new S3Getter(name + ".pdf");
		
		urlPdf = s3.getPdf();
		if(urlPdf != null){
			try{
				startActivity( new Intent( Intent.ACTION_VIEW, Uri.parse( urlPdf.toURI().toString() ) ) );
			}
			catch(Exception e){
				Log.e("Error Displaying PDF", e.toString());
			}
		}
		
	}

    protected Dialog onCreateDialog(int id) {
    	Dialog dialog = null;
    	switch(id){
	    	case CONNECTING: dialog = createDialogConnect();
	    							   break;
	    	case CONNECTION_ERROR: dialog = createErrorDialog();
	    									 break;
	    	default: dialog = null;
	    	         break;
    	}
    	return dialog;
    }
    
	private Dialog createDialogConnect(){
    	Dialog dialog = null;
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
       	builder.setTitle(R.string.updating);        	
    	dialog = builder.create();

    	return dialog;
	}
	
    private Dialog createErrorDialog(){
    	Dialog dialog = null;
	
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);

       	builder.setTitle(R.string.error_connection);
    	builder.setMessage(R.string.text_error_connection);
    	
    	builder.setPositiveButton(R.string.try_again, new OnClickListener() {	
    		@Override
    		public void onClick(DialogInterface dialog, int which) {
    			fillData();
    		}
    	});
    	dialog = builder.create();
    	return dialog;
    }
}
	

