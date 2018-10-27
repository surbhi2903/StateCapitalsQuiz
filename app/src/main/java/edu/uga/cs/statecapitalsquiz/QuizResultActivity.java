package edu.uga.cs.statecapitalsquiz;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.List;

/**
 * Activity for the results of quizzes.
 */
public class QuizResultActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter recyclerAdapter;


    private QuizData quizData = null;
    private List<Quiz> quizList;

    /**
     * Returns the layout
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_result);
        recyclerView = (RecyclerView)findViewById(R.id.rView);
        layoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        quizData = new QuizData(this);
        new RetrieveQuizTask().execute();
    }

    /**
     * Class to connect to and retrieve values from the database, using the QuizData class
     */
    public class RetrieveQuizTask extends AsyncTask<Void, Void, List<Quiz>> {

        /**
         * Runs on start of activity.
         * @return
         */
        @Override
        protected List<Quiz> doInBackground(Void... params) {
            quizData.open();
            quizList = quizData.retrieveAllQuizzes();
            return quizList;
        }


        /**
         * Method is called after the database query is executed.
         * @param quizList the list represented all quiz results
         */
        @Override
        protected void onPostExecute(List<Quiz> quizList) {
            super.onPostExecute(quizList);
            recyclerAdapter = new ResultRecyclerAdapter(quizList);
            recyclerView.setAdapter(recyclerAdapter);
        }
    }

    /**
     * Opens the database if application was previously closed.
     */
    @Override
    protected void onResume() {
        if( quizData != null )
            quizData.open();
        super.onResume();
    }

    /**
     * Closes the database if application is paused.
     */
    @Override
    protected void onPause() {
        if( quizData != null )
            quizData.close();
        super.onPause();
    }
}
