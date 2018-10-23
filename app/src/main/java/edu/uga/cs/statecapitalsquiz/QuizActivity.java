package edu.uga.cs.statecapitalsquiz;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.List;

public class QuizActivity extends AppCompatActivity {

    public static final String DEBUG_TAG="QuizActivity";

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter recyclerAdapter;

    private QuizData quizData = null;
    private List<Quiz> quizList;
    private List<QuizQuestion> newQuiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        quizData = new QuizData(this);

        new RetrieveQuizTask().execute();
    }

    public class RetrieveQuizTask extends AsyncTask<Void, Void, List<QuizQuestion>> {

        @Override
        protected List<QuizQuestion> doInBackground(Void... params) {
            quizData.open();
            newQuiz = quizData.retrieveNewQuiz();
            return newQuiz;
        }

        @Override
        protected void onPostExecute(List<QuizQuestion> quizList) {
            super.onPostExecute(quizList);
            recyclerAdapter = new QuizRecyclerAdapter(quizList);
            recyclerView.setAdapter(recyclerAdapter);
        }
    }
    @Override
    protected void onResume() {
        Log.d(DEBUG_TAG, "");
        if (quizData != null)
            quizData.open();
        super.onResume();
    }

    protected void onPause() {
        Log.d(DEBUG_TAG,"");
        if (quizData != null)
            quizData.close();
        super.onPause();
    }

}

