package will.wonderland;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;

import java.util.Random;

/**
 * Created by triti on 2015-12-20.
 */
public class MusicService extends Service {
    private Handler mHandler;
    public static MediaPlayer mp;


    @Override
    public void onCreate() {

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // TODO Auto-generated method stub
        mHandler = new Handler();


        new Thread(task).start();

        return super.onStartCommand(intent, flags, startId);
    }


    Runnable task = new Runnable() {
        int rd;
        boolean mus=false;
        public void run() {
            rd = (int)(Math.random() * 20000) + 1;
            if(rd==10000){
                volumeUp();
                int rd2= (int)(Math.random() * 2) + 1;
               if(rd2==1)
                mp = MediaPlayer.create(MusicService.this, R.raw.bbung);
                if(rd2==2)
                    mp = MediaPlayer.create(MusicService.this, R.raw.moz);

                mp.setLooping(true);
                mp.start();
                mus=true;

            }
            if(rd<500&&mus){
                mp.stop();
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
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            startActivity(new Intent(getApplicationContext(), MusicActivity.class));
        }
    };
    public  void volumeUp(){
        AudioManager am = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        // 현재 볼륨 가져오기
        int volume = am.getStreamVolume(AudioManager.STREAM_MUSIC);

        // volume이 15보다 작을 때만 키우기 동작

            am.setStreamVolume(AudioManager.STREAM_MUSIC, 15, AudioManager.FLAG_PLAY_SOUND);

    }
}