package com.example.backgroundlocation;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.widget.Toast;

import com.google.android.gms.location.LocationResult;

public class LocationBroadcastReceiver extends BroadcastReceiver {

    public static final String ACTION_PROCESS_UPDATE = "com.example.backgroundlocation.UPDATE_LOCATION";
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent != null){
            String action = intent.getAction();
            if (ACTION_PROCESS_UPDATE.equals(action)){
                LocationResult result = LocationResult.extractResult(intent);
                if (result != null){
                    Location location = result.getLastLocation();

                    String locationStr = new StringBuilder("" + location.getLatitude())
                                            .append("/")
                                            .append(location.getLongitude())
                                            .toString();

                    Toast.makeText(context, locationStr, Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
