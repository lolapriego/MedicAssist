package com.example.medicassist;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

import android.app.ActionBar;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.telephony.SmsManager;

/**
 * This is the main Activity that displays the current device session.
 */
public class BluetoothDevices extends Activity {
    // Debugging
    private static final String TAG = "BluetoothDevice";
    private static final boolean D = true;

    // Message types sent from the BluetoothDeviceService Handler
    public static final int MESSAGE_STATE_CHANGE = 1;
    public static final int MESSAGE_READ = 2;
    public static final int MESSAGE_WRITE = 3;
    public static final int MESSAGE_DEVICE_NAME = 4;
    public static final int MESSAGE_TOAST = 5;

    // Key names received from the BluetoothDeviceService Handler
    public static final String DEVICE_NAME = "device_name";
    public static final String DEVICE_ADDRESS = "device_address";
    public static final String TOAST = "toast";

    // Intent request codes
    private static final int REQUEST_CONNECT_DEVICE_SECURE = 1;
    private static final int REQUEST_ENABLE_BT = 3;

    // Layout Views
    private ListView mConversationView;

    // Name of the connected device
    private String mConnectedDeviceName = null;
    private String mConnectedDeviceAddress = null;
    
    // Array adapter for the conversation thread
    private ArrayAdapter<String> mConversationArrayAdapter;

    // Local Bluetooth adapter
    private BluetoothAdapter mBluetoothAdapter = null;
    // Member object for the device services
    private BluetoothDeviceService mDeviceService = null;
    
    //To get Emergency Contact
    LoginDataBaseAdapter loginDataBaseAdapter=new LoginDataBaseAdapter(this);
    public static String Emergency;
    public static String Name;
    

    //Implement Timer for Emergency
    CountDownTimer timeCounter=new CountDownTimer(30000, 1000) {

        public void onTick(long millisUntilFinished) {
        	//Log.e(TAG, Emergency);     
        	loginDataBaseAdapter=loginDataBaseAdapter.open();
        	Emergency=loginDataBaseAdapter.getEmergency();
        	Name=loginDataBaseAdapter.getSinlgeEntry1();
        	loginDataBaseAdapter.close();
        }

        public void onFinish() {
        	
        	//To send message
        	SmsManager sms = SmsManager.getDefault();
        	String message="Emergency: "+Name+" needs immediate attention.";
            sms.sendTextMessage(Emergency, null, message, 
                                 null, null);
        	//To Make a call
        	/*Intent callIntent = new Intent(Intent.ACTION_CALL);
        	String temp="tel:"+Emergency;
        	Log.e(TAG, temp);
        	Log.e(TAG, message);
			callIntent.setData(Uri.parse(temp));
			startActivity(callIntent);
			*/
        }
     };
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(D) Log.e(TAG, "+++ ON CREATE +++");

        // Set up the window layout
        setContentView(R.layout.bluetooth_main);

        // Get local Bluetooth adapter
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        // If the adapter is null, then Bluetooth is not supported
        if (mBluetoothAdapter == null) {
            Toast.makeText(this, "Bluetooth is not available", Toast.LENGTH_LONG).show();
            finish();
            return;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if(D) Log.e(TAG, "++ ON START ++");

        // If BT is not on, request that it be enabled.
        // setupDevice() will then be called during onActivityResult
        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableIntent, REQUEST_ENABLE_BT);
        // Otherwise, setup the device session
        } else {
            if (mDeviceService == null) setupDevice();
        }
    }

    @Override
    public synchronized void onResume() {
        super.onResume();
        if(D) Log.e(TAG, "+ ON RESUME +");

        // Performing this check in onResume() covers the case in which BT was
        // not enabled during onStart(), so we were paused to enable it...
        // onResume() will be called when ACTION_REQUEST_ENABLE activity returns.
        if (mDeviceService != null) {
            // Only if the state is STATE_NONE, do we know that we haven't started already
            if (mDeviceService.getState() == BluetoothDeviceService.STATE_NONE) {
              // Start the Bluetooth device services
              mDeviceService.start();
            }
        }
    }

    private void setupDevice() {
        Log.d(TAG, "setupDevice()");

        // Initialize the array adapter for the conversation thread
        mConversationArrayAdapter = new ArrayAdapter<String>(this, R.layout.device_message);
        mConversationView = (ListView) findViewById(R.id.in);
        mConversationView.setAdapter(mConversationArrayAdapter);

        

        // Initialize the BluetoothDeviceService to perform bluetooth connections
        mDeviceService = new BluetoothDeviceService(this, mHandler);

    }

    @Override
    public synchronized void onPause() {
        super.onPause();
        if(D) Log.e(TAG, "- ON PAUSE -");
    }

    @Override
    public void onStop() {
        super.onStop();
        if(D) Log.e(TAG, "-- ON STOP --");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // Stop the Bluetooth device services
        if (mDeviceService != null) mDeviceService.stop();
        if(D) Log.e(TAG, "--- ON DESTROY ---");
    }

    /**
     * Sends a message.
     * @param message  A string of text to send.
     */
    

    private final void setStatus(int resId) {
        final ActionBar actionBar = getActionBar();
        actionBar.setSubtitle(resId);
    }

    private final void setStatus(CharSequence subTitle) {
        final ActionBar actionBar = getActionBar();
        actionBar.setSubtitle(subTitle);
    }

    // The Handler that gets information back from the BluetoothDeviceService
    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
            case MESSAGE_STATE_CHANGE:
                if(D) Log.i(TAG, "MESSAGE_STATE_CHANGE: " + msg.arg1);
                switch (msg.arg1) {
                case BluetoothDeviceService.STATE_CONNECTED:
                	setStatus(getString(R.string.title_connected_to, mConnectedDeviceName));
                    mConversationArrayAdapter.clear();
                    break;
                case BluetoothDeviceService.STATE_CONNECTING:
                    setStatus(R.string.title_connecting);
                    break;
                case BluetoothDeviceService.STATE_LISTEN:
                case BluetoothDeviceService.STATE_NONE:
                    setStatus(R.string.title_not_connected);
                    break;
                }
                break;
            case MESSAGE_READ:
                byte[] readBuf = (byte[]) msg.obj;
                // construct a string from the valid bytes in the buffer
                String readMessage = new String(readBuf, 0, msg.arg1);
                mConversationArrayAdapter.add(mConnectedDeviceName+":  " + readMessage);
                appendLog(mConnectedDeviceAddress,readMessage);
                break;
            case MESSAGE_DEVICE_NAME:
                // save the connected device's name
                mConnectedDeviceName = msg.getData().getString(DEVICE_NAME);
                mConnectedDeviceAddress = msg.getData().getString(DEVICE_ADDRESS);
                Toast.makeText(getApplicationContext(), "Connected to "
                               + mConnectedDeviceName, Toast.LENGTH_SHORT).show();
                break;
            case MESSAGE_TOAST:
                Toast.makeText(getApplicationContext(), msg.getData().getString(TOAST),
                               Toast.LENGTH_SHORT).show();
                break;
            }
        }
    };

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(D) Log.d(TAG, "onActivityResult " + resultCode);
        switch (requestCode) {
        case REQUEST_CONNECT_DEVICE_SECURE:
            // When DeviceListActivity returns with a device to connect
            if (resultCode == Activity.RESULT_OK) {
                // Get the device MAC address
                String address = data.getExtras()
                                     .getString(DeviceListActivity.EXTRA_DEVICE_ADDRESS);
                // Get the BLuetoothDevice object
                BluetoothDevice device = mBluetoothAdapter.getRemoteDevice(address);
                // Attempt to connect to the device
                mDeviceService.connect(device,true);
            }
            break;
        case REQUEST_ENABLE_BT:
            // When the request to enable Bluetooth returns
            if (resultCode == Activity.RESULT_OK) {
                // Bluetooth is now enabled, so set up a device session
                setupDevice();
            } else {
                // User did not enable Bluetooth or an error occurred
                Log.d(TAG, "BT not enabled");
                Toast.makeText(this, R.string.bt_not_enabled_leaving, Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent serverIntent = null;
        switch (item.getItemId()) {
        case R.id.secure_connect_scan:
            // Launch the DeviceListActivity to see devices and do scan
            serverIntent = new Intent(this, DeviceListActivity.class);
            startActivityForResult(serverIntent, REQUEST_CONNECT_DEVICE_SECURE);
            return true;
        
        }
        return false;
    }
    
    //Function to create a log with time stamp
    public void appendLog(String deviceAddress, String text) { 
    	String path="sdcard/"+deviceAddress+".txt";   	  
    	String mydate = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
        File logFile = new File(path); 
        timeCounter.start();
        if (!logFile.exists()) { 
            try { 
                logFile.createNewFile(); 
            }catch (IOException e){ 
                e.printStackTrace(); 
          } 
       } 
       try { 
           BufferedWriter buf = new BufferedWriter(new FileWriter(logFile, true));  
           buf.append(mydate+"			"+text); 
           buf.newLine(); 
           buf.close(); 
       } catch (IOException e) { 
           e.printStackTrace(); 
       } 
    }

}
