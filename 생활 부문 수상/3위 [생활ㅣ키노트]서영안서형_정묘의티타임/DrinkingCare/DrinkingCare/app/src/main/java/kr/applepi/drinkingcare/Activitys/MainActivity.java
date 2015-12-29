package kr.applepi.drinkingcare.Activitys;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import kr.applepi.drinkingcare.Adapters.MainAdapter;
import kr.applepi.drinkingcare.DBHelper;
import kr.applepi.drinkingcare.R;
import kr.applepi.drinkingcare.models.Drink;
import kr.applepi.drinkingcare.models.MainModel;
import kr.applepi.drinkingcare.services.MyService;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView mainList;
    private ArrayList<MainModel> arrayList;
    private MainAdapter mainAdapter;
    private CardView coffee, candy;
    private TextView mainTime, caffeineText, sugarText;
    private Intent service;
    private static double caffeine = 0, sugar = 0;

    private AlertDialog.Builder builder;
    private AlertDialog dialog;

    private List<Drink> list;

    public static Activity mainActivity;

    static public SQLiteDatabase db;

    private SharedPreferences mPref;
    private SharedPreferences.Editor editor;

    public static int time = 13680;

    Thread myThread;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            updateThread();
        }
    };

    @Override
    protected void onResume() {
        super.onResume();

        //카페인, 당분 판별
        if (caffeine >= 0.0 && caffeine < 100.0) {
            caffeineText.setText("안전");
            caffeineText.setTextColor(Color.parseColor("#6AFF66"));
        } else if (caffeine >= 100.0 && caffeine < 200.0) {
            caffeineText.setText("주의");
            caffeineText.setTextColor(Color.parseColor("#FFA664"));
        } else {
            caffeineText.setText("위험");
            caffeineText.setTextColor(Color.parseColor("#FF6464"));
        }

        if (sugar >= 0.0 && sugar < 100.0) {
            sugarText.setText("안전");
            sugarText.setTextColor(Color.parseColor("#6AFF66"));
        } else if (sugar >= 100.0 && sugar < 200.0) {
            sugarText.setText("주의");
            sugarText.setTextColor(Color.parseColor("#FFA664"));
        } else {
            sugarText.setText("위험");
            sugarText.setTextColor(Color.parseColor("#FF6464"));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = mPref.edit();

        builder = new AlertDialog.Builder(this);

        if (!mPref.getBoolean("tutorial", false)) {
            startActivity(new Intent(MainActivity.this, TutorialActivity.class));
            finish();
        }

        service = new Intent(this, MyService.class);
        startService(service);

        mainTime = (TextView) findViewById(R.id.main_time);

        myThread = new Thread(new Runnable() {
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

        myThread.start();

        caffeineText = (TextView) findViewById(R.id.coffee_text);
        sugarText = (TextView) findViewById(R.id.candy_text);

        mainActivity = this;

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AddActivity.class));
            }
        });

        DBHelper helper = new DBHelper(this);
        db = helper.getWritableDatabase();

        list = new ArrayList<Drink>();
        try {
            list = readDrinkDB();
        } catch (Exception e) {
            e.printStackTrace();
        }

        mainList = (ListView) findViewById(R.id.list_main);
        arrayList = new ArrayList<>();
        mainAdapter = new MainAdapter(this, R.layout.list_main, arrayList);

        mainList.setAdapter(mainAdapter);
        mainList.setOnItemClickListener(this);
        List <Integer> list2 = new ArrayList<Integer>();
        //초기화
        for(int i=0;i<8;i++)list2.add(0);
        for(int i=0; i<list.size(); i++)
        {
            if(list.get(i).type == 0)
            {
                list2.set(0, list2.get(0) + list.get(i).ml);
            }
            else if(list.get(i).type == 1)
            {
                list2.set(1, list2.get(1) + list.get(i).ml);
            }
            else if(list.get(i).type == 2)
            {
                list2.set(2, list2.get(2) + list.get(i).ml);
            }
            else if(list.get(i).type == 3)
            {
                list2.set(3, list2.get(3) + list.get(i).ml);
            }
            else if(list.get(i).type == 4)
            {
                list2.set(4, list2.get(4) + list.get(i).ml);
            }
            else if(list.get(i).type == 5)
            {
                list2.set(5, list2.get(5) + list.get(i).ml);
            }
            else if(list.get(i).type == 6)
            {
                list2.set(6, list2.get(6) + list.get(i).ml);
            }
            else
            {
                list2.set(7, list2.get(7) + list.get(i).ml);
            }
        }

        for(int i=0; i<list2.size(); i++)
        {
            if(list2.get(i) > 0)
            {
                if(i == 0)
                {
                    arrayList.add(new MainModel((BitmapDrawable) getResources().getDrawable(R.drawable.icon_water), "물", list2.get(i)+"ml"));
                }
                else if(i == 1)
                {
                    arrayList.add(new MainModel((BitmapDrawable) getResources().getDrawable(R.drawable.icon_soda), "탄산음료", list2.get(i)+"ml"));
                    caffeine += list2.get(i) * 0.1;
                    sugar += list2.get(i) * 0.1;
                }
                else if(i== 2)
                {
                    arrayList.add(new MainModel((BitmapDrawable) getResources().getDrawable(R.drawable.icon_ion), "이온음료", list2.get(i)+"ml"));
                    sugar += list2.get(i) * 0.0065;
                }
                else if(i == 3)
                {
                    arrayList.add(new MainModel((BitmapDrawable) getResources().getDrawable(R.drawable.icon_coffee), "커피", list2.get(i)+"ml"));
                    caffeine += list2.get(i) * 0.5;
                    sugar += list2.get(i) * 0.1;
                }
                else if(i == 4)
                {
                    arrayList.add(new MainModel((BitmapDrawable) getResources().getDrawable(R.drawable.icon_tea), "차", list2.get(i)+"ml"));
                    caffeine += list2.get(i) * 0.2;
                }
                else if(i == 5)
                {
                    arrayList.add(new MainModel((BitmapDrawable) getResources().getDrawable(R.drawable.icon_energy), "에너지 드링크", list2.get(i)+"ml"));
                    caffeine += list2.get(i) * 0.24;
                    sugar += list2.get(i) * 0.1;
                }
                else if(i == 6)
                {
                    arrayList.add(new MainModel((BitmapDrawable) getResources().getDrawable(R.drawable.icon_juice), "주스", list2.get(i)+"ml"));
                    sugar += list2.get(i) * 0.1;
                }
                else
                {
                    arrayList.add(new MainModel((BitmapDrawable) getResources().getDrawable(R.drawable.icon_cup), "기타", list2.get(i)+"ml"));
                }
            }
        }

        /*
        arrayList.add(new MainModel((BitmapDrawable) getResources().getDrawable(R.drawable.icon_water), "물", "2L"));
        arrayList.add(new MainModel((BitmapDrawable) getResources().getDrawable(R.drawable.icon_soda), "탄산음료", "2L"));
        arrayList.add(new MainModel((BitmapDrawable) getResources().getDrawable(R.drawable.icon_ion), "이온음료", "2L"));
        arrayList.add(new MainModel((BitmapDrawable) getResources().getDrawable(R.drawable.icon_coffee), "커피", "2L"));
        arrayList.add(new MainModel((BitmapDrawable) getResources().getDrawable(R.drawable.icon_tea), "차", "2L"));
        arrayList.add(new MainModel((BitmapDrawable) getResources().getDrawable(R.drawable.icon_energy), "에너지 드링크", "2L"));
        arrayList.add(new MainModel((BitmapDrawable) getResources().getDrawable(R.drawable.icon_juice), "주스", "2L"));
        arrayList.add(new MainModel((BitmapDrawable) getResources().getDrawable(R.drawable.icon_cup), "기타", "2L"));
        */

        coffee = (CardView) findViewById(R.id.main_coffee);
        candy = (CardView) findViewById(R.id.main_candy);
//        coffee.setBackgroundColor(Color.parseColor("#6AFF66"));
//        candy.setBackgroundColor(Color.parseColor("#6AFF66"));

        if (time <= 0) {
            builder.setTitle("소변 배출 시간 완료.")
                    .setMessage("실제로 소변 배출 욕구를 느끼셨나요?")
                    .setCancelable(false)
                    .setPositiveButton("네 화장실 갔다옴", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            dialog.dismiss();
                            Toast.makeText(getApplicationContext(), "이용해 주셔서 감사합니다~!", Toast.LENGTH_SHORT).show();
                            MyService.first = true;
                            time = 13680;
                            myThread.run();
                            startService(service);
                        }
                    })
                    .setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            dialog.dismiss();
                            Toast.makeText(getApplicationContext(), "저런! 아쉽네요~", Toast.LENGTH_SHORT).show();
                            MyService.first = true;
                            time = 600;
                            myThread.run();
                            startService(service);
                        }
                    });
            dialog = builder.create();
            dialog.show();
        }
    }

    private void updateThread() {
        String timeText = String.valueOf(time);
        mainTime.setText((time / 3600) + "시간 " + (time % 3600 / 60) + "분 " + (time % 3600 % 60) + "초");

        if (time <= 0) {
            time = 0;
            myThread.interrupt();
            stopService(service);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        startActivity(new Intent(MainActivity.this, SettingActivity.class));

        return super.onOptionsItemSelected(item);
    }

    public int readAgeDB() throws Exception {
        Cursor c = db.query("user", null, null, null, null, null, null);
        if (c.getCount() == 0) throw new Exception();
        c.moveToFirst();
        int age = c.getInt(0);
        c.close();
        return age;
    }

    public List<Drink> readDrinkDB() throws Exception {
        Cursor c = db.query("drink", null, null, null, null, null, null);
        List<Drink> list = new ArrayList<Drink>();
        if (c.getCount() == 0) throw new Exception();
        c.moveToFirst();
        for(int i=0; i<c.getCount(); i++)
        {
            long now = System.currentTimeMillis();
            Date date = new Date(now);
            SimpleDateFormat CurDateFormat = new SimpleDateFormat("yyyyMMdd");
            int strCurDate = Integer.valueOf(CurDateFormat.format(date));

            Drink drink = new Drink();

            if(strCurDate == c.getInt(2))
            {
                int type = c.getInt(0);
                int ml = c.getInt(1);
                drink.type = type;
                drink.ml = ml;
                list.add(drink);
            }
            c.moveToNext();
        }
        c.close();
        return list;
    }

}