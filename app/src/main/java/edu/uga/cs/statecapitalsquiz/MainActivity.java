package edu.uga.cs.statecapitalsquiz;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Activity to represent splash screen.
 */
public class MainActivity extends AppCompatActivity {

    public static final String MESSAGE_TYPE = "edu.uga.cs.StateCapitalsQuiz.MESSAGE_TYPE";

    /**
     * Launches the layout.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        DBManager helper = new DBManager(getApplicationContext());
        SQLiteDatabase db = helper.getWritableDatabase();

        InputStream inputStream = null;
        try {
            inputStream = getResources().openRawResource(R.raw.state_capitals);
            BufferedReader buffer = new BufferedReader(new InputStreamReader(inputStream));
            ContentValues contentValues = new ContentValues();
            String line = "";
            String tableName = "States_Quiz";

            db.beginTransaction();
            while ((line = buffer.readLine()) != null) {
                String[] str = line.split(",");

                contentValues.put("state", str[0].trim());
                contentValues.put("city1", str[1].trim());
                contentValues.put("city2", str[2].trim());
                contentValues.put("city3", str[3].trim());

                db.insert(tableName, null, contentValues);
            }
            db.setTransactionSuccessful();
            db.endTransaction();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }

        Button button_csv = (Button) findViewById(R.id.button);
        button_csv.setOnClickListener(new QuizClickListener());
        Button results = (Button) findViewById(R.id.button2);
        results.setOnClickListener(new ScoreClickListener());
    }

    /**
     * Button click listener to launch a new quiz.
     */
    private class QuizClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(v.getContext(), QuizActivity.class);
            v.getContext().startActivity(intent);
        }
    }

    /**
     * Button click listener to view past quiz scores.
     */
    private class ScoreClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            //Create specific intents to start an activity.
            Intent quizResultsIntent = new Intent(v.getContext(), QuizResultActivity.class);
            v.getContext().startActivity(quizResultsIntent);
        }
    }
}

