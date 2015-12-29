package will.wonderland;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;


public class MainActivity extends ActionBarActivity {
    private boolean toggi = false;
    protected ImageButton btn;
    protected ImageView bg;
    protected ImageButton shareBt;
    static Intent intent_bg;
    static Intent intent_lc;
    static Intent intent_mu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startActivity(new Intent(getApplicationContext(), SlpashActivity.class));

        btn = (ImageButton) findViewById(R.id.act);
        bg = (ImageView) findViewById(R.id.bg);
        shareBt = (ImageButton) findViewById(R.id.share);

        intent_bg = new Intent(MainActivity.this, BackService.class);
        intent_lc = new Intent(MainActivity.this, LockService.class);
        intent_mu = new Intent(MainActivity.this, MusicService.class);

        intent_bg.setAction(Intent.ACTION_MAIN);
        intent_lc.setAction(Intent.ACTION_MAIN);
        intent_mu.setAction(Intent.ACTION_MAIN);

        shareBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ShareActivity.class));

            }
        });
        btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        toggi = !toggi;

                        if (toggi) {
                            btn.setBackgroundResource(R.drawable.button_off);
                            bg.setBackgroundResource(R.drawable.bg_off);


                            startService(intent_bg);
                            startService(intent_lc);
                            startService(intent_mu);


                        } else {
                            btn.setBackgroundResource(R.drawable.button_on);
                            bg.setBackgroundResource(R.drawable.bg_on);
                            stopService(intent_bg);
                            stopService(intent_lc);
                            stopService(intent_mu);
                        }


                    }
                });
    }


}
