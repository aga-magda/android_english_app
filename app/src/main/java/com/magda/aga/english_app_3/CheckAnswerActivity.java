package com.magda.aga.english_app_3;

import android.support.v4.app.ActivityCompat;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class CheckAnswerActivity extends BaseActivity {

    private TextView textViewMainTranslation;
    private TextView textViewOtherTranslation;
    private TextView textViewQuestion;
    private Button buttonNext;
    private Button buttonKnownWord;
    private Button buttonUncertainedWord;
    private Button buttonUnknownWord;
    private Button buttonDefinition;
    private List<PolishWord> otherPolishTranslations = new ArrayList<PolishWord>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_answer);

        // adding toolbar - method from BaseActivity
        addToolbar();

        // binding the xml view with java using its id
        textViewMainTranslation = (TextView) findViewById(R.id.TEXT_VIEW_MAIN_TRANSLATION);
        textViewOtherTranslation = (TextView) findViewById(R.id.TEXT_VIEW_OTHER_TRANSLATION);
        textViewQuestion = (TextView) findViewById(R.id.TEXT_VIEW_QUESTION_2);
        buttonNext = (Button) findViewById(R.id.BUTTON_NEXT);
        buttonKnownWord = (Button) findViewById(R.id.BUTTON_KNOWN_WORD);
        buttonUncertainedWord = (Button) findViewById(R.id.BUTTON_UNCERTAINED_WORD);
        buttonUnknownWord = (Button) findViewById(R.id.BUTTON_UNKNOWN_WORD);
        buttonDefinition = (Button) findViewById(R.id.BUTTON_DEFINITION);

        // BaseActivity implements View.OnClickListener, by that I don't have to write new onClickListener for every button
        buttonNext.setOnClickListener(this); // it redirects call to the onClick() method
        buttonKnownWord.setOnClickListener(this);
        buttonUncertainedWord.setOnClickListener(this);
        buttonUnknownWord.setOnClickListener(this);
        buttonNext.setOnClickListener(this);

        // If this is the last question then change text button to "Zakończ"
        if (questionCounter == questionCountTotal){
            buttonNext.setText("Zakończ");
        }

        // show question, good answer (MAIN) and other (NOT MAIN) translation
        showQuestionInTextView(textViewQuestion);
        showCorrectAnswerToTextView(textViewMainTranslation);
        showOtherTranlation();
    }


    // onClick method from View.OnClickListener
    // call methods after click on specific button
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.BUTTON_KNOWN_WORD:
                db.setKnownWord(currentQuestion.getId());
                showToast("Dodano do znanych słów.");
                break;
            case R.id.BUTTON_UNCERTAINED_WORD:
                db.setUncertainedWord(currentQuestion.getId());
                showToast("Dodano do niepewnych słów.");
                break;
            case R.id.BUTTON_UNKNOWN_WORD:
                db.setUnknownWord(currentQuestion.getId());
                showToast("Dodano do nieznanych słów.");
                break;
            case R.id.BUTTON_NEXT:
                showNextQuestion();
                break;
        }
    }


    // if there is a question left open MainActivity, if not - close app
    private void showNextQuestion(){
        if (questionCounter < questionCountTotal) {
            openActivity(MainActivity.class);
        }
        else {
            if (questionCounter == questionCountTotal){
                questionCounter = 0;
                ActivityCompat.finishAffinity(CheckAnswerActivity.this);
            }
        }
    }


    // add polish translation to view
    private void showOtherTranlation(){
        otherPolishTranslations = db.getOtherPolishTranslations(currentQuestion.getId());
        String text = "";
        if (otherPolishTranslations.size() == 0){
            textViewOtherTranslation.setText("----");
        }
        else{
            for (PolishWord polishWord : otherPolishTranslations ){
                if (text == ""){
                    text = text + polishWord.getPl_word() + "\n";
                }
                else {
                    text = text + "\n" + polishWord.getPl_word();
                }
            }
            textViewOtherTranslation.setText(text);
        }
    }



}
