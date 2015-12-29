package kr.applepi.drinkingcare.services;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import kr.applepi.drinkingcare.Activitys.MainActivity;
import kr.applepi.drinkingcare.R;

public class MyService extends Service {
    public MyService() {
    }

    public static boolean first = true;

    int i=0;

    private Handler mHandler;
    private Runnable mRunnable;

    NotificationManager nm;
    Resources res;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            updateThread();
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("start", "서비스 시작!");

        nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        res = getResources();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // TODO Auto-generated method stub

        Thread myThread = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    try {
                        handler.sendMessage(handler.obtainMessage());
                        Thread.sleep(1000);
                    } catch (Throwable t) {
                    }
                }
            }
        });

        if (first) {
            myThread.start();
            first = false;
        }

        return super.onStartCommand(intent, flags, startId);
    }

    private void updateThread() {
        if (MainActivity.time > 0) {
            MainActivity.time--;
        }

        if (MainActivity.time == 3600) {
            Intent notificationIntent = new Intent(this, MyService.class);
            notificationIntent.putExtra("notificationId", 9999); //전달할 값
            PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                    .setPriority(Notification.PRIORITY_MAX)
                    .setContentTitle("약 1시간 후 소변이 마려울 예정입니다.")
                    .setContentText("여유가 있을 때 미리 대비하세요!")
                    .setTicker("약 1시간 후 소변이 마려울 예정입니다. 여유가 있을 때 미리 대비하세요!")
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setLargeIcon(BitmapFactory.decodeResource(res, R.drawable.icon))
                    .setContentIntent(contentIntent)
                    .setAutoCancel(true)
                    .setWhen(System.currentTimeMillis())
                    .setDefaults( Notification.DEFAULT_SOUND|Notification.DEFAULT_VIBRATE|Notification.DEFAULT_LIGHTS)
                    .setNumber(13);

            Notification  n = builder.build();
            nm.notify(1234, n);
        }

        if (MainActivity.time == 1800) {
            Intent notificationIntent = new Intent(this, MyService.class);
            notificationIntent.putExtra("notificationId", 9999); //전달할 값
            PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                    .setPriority(Notification.PRIORITY_MAX)
                    .setContentTitle("약 30분 후 소변이 마려울 예정입니다.")
                    .setContentText("여유가 있을 때 미리 대비하세요!")
                    .setTicker("약 30분 후 소변이 마려울 예정입니다. 여유가 있을 때 미리 대비하세요!")
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setLargeIcon(BitmapFactory.decodeResource(res, R.drawable.icon))
                    .setContentIntent(contentIntent)
                    .setAutoCancel(true)
                    .setWhen(System.currentTimeMillis())
                    .setDefaults( Notification.DEFAULT_SOUND|Notification.DEFAULT_VIBRATE|Notification.DEFAULT_LIGHTS)
                    .setNumber(13);

            Notification  n = builder.build();
            nm.notify(1234, n);
        }

        if (MainActivity.time == 600) {
            Intent notificationIntent = new Intent(this, MyService.class);
            notificationIntent.putExtra("notificationId", 9999); //전달할 값
            PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                    .setPriority(Notification.PRIORITY_MAX)
                    .setContentTitle("약 10분 후 소변이 마려울 예정입니다.")
                    .setContentText("지금 대비하지 않으면 늦습니다!")
                    .setTicker("약 10분 후 소변이 마려울 예정입니다. 지금 대비하지 않으면 늦습니다!")
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setLargeIcon(BitmapFactory.decodeResource(res, R.drawable.icon))
                    .setContentIntent(contentIntent)
                    .setAutoCancel(true)
                    .setWhen(System.currentTimeMillis())
                    .setDefaults( Notification.DEFAULT_SOUND|Notification.DEFAULT_VIBRATE|Notification.DEFAULT_LIGHTS)
                    .setNumber(13);

            Notification  n = builder.build();
            nm.notify(1234, n);
        }
    }

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
}
