package edu.uga.cs.statecapitalsquiz;

/**
 * Class to represent a Quiz of six questions 
 */
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

    /**
     * Default constructor 
     */
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

    /**
     * Overloaded constructor 
     * @param quizDate the date of the quiz 
     * @param q1 question one 
     * @param q2 question two 
     * @param q3 question three 
     * @param q4 question four 
     * @param q5 question five 
     * @param q6 question six
     * @param correctAnswers number of correct answers 
     */
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

    /**
     * Get id of quiz ID in database. 
     */
    public long getId() {
        return id;
    }

    /**
     * Set ID of quiz. 
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Get quiz date.
     * @return date of quiz
     */
    public String getQuizDate() { return quizDate;}

    /**
     * Set date of quiz 
     * @param quizDate date of quiz
     */
    public void setQuizDate(String quizDate) { this.quizDate = quizDate;}

    /**
     * Get quiz question 
     * @return quiz question 
     */
    public String getQ1() { return q1;}

    /**
     * Set the  quiz question 
     */
    public void setQ1(String q1) { this.q1 = q1;}

    /**
     * Get quiz question 
     * @return quiz question 
     */
    public String getQ2() { return q2;}

    /**
     * Set the  quiz question 
     */
    public void setQ2(String q2) {this.q2 = q2;}

    /**
     * Get quiz question 
     * @return quiz question 
     */
    public String getQ3() { return q3;}

    /**
     * Set the  quiz question 
     */
    public void setQ3(String q3) { this.q3 = q3;}

    /**
     * Get quiz question 
     * @return quiz question 
     */
    public String getQ4() { return q4;}

    /**
     * Set the  quiz question 
     */
    public void setQ4(String q4) { this.q4 = q4;}

    /**
     * Get quiz question 
     * @return quiz question 
     */
    public String getQ5() { return q5;}

    /**
     * Set the  quiz question 
     */
    public void setQ5(String q5) {this.q5 = q5;}

    /**
     * Get quiz question 
     * @return quiz question 
     */
    public String getQ6() { return q6;}

    /**
     * Set the  quiz question 
     */
    public void setQ6(String q6) { this.q6 = q6;}

    /**
     * Get number of correct answers. 
     * @return number of correct answers 
     */
    public int getCorrectAnswers() { return correctAnswers;}

    /**
     * Set the number of correct answers. 
     */
    public void setCorrectAnswers(int correctAnswers) {this.correctAnswers = correctAnswers;}
}
