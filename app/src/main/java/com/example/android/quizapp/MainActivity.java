package com.example.android.quizapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startEvaluation(View view) {
        String[] answers = evaluateGui();

        int result = evaluateQuiz(answers);

        toastResult(result);
    }

    public String[] evaluateGui() {
        String[] ret = new String[5];
        EditText editTextQuestion1 = findViewById(R.id.question_1);

        CheckBox checkBoxQuestion2E = findViewById(R.id.question_2_E);
        CheckBox checkBoxQuestion2A = findViewById(R.id.question_2_A);
        CheckBox checkBoxQuestion2D = findViewById(R.id.question_2_D);
        CheckBox checkBoxQuestion2F = findViewById(R.id.question_2_F);

        Boolean answerQuestion2 = false;

        if (checkBoxQuestion2E.isChecked() == true && checkBoxQuestion2A.isChecked() == true && checkBoxQuestion2D.isChecked() == true && checkBoxQuestion2F.isChecked() == false) {
            answerQuestion2 = true;
        }

        CheckBox checkBoxQuestion4E = findViewById(R.id.question_4_E);
        CheckBox checkBoxQuestion4A = findViewById(R.id.question_4_A);
        CheckBox checkBoxQuestion4D = findViewById(R.id.question_4_D);
        CheckBox checkBoxQuestion4G = findViewById(R.id.question_4_G);

        Boolean answerQuestion4 = false;

        Boolean noteE = checkBoxQuestion4E.isChecked();
        Boolean noteA = checkBoxQuestion4A.isChecked();
        Boolean noteD = checkBoxQuestion4D.isChecked();
        Boolean noteG = checkBoxQuestion4G.isChecked();



        if (noteE == true && noteA == false && noteD == false && noteG == false ) {
            answerQuestion4 = true;
        }

        ret[0] = editTextQuestion1.getText().toString().toLowerCase();
        ret[1] = Boolean.toString(answerQuestion2);
        ret[2] = evaluateRadioGroup(R.id.radio_group_question_3).toLowerCase();
        ret[3] = Boolean.toString(answerQuestion4);
        ret[4] = evaluateRadioGroup(R.id.radio_group_question_5).toLowerCase();

        return ret;
    }

    public int evaluateQuiz(String[] answers) {
        int result = 0;
        String[] correctAnswers = {"6", "true", "wood", "true", "body"};

        for (int i = 0; i < correctAnswers.length; i++) {
            if (answers[i].equals(correctAnswers[i])) {
                result++;
            }
        }

        return result;
    }

    public void toastResult(int result) {
        String message = result + " out of 5. ";

        if (result == 0) {
            message += "quite bad";
        } else if (result == 1) {
            message += "You could do better try again.";
        } else if (result == 2) {
            message += "Nice attempt.";
        } else if (result == 3) {
            message += "Really nice.";
        } else if (result == 4) {
            message += "Great!";
        } else if (result == 5) {
            message += "Absolutely awesome!";
        }

        Toast reportResult = Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_SHORT);
        reportResult.show();
    }

    private String evaluateRadioGroup(int id) {
        RadioGroup radioGroupQuestion;
        RadioButton radioButtonQuestion;

        radioGroupQuestion = findViewById(id);

        int radioButtonId = radioGroupQuestion.getCheckedRadioButtonId();
        radioButtonQuestion = findViewById(radioButtonId);

        if (radioButtonQuestion == null) {
            return "";
        }

        return (String) radioButtonQuestion.getText();
    }

    public void reset(View view) {
        EditText editText = findViewById(R.id.question_1);
        editText.setText("");

        CheckBox checkBox = findViewById(R.id.question_2_E);
        checkBox.setChecked(false);

        checkBox = findViewById(R.id.question_2_A);
        checkBox.setChecked(false);

        checkBox = findViewById(R.id.question_2_D);
        checkBox.setChecked(false);

        checkBox = findViewById(R.id.question_2_F);
        checkBox.setChecked(false);

        RadioGroup radioGroup = findViewById(R.id.radio_group_question_3);
        radioGroup.clearCheck();

        checkBox = findViewById(R.id.question_4_E);
        checkBox.setChecked(false);

        checkBox = findViewById(R.id.question_4_A);
        checkBox.setChecked(false);

        checkBox = findViewById(R.id.question_4_D);
        checkBox.setChecked(false);

        checkBox = findViewById(R.id.question_4_G);
        checkBox.setChecked(false);

        radioGroup = findViewById(R.id.radio_group_question_5);
        radioGroup.clearCheck();
    }
}