package edu.uga.cs.statecapitalsquiz;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ResultRecyclerAdapter extends RecyclerView.Adapter<ResultRecyclerAdapter.ResultHolder> {

    private List<Quiz> quizList;

    public ResultRecyclerAdapter(List<Quiz> quizList) {this.quizList = quizList;}

    class ResultHolder extends RecyclerView.ViewHolder {

        TextView result;

        public ResultHolder(View itemView) {
            super(itemView);


            result = (TextView) itemView.findViewById(R.id.item);
        }
    }

    @Override
    public ResultHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.result_layout, null);
        return new ResultHolder(view);
    }

    @Override
    public void onBindViewHolder(ResultHolder holder, int position) {
        Quiz quiz = quizList.get(position);

        String resultString = "Date: " + quiz.getQuizDate() + "; Score = " + quiz.getCorrectAnswers();
      //  holder.result.setText(resultString);
        holder.result.setText("test");
    }

    @Override
    public int getItemCount() {return quizList.size();}
}
