package edu.uga.cs.statecapitalsquiz;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class DBManager extends SQLiteOpenHelper {
    static final String DATABASE_NAME = "states.db";
    public static final String TABLE_NAME = "States_Quiz";
    public static final String COLUMN_STATE = "State";
    public static final String COLUMN_CITY1 = "City 1";
    public static final String COLUMN_CITY2 = "City 2";
    public static final String COLUMN_CITY3 = "City 3";

    public static final String SQL_CREATE_STATES_TABLE = "CREATE TABLE " + TABLE_NAME +
            "(" + BaseColumns._ID + " INTEGER PRIMARY KEY, " +
            COLUMN_STATE + " REAL NOT NULL, " +
            COLUMN_CITY1 + " REAL NOT NULL, " +
            COLUMN_CITY2 + " REAL NOT NULL, " +
            COLUMN_CITY3 + " REAL NOT NULL );";

    public DBManager(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_STATES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
