package edu.uga.cs.statecapitalsquiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class QuizData {

    public static final String DEBUG_TAG = "QuizData";

    private SQLiteDatabase db;
    private SQLiteOpenHelper quizDbHelper;
    private static final String[] allColumns = {
            DBManager.COLUMN_QUIZ_ID,
            DBManager.COLUMN_QUIZ_DATE,
            DBManager.COLUMN_QUESTION1,
            DBManager.COLUMN_QUESTION2,
            DBManager.COLUMN_QUESTION3,
            DBManager.COLUMN_QUESTION4,
            DBManager.COLUMN_QUESTION5,
            DBManager.COLUMN_QUESTION6,
            DBManager.COLUMN_CORRECT_ANSWERS
    };

    private static final String[] allParams = {
            DBManager.COLUMN_ID,
            DBManager.COLUMN_STATE,
            DBManager.COLUMN_CITY1,
            DBManager.COLUMN_CITY2,
            DBManager.COLUMN_CITY3
    };

    public QuizData(Context context) {
        this.quizDbHelper = DBManager.getInstance(context);
    }

    public void open() {
        db = quizDbHelper.getWritableDatabase();
    }

    public void close() {
        if (quizDbHelper != null) {
            quizDbHelper.close();
        }
    }

    public List<QuizQuestion> retrieveNewQuiz() {
        ArrayList<QuizQuestion> questions = new ArrayList<>();
        Cursor cursor = null;
        try {
            cursor = db.query(DBManager.TABLE_NAME_STATES_QUIZ, allParams,
                    null, null, null, null, "RANDOM() limit 1");
            while (cursor.moveToNext()) {
                long id = cursor.getLong(cursor.getColumnIndex(DBManager.COLUMN_ID));
                String questionState = cursor.getString(cursor.getColumnIndex(DBManager.COLUMN_STATE));
                String answerOne = cursor.getString(cursor.getColumnIndex(DBManager.COLUMN_CITY1));
                String answerTwo = cursor.getString(cursor.getColumnIndex(DBManager.COLUMN_CITY2));
                String answerThree = cursor.getString(cursor.getColumnIndex(DBManager.COLUMN_CITY3));
                QuizQuestion question = new QuizQuestion(questionState, answerOne, answerTwo, answerThree);
                question.setId(id);
                questions.add(question);
            }

        }
        catch (Exception e) {
            Log.d( DEBUG_TAG, "Exception caught: " + e);
        }
        finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return questions;
    }

    public List<Quiz> retrieveAllQuizzes() {
        ArrayList<Quiz> quizzes = new ArrayList<>();
        Cursor cursor = null;
        try {
            cursor = db.query(DBManager.TABLE_NAME_STORE_QUIZ, allColumns,
                    null, null, null, null, null);

            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    long id = cursor.getLong(cursor.getColumnIndex(DBManager.COLUMN_QUIZ_ID));
                    String quizDate = cursor.getString(cursor.getColumnIndex(DBManager.COLUMN_QUIZ_DATE));
                    String q1 = cursor.getString(cursor.getColumnIndex(DBManager.COLUMN_QUESTION1));
                    String q2 = cursor.getString(cursor.getColumnIndex(DBManager.COLUMN_QUESTION2));
                    String q3 = cursor.getString(cursor.getColumnIndex(DBManager.COLUMN_QUESTION3));
                    String q4 = cursor.getString(cursor.getColumnIndex(DBManager.COLUMN_QUESTION4));
                    String q5 = cursor.getString(cursor.getColumnIndex(DBManager.COLUMN_QUESTION5));
                    String q6 = cursor.getString(cursor.getColumnIndex(DBManager.COLUMN_QUESTION6));
                    int correctAnswers = cursor.getInt(cursor.getColumnIndex(DBManager.COLUMN_CORRECT_ANSWERS));

                    Quiz quiz = new Quiz(quizDate,q1,q2,q3,q4,q5,q6,correctAnswers);
                    quiz.setId(id);
                    quizzes.add(quiz);
                }
            }
        }
        catch(Exception e) {
            Log.d(DEBUG_TAG, "Exception caught: " + e);
        }
        finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return quizzes;
    }

    public Quiz storeQuiz(Quiz quiz) {
        ContentValues values = new ContentValues();
        values.put(DBManager.COLUMN_QUIZ_DATE, quiz.getQuizDate());
        values.put(DBManager.COLUMN_QUESTION1, quiz.getQ1());
        values.put(DBManager.COLUMN_QUESTION2, quiz.getQ2());
        values.put(DBManager.COLUMN_QUESTION3, quiz.getQ3());
        values.put(DBManager.COLUMN_QUESTION4, quiz.getQ4());
        values.put(DBManager.COLUMN_QUESTION5, quiz.getQ5());
        values.put(DBManager.COLUMN_QUESTION6, quiz.getQ6());
        values.put(DBManager.COLUMN_CORRECT_ANSWERS, quiz.getCorrectAnswers());

        long id = db.insert(DBManager.TABLE_NAME_STORE_QUIZ, null, values);

        quiz.setId(id);

        Log.d(DEBUG_TAG, "Stored new quiz with id: " + String.valueOf(quiz.getId()));
        return quiz;
    }


}
