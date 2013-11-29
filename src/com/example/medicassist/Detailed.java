package com.example.medicassist;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Detailed extends Activity{

	NewAppDataBaseAdapter nadba;
	  LinearLayout layoutOfPopup;
	  protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.detailed);
			nadba=new NewAppDataBaseAdapter(this);
			nadba=nadba.open();
			Bundle extras = getIntent().getExtras();
			String abcpos = extras.getString("positionthatissent");
			int abc1=Integer.parseInt(abcpos)+1;
			
		//	TextView t1=(TextView)findViewById(R.id.docname);
		//	t1.setText(""+abc1);
		//	int abc1=0;
			String[] add=nadba.getAppDetails(abc1);
			 
			
			TextView t1=(TextView)findViewById(R.id.docname);
			TextView t2=(TextView)findViewById(R.id.time);
			TextView t3=(TextView)findViewById(R.id.date);
			TextView t4=(TextView)findViewById(R.id.notes);
			
			
			t1.setText(add[0]);
		
			t2.setText(add[1]);
			t3.setText(add[2]);
			
			t4.setText(add[3]); 
			Button update=(Button)findViewById(R.id.update);
			final Context context = this;
			
			
			update.setOnClickListener(new Button.OnClickListener() {
	            
				
				public void onClick(View v) {
			
					Intent intent = new Intent(context, updateAccountSettings.class);
	                startActivity(intent);   
				}

			});
			
			
	  }
	  protected void onDestroy() {
			
			super.onDestroy();
			
			nadba.close();
		}
	  
}


