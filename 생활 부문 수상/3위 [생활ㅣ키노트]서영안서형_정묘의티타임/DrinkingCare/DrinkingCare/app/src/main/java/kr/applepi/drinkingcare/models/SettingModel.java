package kr.applepi.drinkingcare.models;

import android.graphics.drawable.BitmapDrawable;

public class SettingModel {
	private BitmapDrawable icon;
	private String title;
	private String description;
	private boolean switchVisible;

	public SettingModel(BitmapDrawable icon, String title, String description, boolean switchVisible) {
		this.icon = icon;
		this.title = title;
		this.description = description;
		this.switchVisible = switchVisible;
	}

	public String getTitle() {
		return this.title;
	}
	public String getDescription()
	{
		return this.description;
	}
	public BitmapDrawable getIcon()
	{
		return this.icon;
	}
	public boolean getSwitchVisible() {
		return this.switchVisible;
	}
}