package com.example.tstdb;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.util.Log;

/**
 * Created with IntelliJ IDEA.
 * User: fangjian
 * Date: 13-7-25
 * Time: 下午9:17
 * To change this template use File | Settings | File Templates.
 */
public class MyProvider  extends ContentProvider{
    private SQLiteOpenHelper mydbh;
    private final static UriMatcher umatcher=new UriMatcher(UriMatcher.NO_MATCH);
    static{
        umatcher.addURI("com.example.tstdb.provider","hello",1);
        umatcher.addURI("com.example.tstdb.provider","hello/#",2);
    }


    @Override
    public boolean onCreate() {
        Log.d("test","provider create" );
        mydbh=new MyDbHelper(getContext());
        return true;
    }


    @Override
    public Cursor query(Uri uri, String[] strings, String s, String[] strings2, String s2) {
        Log.d("test","provider query" );
        SQLiteDatabase db = mydbh.getReadableDatabase();
        String[] cols={
                "_id",
                "book"
        }         ;
        Cursor c = db.query(DbContract.MyColumns.TABLE_NAME, cols, null, null, null, null,null);
        c.setNotificationUri(getContext().getContentResolver(),uri);
        return c;
    }

    @Override
    public String getType(Uri uri) {
        Log.d("test","get type" );

        int match=umatcher.match(uri);
        switch (match){
            case 1:return "vnd.android.cursor.dir/hello";
            case 2:return "vnd.android.cursor.item/hello";
            default:return null;
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        SQLiteDatabase db = mydbh.getWritableDatabase();
        long rowId= db.insert(DbContract.MyColumns.TABLE_NAME,null,contentValues);
        Uri nuri= ContentUris.withAppendedId(uri, rowId);
        getContext().getContentResolver().notifyChange(nuri,null);
        return nuri;
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
