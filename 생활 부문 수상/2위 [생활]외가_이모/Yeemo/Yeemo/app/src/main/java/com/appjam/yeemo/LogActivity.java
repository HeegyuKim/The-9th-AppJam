package com.appjam.yeemo;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class LogActivity extends AppCompatActivity {

    DBManager dbMgr;
    SQLiteDatabase db;
    Cursor cursor;

    ListView list;

    int dview, time, x, y;
    String dtitle, dsubtitle;
    LinearLayout topLinear;
    ArrayList<String> arrList;

    int pos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        topLinear = (LinearLayout)findViewById(R.id.log_top_linear);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.log_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(LogActivity.this, "추가됩니다", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LogActivity.this, MainActivity.class);
                intent.putExtra("title", dtitle);
                intent.putExtra("subtitle", dsubtitle);
                intent.putExtra("x", x);
                intent.putExtra("y", y);
                intent.putExtra("time", time);
                intent.putExtra("view", dview);
                startActivity(intent);

                arrList.remove(pos);
                list.clearChoices();
            }
        });

        arrList = new ArrayList<String>();
        ArrayAdapter<String> adapter =  new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, arrList);

        list = new ListView(this);
        list.setAdapter(adapter);
        list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        list.setDivider(new ColorDrawable(Color.TRANSPARENT));
        list.setDividerHeight(20);
        list.setPadding(0, 20, 0, 20);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                pos = i;
            }
        });
        topLinear.addView(list);

        try{

            dbMgr = new DBManager(this);
            db = dbMgr.getReadableDatabase();
            cursor = db.query("DeleteYeemo", null, "title is not null", null,
                    null, null, "title asc");

            while(cursor.moveToNext())
            {
                dview = cursor.getInt(cursor.getColumnIndex("type"));
                time = cursor.getInt(cursor.getColumnIndex("time"));
                x = cursor.getInt(cursor.getColumnIndex("x"));
                y = cursor.getInt(cursor.getColumnIndex("y"));
                dtitle = cursor.getString(cursor.getColumnIndex("title"));
                dsubtitle = cursor.getString(cursor.getColumnIndex("subTitle"));

                arrList.add(dtitle);

                list.clearChoices();
                adapter.notifyDataSetChanged();

            }

        }catch (SQLiteException e){
            e.printStackTrace();
        }
    }

}
