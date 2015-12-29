package com.appjam.yeemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by seo5220 on 15. 9. 15.
 */
public class DBManager extends SQLiteOpenHelper {

    public DBManager(Context context){
        super(context, "YeemoDB", null, 2);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table IncreaseYeemo(" +
                "title text," +
                "subTitle text," +
                "time int," +
                "x int," +
                "y int," +
                "type int"+
                ");");

        db.execSQL("create table DeleteYeemo(" +
                "title text," +
                "subTitle text," +
                "time int," +
                "x int," +
                "y int," +
                "type int"+
                ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS MyTable");
        onCreate(db);
    }
}
