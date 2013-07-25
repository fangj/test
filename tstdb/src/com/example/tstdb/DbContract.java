package com.example.tstdb;

import android.provider.BaseColumns;

/**
 * Created with IntelliJ IDEA.
 * User: fangjian
 * Date: 13-7-25
 * Time: 下午8:21
 * To change this template use File | Settings | File Templates.
 */
public class DbContract {
    private DbContract(){};
    public static class MyColumns implements BaseColumns {
        public static String TABLE_NAME="hello" ;
            final  public static String COLUMN_BOOK="book";
    }
}
