package will.wonderland;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by triti on 2015-12-20.
 */
public class MusicActivity extends ActionBarActivity {
    public static MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        volumeUp();

        mp = MediaPlayer.create(this, R.raw.bbung);
        mp.setLooping(true);
        mp.start();

        Button btn=(Button)findViewById(R.id.button);

        btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mp.stop();
                    }
                });
    }
    public  void volumeUp(){
        AudioManager am = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        // 현재 볼륨 가져오기
        int volume = am.getStreamVolume(AudioManager.STREAM_MUSIC);

        // volume이 15보다 작을 때만 키우기 동작
        while(volume < 15) {
            am.setStreamVolume(AudioManager.STREAM_MUSIC, volume+1, AudioManager.FLAG_PLAY_SOUND);
        }
    }
    public void stopmusic() {
        mp.stop();
    }
}
