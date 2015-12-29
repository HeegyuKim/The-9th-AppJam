package com.appjam.yeemo;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    LinearLayout topLinear;

    ArrayList<String> titleArr;
    ArrayList<String> subtitleArr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        topLinear = (LinearLayout)findViewById(R.id.list_top_Linear);

        Intent intent = getIntent();
        titleArr = intent.getStringArrayListExtra("titleArr");
        subtitleArr = intent.getStringArrayListExtra("subtitleArr");

        for(int i=0; i<titleArr.size(); i++)
        {
            LinearLayout subLinear = new LinearLayout(this);
            LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            param.bottomMargin = 25;
            subLinear.setLayoutParams(param);
            
            TextView tv1 = new TextView(this);
            tv1.setTextSize(20.0f);
            TextView tv2 = new TextView(this);
            tv2.setTextColor(Color.parseColor("#82898f"));

            tv1.setText(titleArr.get(i));
            tv2.setText(subtitleArr.get(i));

            subLinear.addView(tv1);
            subLinear.addView(tv2);

            topLinear.addView(subLinear);

        }

    }
}
