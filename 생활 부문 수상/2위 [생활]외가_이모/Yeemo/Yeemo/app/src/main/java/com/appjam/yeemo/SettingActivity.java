package com.appjam.yeemo;

import android.content.Intent;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;

/**
 * Created by syoun_000 on 2015-12-19.
 */
public class SettingActivity extends PreferenceActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.setting);

        Intent intent = new Intent(this, DeveloperActivity.class);
        Preference p1 = findPreference("prefUsername");
        p1.setIntent(intent);

      /*  Intent intent2 = new Intent(this, AccountActivity.class);
        Preference p2 = findPreference("prefAccount");
        p2.setIntent(intent2);*/

        Intent intent3 = new Intent(this, AccountActivity.class);
        Preference p3 = findPreference("prefNotification");
        p3.setIntent(intent3);

        Intent intent4 = new Intent(this, LogActivity.class);
        Preference p4 = findPreference("prefLog");
        p4.setIntent(intent4);

    }
}
