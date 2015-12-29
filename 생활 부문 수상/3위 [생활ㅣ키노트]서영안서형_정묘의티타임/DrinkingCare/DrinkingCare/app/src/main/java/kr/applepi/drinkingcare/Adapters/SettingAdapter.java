package kr.applepi.drinkingcare.Adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

import kr.applepi.drinkingcare.R;
import kr.applepi.drinkingcare.models.SettingModel;

public class SettingAdapter extends ArrayAdapter<SettingModel> implements CompoundButton.OnCheckedChangeListener {
	private Context mContext;
	private int mLayoutResource;
	private int position;
	private ArrayList<SettingModel> arrayList;
	private LayoutInflater mInflater;

	private SharedPreferences mPref;
	private SharedPreferences.Editor editor;
	private boolean notification;

	public SettingAdapter(Context context, int rowLayoutResource, ArrayList<SettingModel> objects) {
		super(context, rowLayoutResource, objects);
		this.mContext = context;
		this.mLayoutResource = rowLayoutResource;
		this.arrayList = objects;
		this.mInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		mPref = PreferenceManager.getDefaultSharedPreferences(context);
		editor = mPref.edit();
	}

	@Override
	public int getCount() {
		return arrayList.size();
	}

	@Override
	public SettingModel getItem(int position) {
		return arrayList.get(position);
	}

	@Override
	public int getPosition(SettingModel item) {
		return arrayList.indexOf(item);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		notification = mPref.getBoolean("setting_notification", true);

		if(convertView == null) {
			convertView = mInflater.inflate(mLayoutResource, null);
		}

		SettingModel setting = getItem(position);
		this.position = position;

		if(setting != null) {
			ImageView icon = (ImageView) convertView.findViewById(R.id.setting_icon);
			TextView title = (TextView) convertView.findViewById(R.id.setting_title);
			TextView description = (TextView) convertView.findViewById(R.id.setting_description);
			Switch settingSwitch = (Switch) convertView.findViewById(R.id.setting_switch);
			settingSwitch.setOnCheckedChangeListener(this);
			settingSwitch.setTag(position);

			icon.setImageDrawable(setting.getIcon());
			title.setText(setting.getTitle());
			description.setText(setting.getDescription());

			if (setting.getSwitchVisible()) {
				settingSwitch.setVisibility(View.VISIBLE);
				switch (position) {
					case 0: //진동
						settingSwitch.setChecked(notification);
						break;
				}
			} else {
				settingSwitch.setVisibility(View.GONE);
			}
		}

		return convertView;
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

		switch (String.valueOf(buttonView.getTag())) {
			case "0": //노티피케이션
				editor.putBoolean("setting_notification", isChecked);
				editor.commit();
				break;
		}
	}
}
