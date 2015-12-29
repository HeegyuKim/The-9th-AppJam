package kr.applepi.drinkingcare.Fragments;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import kr.applepi.drinkingcare.Activitys.AddActivity;
import kr.applepi.drinkingcare.Activitys.MainActivity;
import kr.applepi.drinkingcare.DBHelper;
import kr.applepi.drinkingcare.R;

/**
 * Created by user on 2015-12-19.
 */
public class AddFragment2 extends Fragment implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    EditText editText1;
    EditText editText2;
    ArrayList<String> arraylist;
    HashMap<String,Integer> map;
    Button button;
    int select=0;

    SQLiteDatabase db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add2, container, false);

        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        DBHelper helper = new DBHelper(this.getActivity());
        db = helper.getWritableDatabase();

        editText1 = (EditText)rootView.findViewById(R.id.editText);
        editText2 = (EditText)rootView.findViewById(R.id.editText2);
        button = (Button)rootView.findViewById(R.id.button);
        button.setOnClickListener(this);
        editText1.setText("0");
        editText2.setText("0");

        map = new HashMap<>();

        map.put("컵", 180);
        map.put("팩", 250);
        map.put("병", 640);
        map.put("캔", 250);
        map.put("잔", 50);
        map.put("큰 컵", 500);
        map.put("큰 팩", 1000);
        map.put("큰 병", 1800);
        map.put("큰 캔", 355);
        map.put("큰 잔", 350);
        map.put("용기종류", 0);

        arraylist = new ArrayList<>();
        arraylist.add("컵");
        arraylist.add("큰 컵");
        arraylist.add("팩");
        arraylist.add("큰 팩");
        arraylist.add("병");
        arraylist.add("큰 병");
        arraylist.add("캔");
        arraylist.add("큰 캔");
        arraylist.add("잔");
        arraylist.add("큰 잔");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_dropdown_item, arraylist);
        //스피너 속성
        Spinner sp = (Spinner) rootView.findViewById(R.id.spinner);
        sp.setPrompt("용기종류"); // 스피너 제목
        sp.setAdapter(adapter);
        sp.setOnItemSelectedListener(this);

        editText1.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                if (!editText1.getText().toString().equals("")) {
                    editText2.setText(String.valueOf(Integer.parseInt(editText1.getText().toString()) * map.get(arraylist.get(select))));
                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });
        return rootView;
    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
                               long arg3) {
        editText2.setText(String.valueOf(Integer.parseInt(editText1.getText().toString()) * map.get(arraylist.get(arg2))));
        select = arg2;
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onClick(View v) {
        if(v==button){

            long now = System.currentTimeMillis();
            Date date = new Date(now);
            SimpleDateFormat CurDateFormat = new SimpleDateFormat("yyyyMMdd");
            int strCurDate = Integer.valueOf(CurDateFormat.format(date));

            try {
                writeDrinkDB(AddActivity.drinkType, Integer.valueOf(editText2.getText().toString()), strCurDate);
            } catch (Exception e) {
                e.printStackTrace();
            }

            int addWater = Integer.valueOf(editText2.getText().toString());
            MainActivity.time -= ((addWater * 0.75) / 1.3) * 30;
            if (addWater < 0) {
                MainActivity.time = 100;
            }

            MainActivity.mainActivity.finish();
            startActivity(new Intent(getActivity(), MainActivity.class));
            getActivity().finish();
        }
    }

    public void writeDrinkDB(int type, int ml, int date) throws Exception {
        ContentValues values = new ContentValues();
        values.put("type", type);
        values.put("ml", ml);
        values.put("date", date);
        db.insert("drink", "", values);
    }
}
