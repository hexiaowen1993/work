package com.example.administrator.yuekao0401.Databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2017/4/2.
 */

public class CreateDatabases extends SQLiteOpenHelper {
    public CreateDatabases(Context context) {
        super(context, "yuekao",null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
      String sql1="create table mychannel(_id Integer primary key autoincrement ,mytitle varchar(20))";
      String sql2="create table otherchannel(_id Integer primary key autoincrement ,othertitle varchar(20))";
        db.execSQL(sql1);
        db.execSQL(sql2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
