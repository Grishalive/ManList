package com.grishalive.manlist;

import android.provider.BaseColumns;

public abstract class QueryStrings implements BaseColumns {
    public static final String TABLE_NAME = "entry";
    public static final String COLUMN_NAME_NAME = "name";
    public static final String COLUMN_NAME_DATE = "date";
    public static final String COLUMN_NAME_PHONE = "phone";
    public static final String TEXT_TYPE = " TEXT";
    public static final String COMMA_SEP = ",";
    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + QueryStrings.TABLE_NAME + " (" +
                    QueryStrings._ID + " INTEGER PRIMARY KEY," +
                    QueryStrings.COLUMN_NAME_NAME + TEXT_TYPE + COMMA_SEP +
                    QueryStrings.COLUMN_NAME_DATE + TEXT_TYPE + COMMA_SEP +
                    QueryStrings.COLUMN_NAME_PHONE + TEXT_TYPE +
                    " );";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + QueryStrings.TABLE_NAME;
}


