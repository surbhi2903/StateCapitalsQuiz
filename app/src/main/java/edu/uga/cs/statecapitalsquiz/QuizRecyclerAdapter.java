
package edu.uga.cs.statecapitalsquiz;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.List;

public class QuizRecyclerAdapter extends RecyclerView.Adapter<QuizRecyclerAdapter.QuizHolder> {

    public static final String DEBUG_TAG = "Quiz";

    private List<QuizQuestion> quizQuestions;

    public QuizRecyclerAdapter(List<QuizQuestion> quizQuestions) { this.quizQuestions = quizQuestions;}

    class QuizHolder extends RecyclerView.ViewHolder {

        TextView stateName;
        RadioGroup radioGroup;
        RadioButton cityOne;
        RadioButton cityTwo;
        RadioButton cityThree;
        RadioButton selectedButton;
        Button button;

        public QuizHolder(final View itemView) {
            super(itemView);

            stateName = (TextView) itemView.findViewById(R.id.stateName);
            radioGroup = (RadioGroup) itemView.findViewById(R.id.radio);
            cityOne = (RadioButton) itemView.findViewById(R.id.stateOne);
            cityTwo = (RadioButton) itemView.findViewById(R.id.stateTwo);
            cityThree = (RadioButton) itemView.findViewById(R.id.stateThree);
            button = (Button) itemView.findViewById(R.id.button);

            button.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    String checkAnswer;
                    int selectedId = radioGroup.getCheckedRadioButtonId();
                    selectedButton = (RadioButton) itemView.findViewById(selectedId);
                    if(selectedButton == cityOne) {
                        checkAnswer = "true";
                    } else {
                        checkAnswer = "false";
                    }

                }
            });
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

        holder.stateName.setText("What is the capital of " + quizQuestion.getQuestion() + "?");
        holder.cityOne.setText(quizQuestion.getAnswerOne());
        holder.cityTwo.setText(quizQuestion.getAnswerTwo());
        holder.cityThree.setText(quizQuestion.getAnswerThree());
    }

    @Override
    public int getItemCount() { return quizQuestions.size(); }

}
