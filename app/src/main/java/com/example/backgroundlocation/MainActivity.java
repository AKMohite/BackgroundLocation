package com.example.backgroundlocation;

import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

public class MainActivity extends AppCompatActivity {

    Button startBtn, stopBtn;
    LocationRequest locationRequest;
    FusedLocationProviderClient fusedLocationProviderClient;
    private View.OnClickListener startBtnListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
//            i1 = new Intent(MainActivity.this, BackgroundLocationService.class);
//            startService(i1);
            updateLocation();
        }
    };

    private void updateLocation() {
        locationRequest = new LocationRequest();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(30000);
        locationRequest.setMaxWaitTime(30000);
//        locationRequest.setSmallestDisplacement(10f);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        fusedLocationProviderClient.requestLocationUpdates(locationRequest, getPendingIntent());
    }

    @Override
    protected void onDestroy() {
//        if (fusedLocationProviderClient != null){
//            fusedLocationProviderClient.removeLocationUpdates(getPendingIntent());
//        }
        super.onDestroy();
    }

    private PendingIntent getPendingIntent() {
        Intent intent = new Intent(this, LocationBroadcastReceiver.class);
        intent.setAction(LocationBroadcastReceiver.ACTION_PROCESS_UPDATE);
        return PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    private View.OnClickListener stopBtnListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
//            i1 = new Intent(MainActivity.this, BackgroundLocationService.class);
//            stopService(i1);
        }
    };
    Intent i1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startBtn = findViewById(R.id.start_btn);
        stopBtn = findViewById(R.id.stop_btn);

        startBtn.setOnClickListener(startBtnListener);

        stopBtn.setOnClickListener(stopBtnListener);
    }
}
