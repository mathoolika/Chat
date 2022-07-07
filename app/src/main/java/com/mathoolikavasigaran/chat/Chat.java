package com.mathoolikavasigaran.chat;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.os.Handler;

public class Chat {


    private static final int STATE_NONE = 0;
    private final Handler handler;
    private final Context context;
    int state = STATE_NONE;
    private BluetoothAdapter btAdapter;

    public Chat(Context context, Handler handler) {
        this.context = context;
        this.handler = handler;


        btAdapter = BluetoothAdapter.getDefaultAdapter();
    }

    public void write(byte[] bytes) {
    }

    public void connect(BluetoothDevice remoteDevice) {
    }

    public void stop() {
    }
}
