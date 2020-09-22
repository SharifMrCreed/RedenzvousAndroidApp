package com.alle.san.restaurant.utilities;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.alle.san.restaurant.utilities.PersonContract;


public class PersonDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MatchScoreDatabase.db";
    public static final int DATABASE_VERSION = 1;

    public PersonDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String SQL_TABLE_CREATE_COMMAND = "CREATE TABLE "+ PersonContract.PersonTable.TABLE_NAME +
                " (" + PersonContract.PersonTable.ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                PersonContract.PersonTable.FULL_NAME + " TEXT NOT NULL, " +
                PersonContract.PersonTable.EMAIL + " TEXT NOT NULL, " +
                PersonContract.PersonTable.PASSWORD + " TEXT NOT NULL); ";
        sqLiteDatabase.execSQL(SQL_TABLE_CREATE_COMMAND);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
