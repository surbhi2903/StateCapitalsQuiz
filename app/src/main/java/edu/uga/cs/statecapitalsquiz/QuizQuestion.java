package edu.uga.cs.statecapitalsquiz;

/**
 * Class to represent a quiz question. 
 */
public class QuizQuestion {

    private long id;
    private String question;
    private String answerOne;
    private String answerTwo;
    private String answerThree;

    /**
     * Default constructor 
     */
    public QuizQuestion() {
        this.id = -1;
        this.question = null;
        this.answerOne = null;
        this.answerTwo = null;
        this.answerThree = null;
    }

    /**
     * Constructor to create a quiz question 
     * @param question The state name 
     * @param answersOne The first answer choice 
     * @param answerTwo The second answer choice 
     * @param answerThree The third answer choice 
     */
    public QuizQuestion(String question, String answersOne, String answerTwo, String answerThree) {
        this.id = -1;
        this.question = question;
        this.answerOne = answersOne;
        this.answerTwo = answerTwo;
        this.answerThree = answerThree;
    }

    /**
     * Get ID of quiz question in database. 
     * @return the ID of the quiz quesiton 
     */
    public long getId() {return id;}

    /**
     * Set the ID of the QuizQuestion 
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Get the quiz question 
     * @return the quiz question state name
     */
    public String getQuestion() {
        return question;
    }

    /**
     * Set the quiz question 
     */
    public void setQuestion(String question) {
        this.question = question;
    }

    /**
     * Get the first answer choice 
     */
    public String getAnswerOne() {
        return answerOne;
    }

    /**
     * Set the first answer choice 
     */
    public void setAnswerOne(String answerOne) {
        this.answerOne = answerOne;
    }

    /**
     * Get the second answer choice 
     * @return the second answer choice 
     */
    public String getAnswerTwo() {
        return answerTwo;
    }

    /**
     * Set the second answer choice 
     */
    public void setAnswerTwo(String answerTwo) {
        this.answerTwo = answerTwo;
    }

    /**
     * Get the third answer choice 
     * @return the third answer choice 
     */
    public String getAnswerThree() {
        return answerThree;
    }

    /**
     * Set the third answer choice 
     */
    public void setAnswerThree(String answerThree) {
        this.answerThree = answerThree;
    }

}

