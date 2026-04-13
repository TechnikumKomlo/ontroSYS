package com.example.ontrosysm;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.StatFs;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.io.IOException;


import androidx.appcompat.app.AppCompatActivity;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    private FileServer server;
    private ProgressBar storageProgress;
    private TextView storageText;
    private TextView textView;
    private EditText portInput;

    // ✅ IP cím tárolása változóban
    private String currentIpAddress = "";

    private final Handler handler = new Handler();
    private final int REFRESH_INTERVAL = 10000; // refreshrate (ms)

    private final Runnable updateTask = new Runnable() {
        @Override
        public void run() {
            updateStorageBar();
            updateWifiIp();
            handler.postDelayed(this, REFRESH_INTERVAL);
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        storageProgress = findViewById(R.id.storageProgress);
        storageText = findViewById(R.id.storageText);
        textView = findViewById(R.id.textView);
        portInput = findViewById(R.id.portInput);


        updateStorageBar();
        updateWifiIp();
        startFileServer();
    }


    @Override
    protected void onResume() {
        super.onResume();
        handler.post(updateTask);
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(updateTask);
    }

    private void updateStorageBar() {
        File path = Environment.getDataDirectory();
        StatFs stat = new StatFs(path.getPath());

        long blockSize = stat.getBlockSizeLong();
        long totalBlocks = stat.getBlockCountLong();
        long availableBlocks = stat.getAvailableBlocksLong();

        long totalBytes = totalBlocks * blockSize;
        long freeBytes = availableBlocks * blockSize;
        long usedBytes = totalBytes - freeBytes;

        int usedPercent = (int) ((usedBytes * 100L) / totalBytes);

        storageProgress.setProgress(usedPercent);

        if (usedPercent >= 90) {
            storageProgress.setProgressTintList(ColorStateList.valueOf(Color.RED));
        } else if (usedPercent >= 75) {
            storageProgress.setProgressTintList(ColorStateList.valueOf(Color.YELLOW));
        } else {
            storageProgress.setProgressTintList(ColorStateList.valueOf(Color.GREEN));
        }

        String text = "Used: " + formatSize(usedBytes) +
                " / " + formatSize(totalBytes) +
                " (" + usedPercent + "%)";
        storageText.setText(text);
    }

    private void updateWifiIp() {
        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        int ip = wifiInfo.getIpAddress();

        //IP mentése változóba
        currentIpAddress = String.format(
                "%d.%d.%d.%d",
                (ip & 0xff),
                (ip >> 8 & 0xff),
                (ip >> 16 & 0xff),
                (ip >> 24 & 0xff)
        );

        textView.setText(currentIpAddress);
    }

    public String getCurrentIpAddress() {
        return currentIpAddress;
    }

    private String formatSize(long bytes) {
        float kb = bytes / 1024f;
        float mb = kb / 1024f;
        float gb = mb / 1024f;

        if (gb >= 1) return String.format("%.2f GB", gb);
        if (mb >= 1) return String.format("%.2f MB", mb);
        if (kb >= 1) return String.format("%.2f KB", kb);
        return bytes + " B";
    }
    private void startFileServer() {
        int port = 0; // 0 = automatikus portválasztás

        File sharedFolder = Environment.getExternalStorageDirectory();

        server = new FileServer(port, sharedFolder);

        try {
            server.start();

            int actualPort = server.getListeningPort(); // <-- a tényleges port

            // KIÍRÁS A TEXTVIEW-RE
            storageText.setText(
                    "File server running at: http://" + currentIpAddress + ":" + actualPort
            );

            // KIÍRÁS A PORTINPUT EDITTEXT-BE
            portInput.setText(String.valueOf(actualPort));

        } catch (IOException e) {
            storageText.setText("Failed to start server");
        }
    }


}