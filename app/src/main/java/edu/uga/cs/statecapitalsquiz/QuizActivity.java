package edu.uga.cs.statecapitalsquiz;

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

        layoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false);


        recyclerView.setLayoutManager(layoutManager);



        final PagerSnapHelper snapHelper = new PagerSnapHelper();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    View view = snapHelper.findSnapView(layoutManager);
                    int pos = layoutManager.getPosition(view);
                    Log.e("Item position at: " ,""+ pos);

                }
            }
        });

        snapHelper.attachToRecyclerView(recyclerView);

        final ItemTouchHelper.SimpleCallback simpleTouch = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleTouch);
        itemTouchHelper.attachToRecyclerView(recyclerView);


        quizData = new QuizData(this);

        new RetrieveQuizTask().execute();
    }

    public void removeQuestion(int pos) {
        newQuiz.remove(pos);
        recyclerView.removeViewAt(pos);
        recyclerAdapter.notifyItemRemoved(pos);
        recyclerAdapter.notifyItemRangeChanged(pos, newQuiz.size());
        recyclerAdapter.notifyDataSetChanged();
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
