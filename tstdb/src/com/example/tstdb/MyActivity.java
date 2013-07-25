package com.example.tstdb;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MyActivity extends Activity implements LoaderManager.LoaderCallbacks<Cursor> {
    private SimpleCursorAdapter adapter;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button btn= (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertDb();
                insertWithProvider();
            }
        });

        Button btn1=(Button)findViewById(R.id.button1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showData();
                showData2();
            }
        });

        ListView lv= (ListView) findViewById(R.id.listView);
        String[] from={"book"};
        int[] to={R.id.textView};
         adapter=new SimpleCursorAdapter(this,R.layout.item,null,from,to,0);
        lv.setAdapter(adapter);
        getLoaderManager().initLoader(0,null,this);

    }
    private void insertDb(){
        MyDbHelper dbh = new MyDbHelper(getApplicationContext());
        SQLiteDatabase db = dbh.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(DbContract.MyColumns.COLUMN_BOOK,"herrybotter"+randomString());
        db.insert(DbContract.MyColumns.TABLE_NAME, null, cv);
        db.close();
    }

    private void showData(){
        Uri uri=Uri.parse("content://com.example.tstdb.provider")  ;
        Cursor c=getContentResolver().query(uri,null,null,null,null);
        c.moveToFirst();
        do {
        String bookname=c.getString(c.getColumnIndex(DbContract.MyColumns.COLUMN_BOOK));
        Log.d("test", bookname);
        } while(c.moveToNext());
        c.close();
    }

    private void showData2(){
        Uri uri=Uri.parse("content://com.example.tstdb.provider")  ;
        Cursor c=getContentResolver().query(uri,null,null,null,null);
        adapter.swapCursor(c);

    }


    private void insertWithProvider(){
        Uri uri=Uri.parse("content://com.example.tstdb.provider");
        ContentValues cv=new ContentValues();
        cv.put("book","little cat"+randomString());
        Uri result=getContentResolver().insert(uri,cv);

    }

    private String randomString(){
        return ""+Math.round(Math.random()*1000 );
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        Uri uri=Uri.parse("content://com.example.tstdb.provider")  ;
        String[] columns={"book"};
        return new CursorLoader(this,uri,null,null,null,null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> objectLoader, Cursor o) {
        adapter.swapCursor(o);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> objectLoader) {
        adapter.swapCursor(null);
    }
}
