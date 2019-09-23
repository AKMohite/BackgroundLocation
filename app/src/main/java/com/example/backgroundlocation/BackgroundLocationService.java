package com.example.backgroundlocation;

import android.app.Dialog;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.LocationManager;
import android.os.BatteryManager;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

public class BackgroundLocationService extends Service {
    /** indicates how to behave if the service is killed */
    int mStartMode;
    public static final String
            BROADCAST_ACTION = "logout";
    /** interface for clients that bind */
    IBinder mBinder;

    /** indicates whether onRebind should be used */
    boolean mAllowRebind;

    /** Called when the service is being created. */
    @Override
    public void onCreate() {

    }

    private Dialog noLocationDialog;;
    /** The service is starting, due to a call to startService() */

    /** The service is starting, due to a call to startService() */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Handler handler = new Handler();
        final Runnable runAble = new Runnable() {
            @Override
            public void run() {
                String gps = "On";
                Log.d("Check_gps","Checking...");
                LocationManager lm = (LocationManager)getSystemService(LOCATION_SERVICE);
                boolean gps_enabled = false;
                boolean network_enabled = false;
                try {
                    gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
                } catch(Exception ex) {}
                if(!gps_enabled) {
//                    final HomeFragment homeFragment = new HomeFragment();
//                    homeFragment.callLogout();
//                    Intent intent1 = new Intent(BROADCAST_ACTION);
//                    sendBroadcast(intent1);
                    gps  = "Off";
                }else {
//                    if(!((DriveMeApplication) getApplication()).getRideActive()){
//                        handler.postDelayed(this, 10000);
//                    }else{
                        final LocationManager manager =
                                (LocationManager) getApplication().getSystemService(Context.LOCATION_SERVICE);
                        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                            if (noLocationDialog == null) {
//                                noLocationDialog = Utility.buildAlertDialogNoGps(getApplication());
                            }
//                            noLocationDialog.show();
                        }
//                    }
                }

                IntentFilter iFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
                Intent batteryStatus = getApplicationContext().registerReceiver(null, iFilter);
                int level = batteryStatus != null ? batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1) : -1;
                int scale = batteryStatus != null ? batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1) : -1;
                float batteryPct = level / (float) scale;
                int battery =  (int) (batteryPct * 100);
                Log.d("Battery:",battery+" ");

//                String token = NotificationPreferences.prefs(DriveMeApplication.getContext()).getString(NotificationPreferences.NOTIFICATION_REGISTRATION_TOKEN, "");
//                String device_id = Utility.uniqueDeviceID(DriveMeApplication.getContext());
                String myDeviceModel = android.os.Build.MODEL;
                String myDeviceProduct = android.os.Build.PRODUCT;
                String myDeviceManufact = android.os.Build.MANUFACTURER;
                String myDeviceBrand = android.os.Build.BRAND;
                String myDevice = android.os.Build.DEVICE;
//
//                ApiService apiService = RestClient.service();
//                Driver driver = Util.getUser(DriveMeApplication.getContext());
//                if(driver!=null) {
//                    String ride_active="";
//                    if(!((DriveMeApplication) getApplication()).getRideActive()){
//                        ride_active = "No";
//                    }else{
//                        ride_active = "Yes";
//                    }
//                    apiService.updateBatteryPercentage(driver.getDriver_id(), battery + "", device_id, myDeviceModel, myDeviceProduct, myDeviceManufact, myDeviceBrand, myDevice,gps,ride_active).subscribe(apiResponse -> {
//                        if (apiResponse.getStatusCode().equalsIgnoreCase("200")) {
//                        }
//                    });
//                }
            }
        };
        handler.postDelayed(runAble,900000);
        return START_STICKY;
    }

//    private RideModel ridesModel() {
//        return new ServiceHomeModel(RestClient.service());
//    }
    /** A client is binding to the service with bindService() */
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    /** Called when all clients have unbound with unbindService() */
    @Override
    public boolean onUnbind(Intent intent) {
        return mAllowRebind;
    }

    /** Called when a client is binding to the service with bindService()*/
    @Override
    public void onRebind(Intent intent) {

    }

    /** Called when The service is no longer used and is being destroyed */
    @Override
    public void onDestroy() {

    }
}
