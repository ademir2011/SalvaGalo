package com.example.ademi.salvagalo;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

/**
 * Created by ademi on 05/07/2016.
 */
public class FirstService extends Service {

    private static final String TAG = "StickyService";
    public Context context = this;
    public Handler handler = null;
    public static Runnable runnable = null;


    @Override
    public IBinder onBind(Intent arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "onStartCommand");

        handler = new Handler();
        runnable = new Runnable() {
            public void run() {

                ActivityManager actvityManager = (ActivityManager) getSystemService( ACTIVITY_SERVICE );
                List<ActivityManager.RunningAppProcessInfo> procInfos = actvityManager.getRunningAppProcesses();

                boolean cont = false;

                for(ActivityManager.RunningAppProcessInfo proc : procInfos){

                    if (proc.processName.equals("com.example.ademi.galonovaversao")){
                        Log.e(">",proc.processName+" - ACHOU");
                        cont = true;
                    }
                }

                if(!cont){
                    Log.e(">","NAO ACHOU vai executar");
                    Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.example.ademi.galonovaversao");
                    if (launchIntent != null) {
                        startActivity(launchIntent);//null pointer check in case package name was not found
                    }

                }

                handler.postDelayed(runnable, 10000);
            }
        };

        handler.postDelayed(runnable, 15000);

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        sendBroadcast(new Intent("YouWillNeverKillMe"));
    }
}
