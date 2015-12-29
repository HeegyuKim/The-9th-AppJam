package kr.applepi.drinkingcare.Activitys;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import kr.applepi.drinkingcare.Adapters.SettingAdapter;
import kr.applepi.drinkingcare.R;
import kr.applepi.drinkingcare.models.SettingModel;

public class SettingActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView settingList;
    private ArrayList<SettingModel> arrayList;
    private SettingAdapter settingAdapter;
    private SharedPreferences mPref;
    private SharedPreferences.Editor editor;
    private AlertDialog.Builder builder;
    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.action_settings);

        mPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = mPref.edit();

        builder = new AlertDialog.Builder(this);

        settingList= (ListView) findViewById(R.id.list_setting);
        arrayList = new ArrayList<>();
        settingAdapter = new SettingAdapter(this, R.layout.list_setting, arrayList);

        settingList.setAdapter(settingAdapter);
        settingList.setOnItemClickListener(this);

        arrayList.add(new SettingModel((BitmapDrawable) getResources().getDrawable(R.drawable.setting_notification),
                getResources().getString(R.string.setting_notification_title), getResources().getString(R.string.setting_notification_description), true));
        arrayList.add(new SettingModel((BitmapDrawable) getResources().getDrawable(R.drawable.setting_init),
                getResources().getString(R.string.setting_init_title), getResources().getString(R.string.setting_init_description), false));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 1:
                builder.setTitle("데이터 초기화")
                        .setMessage("모든 설정을 초기화하고 앱을 다시 시작합니다.")
                        .setCancelable(false)
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                editor.putBoolean("tutorial", false);
                                editor.commit();
                                Intent intent = new Intent(SettingActivity.this, TutorialActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);

                                dialog.dismiss();
                            }
                        })
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                dialog.dismiss();
                            }
                        });;
                dialog = builder.create();
                dialog.show();
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            default:
                finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
