package com.example.administrator.toutiao.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Created by Administrator on 2017/3/21.
 */

public class ShouyeDatabase extends SQLiteOpenHelper{

    public ShouyeDatabase(Context context) {
        super(context, "toutiao", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
           String sql="create  table shouye(_id Integer primary key autoincrement,title varchar(20),pic varchar(20),webUrl varchar(20)) ";
            db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }




}
