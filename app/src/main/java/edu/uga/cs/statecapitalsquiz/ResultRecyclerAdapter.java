package edu.uga.cs.statecapitalsquiz;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Recycler view adapter to display the list of quizzes taken.
 */
public class ResultRecyclerAdapter extends RecyclerView.Adapter<ResultRecyclerAdapter.ResultHolder> {

    private List<Quiz> quizList;

    /**
     * Constructor holding the data to be represented.
     * @param quizList
     */
    public ResultRecyclerAdapter(List<Quiz> quizList) {this.quizList = quizList;}

    /**
     * Holds the items in the view.
     */
    class ResultHolder extends RecyclerView.ViewHolder {

        TextView result;

        public ResultHolder(View itemView) {
            super(itemView);


            result = (TextView) itemView.findViewById(R.id.item);
        }
    }

    /**
     * Inflate the layout that represents the Quiz Results.
     * @return
     */
    @Override
    public ResultHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.result_layout, null);
        return new ResultHolder(view);
    }

    /**
     * Attaches view items to the view holder.
     * @param holder the view to be changed
     * @param position position of the view
     */
    @Override
    public void onBindViewHolder(ResultHolder holder, int position) {
        Quiz quiz = quizList.get(position);

        StringBuilder sb = new StringBuilder();
        sb.append("Id        :").append(quiz.getId()).append("\n");
        sb.append("Date      :").append(quiz.getQuizDate()).append("\n");
        sb.append("Question1 :").append(quiz.getQ1()).append("\n");
        sb.append("Question2 :").append(quiz.getQ2()).append("\n");
        sb.append("Question3 :").append(quiz.getQ3()).append("\n");
        sb.append("Question4 :").append(quiz.getQ4()).append("\n");
        sb.append("Question5 :").append(quiz.getQ5()).append("\n");
        sb.append("Question6 :").append(quiz.getQ6()).append("\n");
        sb.append("Score     :").append(quiz.getCorrectAnswers());

        holder.result.setText(sb.toString());
    }

    /**
     * Gets the number of items in the RecyclerView
     * @return number of items in QuizList
     */
    @Override
    public int getItemCount() {return quizList.size();}
}
