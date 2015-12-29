package kr.applepi.drinkingcare.models;

import android.graphics.drawable.BitmapDrawable;

/**
 * Created by user on 2015-12-19.
 */
public class Product {
    BitmapDrawable icon;
    String text;
    public Product(BitmapDrawable icon, String text){
        this.icon = icon;
        this.text = text;
    }
    public BitmapDrawable getIcon(){
        return icon;
    }
    public String getText(){
        return text;
    }
}
