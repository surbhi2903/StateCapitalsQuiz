package edu.uga.cs.statecapitalsquiz;

public class Quiz {

    private long id;
    private int quizNumber;
    private int quizScore;

    public Quiz() {
        this.id = -1;
    }

    public Quiz(int quizNumber, int quizScore) {
        this.id = -1;
        this.quizNumber = quizNumber;
        this.quizScore = quizScore;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getQuizNumber() {
        return quizNumber;
    }

    public void setQuizNumber(int quizNumber){
        this.quizNumber = quizNumber;
    }

    public int getQuizScore() {
        return quizScore;
    }

    public void setQuizScore(int quizScore){
        this.quizScore = quizScore;
    }
}
