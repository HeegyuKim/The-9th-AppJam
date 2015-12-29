package kr.applepi.drinkingcare.Fragments;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import kr.applepi.drinkingcare.Activitys.MainActivity;
import kr.applepi.drinkingcare.R;

/**
 * Created by user on 2015-12-19.
 */
public class TutorialFragment6 extends Fragment implements View.OnClickListener {

    Button btn;
    EditText editText;
    private SharedPreferences mPref;
    private SharedPreferences.Editor editor;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tutorial6, container, false);


        mPref = PreferenceManager.getDefaultSharedPreferences(getActivity());
        editor = mPref.edit();

        editText = (EditText) rootView.findViewById(R.id.tutorial_editText);
        btn = (Button) rootView.findViewById(R.id.tutorial_button);
        btn.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        if (editText.getText().toString().length() == 0) {
            Toast.makeText(getActivity(), "나이를 입력해주세요.", Toast.LENGTH_SHORT).show();
        } else {
            editor.putBoolean("tutorial", true);
            editor.putString("age", editText.getText().toString());
            editor.commit();
            try {
                writeAgeDB(Integer.valueOf(editText.getText().toString()));
            } catch (Exception e) {
                e.printStackTrace();
            }

            startActivity(new Intent(getActivity(), MainActivity.class));
            getActivity().finish();
        }
    }

    public void writeAgeDB(int age) throws Exception {
        ContentValues values = new ContentValues();
        values.put("age", age);
        int colNum = MainActivity.db.update("user", values, null, null);
        if(colNum==0)
        {
            MainActivity.db.insert("user", "", values);
            Toast.makeText(getContext(), readAgeDB() + "", Toast.LENGTH_SHORT).show();
        }
    }

    public int readAgeDB() throws Exception {
        Cursor c = MainActivity.db.query("user", null, null, null, null, null, null);
        if (c.getCount() == 0) throw new Exception();
        c.moveToFirst();
        int age = c.getInt(0);
        c.close();
        return age;
    }
}
