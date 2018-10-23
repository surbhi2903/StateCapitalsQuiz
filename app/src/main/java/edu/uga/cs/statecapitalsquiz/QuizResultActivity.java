package edu.uga.cs.statecapitalsquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TableRow;
import android.widget.TextView;

public class QuizResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_result);
        EditText editText = (EditText) findViewById(R.id.editText);
        Intent intent = getIntent();
        String quizText = intent.getStringExtra(MainActivity.MESSAGE_TYPE);
        editText.setText(quizText);

    }
}
