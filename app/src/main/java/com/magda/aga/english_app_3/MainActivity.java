package com.magda.aga.english_app_3;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MainActivity extends BaseActivity {

    /****************************** VARIABLES ******************************/

    // elements of View
    private TextView textViewQuestion;
    private TextView textViewQuestionCount;
    private Button [] buttonsOption = new Button[4];
    private Button buttonCheck;
    private PolishWord answer1, answer2, answer3;
    private ArrayList<PolishWord> answers = new ArrayList<PolishWord>();


    /****************************** HANDLING APPLICATION ******************************/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // adding toolbar - method from BaseActivity
        addToolbar();

        // binding the xml view with java using its id
        textViewQuestion = findViewById(R.id.TEXT_VIEW_QUESTION);
        textViewQuestionCount = findViewById(R.id.TEXT_VIEW_QUESTION_COUNT);

        buttonsOption[0] = findViewById(R.id.BUTTON_OPTION0);
        buttonsOption[1] = findViewById(R.id.BUTTON_OPTION1);
        buttonsOption[2] = findViewById(R.id.BUTTON_OPTION2);
        buttonsOption[3] = findViewById(R.id.BUTTON_OPTION3);
        buttonCheck = findViewById(R.id.BUTTON_CHECK);

        // BaseActivity implements View.OnClickListener, by that I don't have to write new onClickListener for every button
        buttonsOption[0].setOnClickListener(this); // it redirects call to the onClick() method
        buttonsOption[1].setOnClickListener(this);
        buttonsOption[2].setOnClickListener(this);
        buttonsOption[3].setOnClickListener(this);
        buttonCheck.setOnClickListener(this);

        questionList = db.getAllEnglishWords(); // question is english word
        questionCountTotal = questionList.size(); // number of questions

        // Shared preferences - questionCounter
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        this.questionCounter = sharedPref.getInt("questionCounter", 0);

        showNextQuestion();

    }

    // Shared preferences - questionCounter
    @Override
    protected void onPause(){
        super.onPause();
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("questionCounter", questionCounter);
        editor.commit();
    }

    // onClick method from View.OnClickListener
    // call methods after click on specific button
    public void onClick (View view){
        switch (view.getId()) {
            case R.id.BUTTON_OPTION0:
                buttonSelected(buttonsOption[0]);
                break;

            case R.id.BUTTON_OPTION1:
                buttonSelected(buttonsOption[1]);
                break;

            case R.id.BUTTON_OPTION2:
                buttonSelected(buttonsOption[2]);
                break;

            case R.id.BUTTON_OPTION3:
                buttonSelected(buttonsOption[3]);
                break;

            case R.id.BUTTON_CHECK:
                // if any of buttons is selected check answer after click
                if (buttonsOption[0].isSelected() || buttonsOption[1].isSelected() || buttonsOption[2].isSelected() || buttonsOption[3].isSelected()) {
                    checkAnswer();
                    openActivity(CheckAnswerActivity.class);
                }
                else {
                    showToast("Proszę wybrać odpowiedź.");
                }
                break;
        }
    }


    /****************************** METHODS USE ABOVE ******************************/

    private void showNextQuestion () {
        // till there is a question left - show next question in MainActivity
        if (questionCounter < questionCountTotal) {
            currentQuestion = questionList.get(questionCounter);
            showQuestionInTextView(textViewQuestion);

            // get correct answer and 3 random words from database
            correctAnswer = db.getCorrectAnswer(currentQuestion.getId());
            answer1 = db.getRandomPolishWord(correctAnswer.getId());
            answer2 = db.getRandomPolishWord(correctAnswer.getId(), answer1.getId());
            answer3 = db.getRandomPolishWord(correctAnswer.getId(), answer1.getId(), answer2.getId());

            // clear array after last question and add new answers
            answers.clear();
            answers.addAll(Arrays.asList(correctAnswer, answer1, answer2, answer3));

            // shuffle answers in random order
            Collections.shuffle(answers);

            // set random order answers in buttons
            for (int i = 0; i<buttonsOption.length; i++){
                buttonsOption[i].setText(answers.get(i).getPl_word());
            }

            // adding number to questionCounter and show question number
            questionCounter++;

            textViewQuestionCount.setText("Pytanie: " + questionCounter + "/" + questionCountTotal);
        }

        else {
            // close this activity and run another if exists
            finish();
        }
    }


    // check answer - save to database and show toast
    private void checkAnswer(){
        for (Button b: buttonsOption){
            // if specific button is selected and text on it is correct answer then check it as a good answer
            if (b.isSelected() && (correctAnswer.getPl_word() == b.getText())){
                db.setGoodAnswer(currentQuestion.getId());
                isGoodAnswer = true;
                showToastGoodAnswer();
            }
            else if (b.isSelected() && (correctAnswer.getPl_word() != b.getText())) {
                db.setBadAnswer(currentQuestion.getId());
                isGoodAnswer = false;
                showToastBadAnswer();
            }
        }
    }


    // button selected - change a state for selected button - and by that in xml code change view of that button
    public void buttonSelected(Button button){
        for (int i = 0; i<4; i++){
            if(button == buttonsOption[i])
                button.setSelected(true);
            else
                buttonsOption[i].setSelected(false);
        }
    }
}



