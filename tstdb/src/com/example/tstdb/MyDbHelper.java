package com.example.tstdb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created with IntelliJ IDEA.
 * User: fangjian
 * Date: 13-7-25
 * Time: 下午8:24
 * To change this template use File | Settings | File Templates.
 */
public class MyDbHelper  extends SQLiteOpenHelper{

    public static final int version=1;
    public static final String DB_NAME="mydb";
    public static final String create_sql="create table "+DbContract.MyColumns.TABLE_NAME+"(_id INTEGER PRIMARY KEY," +
            "book varchar(32)" +
            ")";

    public MyDbHelper(Context context) {
        super(context, DB_NAME, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(create_sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
