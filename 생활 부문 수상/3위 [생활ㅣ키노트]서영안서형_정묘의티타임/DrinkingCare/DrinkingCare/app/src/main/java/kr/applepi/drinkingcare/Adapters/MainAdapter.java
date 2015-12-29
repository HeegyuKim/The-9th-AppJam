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
import android.widget.TextView;

import java.util.ArrayList;

import kr.applepi.drinkingcare.R;
import kr.applepi.drinkingcare.models.MainModel;

public class MainAdapter extends ArrayAdapter<MainModel> implements CompoundButton.OnCheckedChangeListener {
	private Context mContext;
	private int mLayoutResource;
	private ArrayList<MainModel> arrayList;
	private LayoutInflater mInflater;

	private SharedPreferences mPref;
	private SharedPreferences.Editor editor;

	public MainAdapter(Context context, int rowLayoutResource, ArrayList<MainModel> objects) {
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
	public MainModel getItem(int position) {
		return arrayList.get(position);
	}

	@Override
	public int getPosition(MainModel item) {
		return arrayList.indexOf(item);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if(convertView == null) {
			convertView = mInflater.inflate(mLayoutResource, null);
		}

		MainModel mainModel = getItem(position);

		if(mainModel != null) {
			ImageView icon = (ImageView) convertView.findViewById(R.id.main_iconTest);
			TextView title = (TextView) convertView.findViewById(R.id.main_title);
			TextView quantity = (TextView) convertView.findViewById(R.id.main_quantity);

			icon.setImageDrawable(mainModel.getIcon());
			title.setText(mainModel.getTitle());
			quantity.setText(mainModel.getQuantity());
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
