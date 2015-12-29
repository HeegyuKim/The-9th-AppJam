package will.wonderland;

import android.app.ActivityManager;
import android.app.ApplicationErrorReport;
import android.app.Service;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

/**
 * Created by triti on 2015-12-19.
 */
public class BackService extends Service {
    private Handler mHandler;
    public int processNum, processNumHis;


    @Override
    public void onCreate() {

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // TODO Auto-generated method stub
        mHandler = new Handler();
        int tmp = isServiceRunningCheck();

        processNum = tmp;
        processNumHis = tmp;

        new Thread(task).start();

        return super.onStartCommand(intent, flags, startId);
    }


    Runnable task = new Runnable() {
        int rd;

        public void run() {

            while (true) {

                if (processNum > processNumHis) {

                    WallpaperManager myWallpaperManager = WallpaperManager.getInstance(getApplicationContext());
                     try {
                        rd = (int) (Math.random() * 4) + 1;
                        if (rd == 1)
                            myWallpaperManager.setResource(R.raw.bgg_1);
                        else if (rd == 2)
                            myWallpaperManager.setResource(R.raw.bgg_2);
                        else if (rd == 3)
                            myWallpaperManager.setResource(R.raw.bg_3);
                        else if (rd == 4)
                            myWallpaperManager.setResource(R.raw.bg_4);


                    } catch (IOException e) {
                        e.printStackTrace();


                    }



                }
                processNumHis = isServiceRunningCheck();
                processNum = isServiceRunningCheck();

            }
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();


    }

    private class ToastRunnable implements Runnable {
        String mText;

        public ToastRunnable(String text) {
            mText = text;
        }

        @Override
        public void run(){
            Toast.makeText(getApplicationContext(), mText, Toast.LENGTH_SHORT).show();
        }
    }


    private void someMethod(int a,int b) {
        mHandler.post(new ToastRunnable(a+" "+b));
    }
    public int isServiceRunningCheck() {
        int cnt = 0;
        //ActivityManager am = (ActivityManager) getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        // List<ActivityManager.RunningAppProcessInfo> appList = am.getRunningAppProcesses();

        //return appList.size();
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            ++cnt;
        }
        return cnt;
    }
}
