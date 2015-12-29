package will.wonderland;

import android.app.ActionBar;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by triti on 2015-12-19.
 * r1 o2 y3 □ b5
 □안에 들어갈 것은?


 2. a4
 3. x2
 4. k5
 5. g4

 10+1=11
 11-2=9
 3-8=7
 9-11=□


 2. 4
 3. 6
 4. 8
 5. 10
 */
public class LocjScreen extends ActionBarActivity{
    static  int anwser=0;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);


        Button Btn1 = (Button) findViewById(R.id.button1);
        Button Btn2 = (Button) findViewById(R.id.button2);
        Button Btn3 = (Button) findViewById(R.id.button3);
        Button Btn4 = (Button) findViewById(R.id.button4);

        TextView text1=(TextView)findViewById(R.id.text1);
        TextView text2=(TextView)findViewById(R.id.text2);
        TextView text3=(TextView)findViewById(R.id.text3);
        TextView text4=(TextView)findViewById(R.id.text4);

        TextView text =(TextView)findViewById(R.id.qu);
        text.setText("r1 o2 y3 □ b5\n" +
                "□안에 들어갈 것은?");

        text1.setText("1                  a4");
        text2.setText("2                  x2");
        text3.setText("3                  k5");
        text4.setText("4                  g4");


        Btn1.setOnClickListener(new btnOnClick());
        Btn2.setOnClickListener(new btnOnClick());
        Btn3.setOnClickListener(new btnOnClick());
        Btn4.setOnClickListener(new btnOnClick());


    }
    private class btnOnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = null;

            switch(v.getId()) {
                case R.id.button1 :
                    if(anwser==1)
                        finish();
                    break;
                case R.id.button2 :
                    if(anwser==2)
                        finish();
                    break;
                case R.id.button3 :
                    if(anwser==3)
                        finish();
                    break;
                case R.id.button4 :
                    if(anwser==4)
                        finish();
                    break;
            }

            startActivity(intent);
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                return true;

        }
        return super.onKeyDown(keyCode, event);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
