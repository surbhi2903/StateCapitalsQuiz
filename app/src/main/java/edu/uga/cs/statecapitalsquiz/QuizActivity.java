package edu.uga.cs.statecapitalsquiz;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class QuizActivity extends AppCompatActivity {

    public static final String DEBUG_TAG="QuizActivity";

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter recyclerAdapter;


    @Override
    public Context getApplicationContext() {
        return super.getApplicationContext();
    }

    private QuizData quizData = null;
    private Quiz quizResult;
    private List<QuizQuestion> newQuiz;
    private TextView stateName;
    private RadioButton cityOne;
    private RadioButton cityTwo;
    private RadioButton cityThree;
    private RadioGroup answerGroup;
    private int numCorrect = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        layoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false);

        stateName = (TextView) findViewById(R.id.stateName);
        answerGroup = (RadioGroup) findViewById(R.id.radio);


        recyclerView.setLayoutManager(layoutManager);
        final PagerSnapHelper snapHelper = new PagerSnapHelper();

        snapHelper.attachToRecyclerView(recyclerView);
        quizData = new QuizData(this);
        new RetrieveQuizTask().execute();
    }



    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {

        }
    }

    public class RetrieveQuizTask extends AsyncTask<Void, Void, List<QuizQuestion>> {

        // This method will run as a background process to write into db.
        @Override
        protected List<QuizQuestion> doInBackground(Void... params) {
            quizData.open();
            newQuiz = quizData.retrieveNewQuiz();
            return newQuiz;
        }


        // This method will be automatically called by Android once the db writing
        // background process is finished.
        @Override
        protected void onPostExecute(List<QuizQuestion> quizList) {
            super.onPostExecute(quizList);
            recyclerAdapter = new QuizRecyclerAdapter(quizList, getApplicationContext());
            recyclerView.setAdapter(recyclerAdapter);
        }
    }

    private class CreateQuizTask extends AsyncTask<Quiz, Void, Quiz> {

        @Override
        protected Quiz doInBackground(Quiz... quiz) {
            quizData.storeQuiz(quiz[0]);
            return quiz[0];
        }

        @Override
        protected void onPostExecute(Quiz quiz) {
            super.onPostExecute(quiz);
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
