package kr.applepi.drinkingcare.models;

import android.graphics.drawable.BitmapDrawable;

public class MainModel {
	private BitmapDrawable icon;
	private String title;
	private String quantity;

	public MainModel(BitmapDrawable icon, String title, String quantity) {
		this.icon = icon;
		this.title = title;
		this.quantity = quantity;
	}

	public String getTitle() {
		return this.title;
	}
	public String getQuantity()
	{
		return this.quantity;
	}
	public BitmapDrawable getIcon()
	{
		return this.icon;
	}
}
