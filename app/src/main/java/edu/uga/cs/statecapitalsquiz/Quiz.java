package edu.uga.cs.statecapitalsquiz;

public class Quiz {

    private long id;
    private String quizDate;
    private String q1;
    private String q2;
    private String q3;
    private String q4;
    private String q5;
    private String q6;
    private int correctAnswers;

    public Quiz() {
        this.id = -1;
        this.quizDate = null;
        this.q1 = null;
        this.q2 = null;
        this.q3 = null;
        this.q4 = null;
        this.q5 = null;
        this.q6 = null;
    }

    public Quiz(String quizDate, String q1, String q2, String q3, String q4, String q5, String q6, int correctAnswers) {
        this.id = -1;
        this.quizDate = quizDate;
        this.q1 = q1;
        this.q2 = q2;
        this.q3 = q3;
        this.q4 = q4;
        this.q5 = q5;
        this.q6 = q6;
        this.correctAnswers = correctAnswers;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getQuizDate() { return quizDate;}

    public void setQuizDate(String quizDate) { this.quizDate = quizDate;}

    public String getQ1() { return q1;}

    public void setQ1(String q1) { this.q1 = q1;}

    public String getQ2() { return q2;}

    public void setQ2(String q2) {this.q2 = q2;}

    public String getQ3() { return q3;}

    public void setQ3(String q3) { this.q3 = q3;}

    public String getQ4() { return q4;}

    public void setQ4() { this.q4 = q4;}

    public String getQ5() { return q5;}

    public void setQ5(String q5) {this.q5 = q5;}

    public String getQ6() { return q6;}

    public void setQ6(String q6) { this.q6 = q6;}

    public int getCorrectAnswers() { return correctAnswers;}

    public void setCorrectAnswers(int correctAnswers) {this.correctAnswers = correctAnswers;}
}
