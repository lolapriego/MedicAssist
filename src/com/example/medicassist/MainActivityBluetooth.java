package com.example.medicassist;

import java.util.Set; 

import com.example.medicassist.R;

import android.os.Bundle;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class MainActivityBluetooth extends Activity {

	protected static final String TAG = "BLUETOOTH";
    protected static final int DISCOVERY_REQUEST = 1;
    BluetoothAdapter bluetooth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_bluetooth);

      BluetoothAdapter bluetooth = BluetoothAdapter.getDefaultAdapter();   

      this.bluetooth = bluetooth;
      initBluetooth();

    }
    
    private static final int ENABLE_BLUETOOTH = 1;

    private void initBluetooth() {
      if (!bluetooth.isEnabled()) { 
        // Bluetooth isn't enabled, prompt the user to turn it on.
        Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        startActivityForResult(intent, ENABLE_BLUETOOTH);
      } else {
        // Bluetooth is enabled, initialize the UI.
      }
    }
    
    
    ////To Make the mobile Discoverable for 300secs
    public void Enable_discoverability(View view){
    Intent discoverableIntent = new
    		Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
    		discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
    		startActivity(discoverableIntent);
    }
    
    public void Pair(View view) {
    	Intent i = new Intent(MainActivityBluetooth.this, BluetoothPair.class);
        startActivityForResult(i, 100);
    }
    public void Connect(View view) {
    	Intent i = new Intent(MainActivityBluetooth.this, BluetoothDevices.class);
        startActivityForResult(i, 100);
    }
    public void dummy(View view) {
    	//Intent i = new Intent(MainActivity.this, BluetoothPair.class);
        //startActivityForResult(i, 100);
    }
}
