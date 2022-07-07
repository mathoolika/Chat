package com.mathoolikavasigaran.chat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
<<<<<<< Updated upstream

public class MainActivity extends AppCompatActivity {

=======
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_ENABLE_BLUETOOTH = 1;
    private static final int STATE_SEARCHING = 1;
    private static final int STATE_CONNECTING = 2;
    private static final int STATE_CONNECTED = 3;
    private static final int STATE_CONNECTION_FAILED = 4;
    private static final int STATE_MESSAGE_RECEIVED = 5;
    private static final String DEVICE_NAME = "deviceName";
    private static final String APP_NAME = "Chat App";
    private static final int SELECT_DEVICE = 102;
    private static final int LOCATION_PERMISSION_REQUEST = 101 ;
    private ArrayAdapter<String> adapterMainChat;
    private String device;
    private Button connectBtn;
    private Button sendBtn;
    private Button searchBtn;
    private ListView listView;
    private TextView message;
    private TextView status;
    private EditText space;
    private BluetoothAdapter btAdapter;
    private BluetoothDevice[] btDevice;
    private Context context;
    private Chat chat;

    //Receive receive;

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case STATE_SEARCHING:
                    status.setText("Searching");
                    break;
                case STATE_CONNECTING:
                    status.setText("Connecting...");
                    break;
                case STATE_CONNECTED:
                    status.setText("Connected: " + device);
                    break;
                case STATE_CONNECTION_FAILED:
                    status.setText("Connection Failed");
                    break;
                case STATE_MESSAGE_RECEIVED:
                    byte[] buffer1 = (byte[]) msg.obj;
                    String outputBuffer = new String(buffer1);
                    adapterMainChat.add("Me: " + outputBuffer);
                    break;
            }
            return false;
        }
    });

>>>>>>> Stashed changes
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
<<<<<<< Updated upstream
=======
        setUI();
        setBluetooth();
        chat = new Chat(context, handler);

    }

    private void setBluetooth() {
        btAdapter = BluetoothAdapter.getDefaultAdapter();
        if (btAdapter == null) {
            Toast.makeText(context, "No bluetooth found", Toast.LENGTH_SHORT).show();
        }
    }

    private void setUI() {
        connectBtn = findViewById(R.id.connect);
        sendBtn = findViewById(R.id.send);
        searchBtn = findViewById(R.id.search);
        listView = findViewById(R.id.list);
        message = findViewById(R.id.message);
        status = findViewById(R.id.status);
        space = findViewById(R.id.writespace);


        //adapterMainChat = new ArrayAdapter<String>(context, R.layout.msg_layout);
        //listView.setAdapter(adapterMainChat);

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = space.getText().toString();
                if (!message.isEmpty()) {
                    space.setText("");
                    chat.write(message.getBytes());
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search:
                checkPermissions();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void checkPermissions() {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST);
        } else {
            Intent intent = new Intent(context, ListActivity.class);
            startActivityForResult(intent, SELECT_DEVICE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SELECT_DEVICE && resultCode == RESULT_OK) {
            String address = data.getStringExtra("deviceAddress");
            chat.connect(btAdapter.getRemoteDevice(address));
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == LOCATION_PERMISSION_REQUEST) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(context, ListActivity.class);
                startActivityForResult(intent, SELECT_DEVICE);
            } else {
                new AlertDialog.Builder(context)
                        .setCancelable(false)
                        .setMessage("Location permission is required.")
                        .setPositiveButton("Grant", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                checkPermissions();
                            }
                        })
                        .setNegativeButton("Deny", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                MainActivity.this.finish();
                            }
                        }).show();
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (chat != null) {
            chat.stop();
        }
>>>>>>> Stashed changes
    }
}