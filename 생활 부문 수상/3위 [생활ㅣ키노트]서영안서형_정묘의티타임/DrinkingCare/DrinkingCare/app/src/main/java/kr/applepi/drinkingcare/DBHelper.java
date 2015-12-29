package kr.applepi.drinkingcare;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by SeungYoub on 15. 12. 20..
 */
public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context c) {
        super(c, "DrinkingCare.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table user (age int)");
        db.execSQL("create table drink (type int, ml int, date int)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists user");
        db.execSQL("drop table if exists drink");
        onCreate(db);
    }
}