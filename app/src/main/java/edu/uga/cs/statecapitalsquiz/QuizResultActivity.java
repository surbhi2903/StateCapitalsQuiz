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

public class QuizResultActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter recyclerAdapter;


    private QuizData quizData = null;
    private List<Quiz> quizList;

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

    public class RetrieveQuizTask extends AsyncTask<Void, Void, List<Quiz>> {

        @Override
        protected List<Quiz> doInBackground(Void... params) {
            quizData.open();
            quizList = quizData.retrieveAllQuizzes();
            return quizList;
        }



        @Override
        protected void onPostExecute(List<Quiz> quizList) {
            super.onPostExecute(quizList);
            recyclerAdapter = new ResultRecyclerAdapter(quizList);
            recyclerView.setAdapter(recyclerAdapter);
        }
    }
    @Override
    protected void onResume() {
        if( quizData != null )
            quizData.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        if( quizData != null )
            quizData.close();
        super.onPause();
    }
}
