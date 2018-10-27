package edu.uga.cs.statecapitalsquiz;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Class used to create and manage the SQLite Database.
 */
public class DBManager extends SQLiteOpenHelper {
    private static final String DEBUG_TAG = "JobLeadsDBHelper";
    static final String DATABASE_NAME = "states.db";
    private static final int DB_VERSION = 1;

    public static final String TABLE_NAME_STATES_QUIZ = "States_Quiz";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_STATE = "state";
    public static final String COLUMN_CITY1 = "city1";
    public static final String COLUMN_CITY2 = "city2";
    public static final String COLUMN_CITY3 = "city3";

    public static final String TABLE_NAME_STORE_QUIZ = "Store_Quiz";
    public static final String COLUMN_QUIZ_ID = "quizId";
    public static final String COLUMN_QUIZ_DATE = "quizDate";
    public static final String COLUMN_QUESTION1 = "question1";
    public static final String COLUMN_QUESTION2 = "question2";
    public static final String COLUMN_QUESTION3 = "question3";
    public static final String COLUMN_QUESTION4 = "question4";
    public static final String COLUMN_QUESTION5 = "question5";
    public static final String COLUMN_QUESTION6 = "question6";
    public static final String COLUMN_CORRECT_ANSWERS = "correctAnswers";

    private static DBManager helperInstance;

    public static final String CREATE_STATES_TABLE =
            "create table " + TABLE_NAME_STATES_QUIZ + " ("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COLUMN_STATE + " TEXT, "
                    + COLUMN_CITY1 + " TEXT, "
                    + COLUMN_CITY2 + " TEXT, "
                    + COLUMN_CITY3 + " TEXT"
                    + ")";

    public static final String CREATE_STORE_QUIZ_TABLE =
            "create table " + TABLE_NAME_STORE_QUIZ + " ("
                    + COLUMN_QUIZ_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COLUMN_QUIZ_DATE + " TEXT, "
                    + COLUMN_QUESTION1 + " TEXT, "
                    + COLUMN_QUESTION2 + " TEXT, "
                    + COLUMN_QUESTION3 + " TEXT, "
                    + COLUMN_QUESTION4 + " TEXT, "
                    + COLUMN_QUESTION5 + " TEXT, "
                    + COLUMN_QUESTION6 + " TEXT, "
                    + COLUMN_CORRECT_ANSWERS + " INTEGER"
                    + ")";

     /**
     * Constructor
     * @param context
     */
    public DBManager(Context context) {
        super(context, DATABASE_NAME, null, DB_VERSION);
    }
    
    /**
     * Returns an instance of a DBManager object. 
     * @param context
     * @return DBManager object 
     */
    public static synchronized DBManager getInstance( Context context ) {
        if( helperInstance == null ) {
            helperInstance = new DBManager( context.getApplicationContext() );
        }
        return helperInstance;
    }

    /**
     * Creates the database tables.
     * @param sqLiteDatabase the database connector
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_STATES_QUIZ);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_STORE_QUIZ);

        sqLiteDatabase.execSQL(CREATE_STATES_TABLE);
        Log.d( DEBUG_TAG, "Table " + TABLE_NAME_STATES_QUIZ + " created" );
        sqLiteDatabase.execSQL(CREATE_STORE_QUIZ_TABLE);
        Log.d( DEBUG_TAG, "Table " + TABLE_NAME_STORE_QUIZ + " created" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }


}
