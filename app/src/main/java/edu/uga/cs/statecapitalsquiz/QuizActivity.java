package edu.uga.cs.statecapitalsquiz;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.List;

public class QuizActivity extends AppCompatActivity {

    public static final String DEBUG_TAG="QuizActivity";

    private TextView quizQuestion;
    private RadioGroup answerChoices;
    private RadioButton answerOne;
    private RadioButton answerTwo;
    private RadioButton answerThree;

    private QuizData quizData = null;

    private List<Quiz> quizList;
    private QuizQuestion newQuiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        quizQuestion = (TextView) findViewById(R.id.questionField);
        answerChoices = (RadioGroup) findViewById(R.id.answerGroup);
        answerOne = (RadioButton) findViewById(R.id.answer1);
        answerTwo = (RadioButton) findViewById(R.id.answer2);
        answerThree = (RadioButton) findViewById(R.id.answer3);

        quizData = new QuizData(this);
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

        }
    }
}
