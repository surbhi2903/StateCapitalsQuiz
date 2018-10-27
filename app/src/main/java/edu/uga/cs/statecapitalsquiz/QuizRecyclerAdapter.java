package edu.uga.cs.statecapitalsquiz;

import android.content.Context;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Recycler view adapter to display a new QuizQuestion object.
 */
public class QuizRecyclerAdapter extends RecyclerView.Adapter<QuizRecyclerAdapter.QuizHolder> {

    public static final String DEBUG_TAG = "Quiz";

    private List<QuizQuestion> quizQuestions;
    private Context context;
    private RadioGroup lastCheckedRadioGroup = null;
    private Map<Integer,Boolean> answers = new HashMap<>();
    private boolean quizSubmitted = false;


    /**
     * Constructor to represents a list of QuizQuestions.
     * @param quizQuestions the list of quiz questions
     * @param ctx
     */
    public QuizRecyclerAdapter(List<QuizQuestion> quizQuestions, Context ctx) {
        context = ctx;
        this.quizQuestions = quizQuestions;
    }

    /**
     * Class to hold the objects represented in the RecyclerView
     */
    class QuizHolder extends RecyclerView.ViewHolder {

        TextView date;
        TextView stateName;
        RadioGroup radioGroup;
        RadioButton cityOne;
        RadioButton cityTwo;
        RadioButton cityThree;
        RadioButton selectedButton;
        Button button;
        int score = 0;
        QuizQuestion currentQuizQuestion;
        int currentQuestionNumber;


        /**
         * Constructor of QuizHolder class.
         * @param itemView the view to be returned
         */
        public QuizHolder(final View itemView) {
            super(itemView);

            date = (TextView) itemView.findViewById(R.id.date);
            stateName = (TextView) itemView.findViewById(R.id.stateName);
            radioGroup = (RadioGroup) itemView.findViewById(R.id.radio);

            cityOne = (RadioButton) itemView.findViewById(R.id.stateOne);
            cityTwo = (RadioButton) itemView.findViewById(R.id.stateTwo);
            cityThree = (RadioButton) itemView.findViewById(R.id.stateThree);

            button = (Button) itemView.findViewById(R.id.button);


            /**
             * Listener to detect the input provided by the user.
             */
            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    if (lastCheckedRadioGroup != null
                            && lastCheckedRadioGroup.getCheckedRadioButtonId()
                            != radioGroup.getCheckedRadioButtonId()
                            && lastCheckedRadioGroup.getCheckedRadioButtonId() != -1) {
                        lastCheckedRadioGroup.clearCheck();
                    }
                    lastCheckedRadioGroup = radioGroup;
                    //Log.d(DEBUG_TAG, "ID " + radioGroup.getCheckedRadioButtonId());

                    int selectedId = radioGroup.getCheckedRadioButtonId();
                    selectedButton = (RadioButton) itemView.findViewById(selectedId);
                    if( selectedButton != null) {
                        // By default correct answer is always option one.
                        if( selectedButton.getText().equals(currentQuizQuestion.getAnswerOne())){
                            answers.put(currentQuestionNumber,Boolean.TRUE);
                        } else {
                            answers.put(currentQuestionNumber,Boolean.FALSE);
                        }

                    }

                }
            });


        }
        public int getNumCorrect() {
            return score;
        }

    }

    @Override
    public QuizHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.quiz_question, null);
        return new QuizHolder(view);
    }


    /**
     * Sets each element in the view to the appropriate values, represnted by a QuizQuestion object.
     * @param holder The view to be set.
     * @param position The position of the view within the RecyclerView
     */
    @Override
    public void onBindViewHolder(QuizHolder holder, int position) {
        QuizQuestion quizQuestion = quizQuestions.get(position);
        holder.currentQuizQuestion = quizQuestion;
        holder.currentQuestionNumber = position + 1;
        int id = position;
        int score = 0;
        if (position != 5) {
            holder.button.setVisibility(View.GONE);
        }

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(quizSubmitted == false){
                    Quiz quiz = new Quiz();

                    Calendar calendar = Calendar.getInstance();
                    SimpleDateFormat mdformat = new SimpleDateFormat("yyyy / MM / dd ");
                    String strDate = mdformat.format(calendar.getTime());

                    int score = 0;
                    quiz.setQuizDate(strDate);

                    if(Boolean.TRUE.equals(answers.get(1))){
                        quiz.setQ1("true");
                        score += 1;
                    }else {
                        quiz.setQ1("false");
                    }

                    if(Boolean.TRUE.equals(answers.get(2))){
                        quiz.setQ2("true");
                        score += 1;
                    }else {
                        quiz.setQ2("false");
                    }

                    if(Boolean.TRUE.equals(answers.get(3))){
                        quiz.setQ3("true");
                        score += 1;
                    }else {
                        quiz.setQ3("false");
                    }

                    quiz.setQuizDate(strDate);
                    if(Boolean.TRUE.equals(answers.get(4))){
                        quiz.setQ4("true");
                        score += 1;
                    }else {
                        quiz.setQ4("false");
                    }

                    quiz.setQuizDate(strDate);
                    if(Boolean.TRUE.equals(answers.get(5))){
                        quiz.setQ5("true");
                        score += 1;
                    }else {
                        quiz.setQ5("false");
                    }

                    quiz.setQuizDate(strDate);
                    if(Boolean.TRUE.equals(answers.get(6))){
                        quiz.setQ6("true");
                        score += 1;
                    }else {
                        quiz.setQ6("false");
                    }

                    quiz.setCorrectAnswers(score);

                    QuizData quizData = new QuizData(context);
                    quizData.open();
                    quizData.storeQuiz(quiz);
                    quizData.close();
                    quizSubmitted = true;
                }else {
                    Toast.makeText(context, "Quiz already submitted - Go Back and start a new Quiz!", Toast.LENGTH_LONG).show();
                }

            }
        });

        if (holder.radioGroup.getCheckedRadioButtonId() == holder.cityOne.getId()) {
            Toast.makeText(context, "Please select Gender", Toast.LENGTH_SHORT).show();
        }

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("yyyy / MM / dd ");
        String strDate = "Current Date : " + mdformat.format(calendar.getTime());

        holder.date.setText(strDate);
        holder.stateName.setText("What is the capital of " + quizQuestion.getQuestion() + "?");
        holder.cityOne.setText(quizQuestion.getAnswerOne());
        holder.cityTwo.setText(quizQuestion.getAnswerTwo());
        holder.cityThree.setText(quizQuestion.getAnswerThree());

    }

    /**
     * Number of items in the RecyclerView
     * @return number of items in the view
     */
    @Override
    public int getItemCount() { return quizQuestions.size(); }

}
