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
import java.util.List;

public class QuizRecyclerAdapter extends RecyclerView.Adapter<QuizRecyclerAdapter.QuizHolder> {

    public static final String DEBUG_TAG = "Quiz";

    private List<QuizQuestion> quizQuestions;
    private Context context;
    private RadioGroup lastCheckedRadioGroup = null;


    public QuizRecyclerAdapter(List<QuizQuestion> quizQuestions, Context ctx) {
        context = ctx;
        this.quizQuestions = quizQuestions;
    }

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


        public QuizHolder(final View itemView) {
            super(itemView);

            date = (TextView) itemView.findViewById(R.id.date);
            stateName = (TextView) itemView.findViewById(R.id.stateName);
            radioGroup = (RadioGroup) itemView.findViewById(R.id.radio);

            cityOne = (RadioButton) itemView.findViewById(R.id.stateOne);
            cityTwo = (RadioButton) itemView.findViewById(R.id.stateTwo);
            cityThree = (RadioButton) itemView.findViewById(R.id.stateThree);

            button = (Button) itemView.findViewById(R.id.button);


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
                    Log.d(DEBUG_TAG, "ID " + radioGroup.getCheckedRadioButtonId());
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


    @Override
    public void onBindViewHolder(QuizHolder holder, int position) {
        QuizQuestion quizQuestion = quizQuestions.get(position);
        int id = position;
        int score = 0;
        if (position != 5) {
            holder.button.setVisibility(View.GONE);
        }
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Button Clicked", Toast.LENGTH_LONG).show();
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

    @Override
    public int getItemCount() { return quizQuestions.size(); }

}
