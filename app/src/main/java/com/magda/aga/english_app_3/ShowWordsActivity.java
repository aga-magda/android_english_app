package com.magda.aga.english_app_3;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class ShowWordsActivity extends BaseActivity {

    /****************************** VARIABLES ******************************/

    // elements of View
    private TextView textViewWords;


    /****************************** HANDLING APPLICATION ******************************/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_words);

        // binding the xml view with java using its id
        textViewWords = (TextView) findViewById(R.id.TEXT_VIEW_WORDS);

        // enable to scroll text view
        textViewWords.setMovementMethod(new ScrollingMovementMethod());

        // adding toolbar - method from BaseActivity
        addToolbar();

        showWordsByType(wordsType, textViewWords);
    }


    @Override
    public void onClick(View view) {}


    /****************************** METHODS USE ABOVE ******************************/

    // methods use to show words in ShowWordsActivity
    private void showWordsByType(int type, TextView textView){
        if (type == 1){
            wordList = db.getAllBadAnswer();
            showWordsInTextView(wordList, textView);
            wordList.clear();
        }
        if (type == 2){
            wordList = db.getAllGoodAnswer();
            showWordsInTextView(wordList, textView);
            wordList.clear();
        }
        if (type == 3){
            wordList = db.getAllKnownWords();
            showWordsInTextView(wordList, textView);
            wordList.clear();
        }
        if (type == 4){
            wordList = db.getAllUncertainedWords();
            showWordsInTextView(wordList, textView);
            wordList.clear();
        }
        if (type == 5){
            wordList = db.getAllUnknownWords();
            showWordsInTextView(wordList, textView);
            wordList.clear();
        }
    }


    private void showWordsInTextView(List<EnglishWord> wordList, TextView textView){
        String text = "";
        if (wordList.size() == 0){
            textView.setText("----");
        }
        else{
            for (EnglishWord englishWord : wordList ){
                text = text + englishWord.getEn_word() + "\n";
            }
        }
        textView.setText(text);
    }
}
