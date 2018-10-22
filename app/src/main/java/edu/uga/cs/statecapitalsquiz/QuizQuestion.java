package edu.uga.cs.statecapitalsquiz;

public class QuizQuestion {

    private long id;
    private String question;
    private String answerOne;
    private String answerTwo;
    private String answerThree;

    public QuizQuestion() {
        this.id = -1;
        this.question = null;
        this.answerOne = null;
        this.answerTwo = null;
        this.answerThree = null;
    }

    public QuizQuestion(String question, String answersOne, String answerTwo, String answerThree) {
        this.id = -1;
        this.question = question;
        this.answerOne = answersOne;
        this.answerTwo = answerTwo;
        this.answerThree = answerThree;
    }

    public long getId() {return id;}

    public void setId(long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswerOne() {
        return answerOne;
    }

    public void setAnswerOne(String answerOne) {
        this.answerOne = answerOne;
    }

    public String getAnswerTwo() {
        return answerTwo;
    }

    public void setAnswerTwo(String answerTwo) {
        this.answerTwo = answerTwo;
    }

    public String getAnswerThree() {
        return answerThree;
    }

    public void setAnswerThree(String answerThree) {
        this.answerThree = answerThree;
    }

}
