package co.miniforge.corey.stringanalyzer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;


public class AnalyzerActivity extends AppCompatActivity {

    String message = "";

    TextView messageTextView;
    Button backToInputButton;

    TextAnalyzerUtil textUtil;

    TextView uniqueWords;
    TextView characterCount;
    TextView wordCount;
    TextView uniqueCharacters;
    TextView longestWord;
    TextView specialCharactersCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analyzer);

        getIntentData();

        textUtil = new TextAnalyzerUtil(message);

        locateViews();

        setClickListener();

        bindData();
    }

    void locateViews() {
        this.messageTextView = (TextView) findViewById(R.id.messageTextView);
        this.characterCount = (TextView) findViewById(R.id.characterCount);
        this.wordCount = (TextView) findViewById(R.id.wordCount);
        this.uniqueCharacters = (TextView) findViewById(R.id.uniqueCharacters);
        this.uniqueWords = (TextView) findViewById(R.id.uniqueWords);
        this.longestWord = (TextView) findViewById(R.id.longestWord);
        this.specialCharactersCount = (TextView) findViewById(R.id.specialCharactersCount);
        this.backToInputButton = (Button) findViewById(R.id.backToInputButton);
    }

    void getIntentData(){
        if(getIntent().hasExtra(InputActivity.intentTag)){
            this.message = getIntent().getStringExtra(InputActivity.intentTag);

            //Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
        }
    }

    void bindData(){
        messageTextView.setText(message);

        characterCount.setText(String.format(Locale.getDefault(), "Character Count: %d", textUtil.getTextCharacterCount()));
        wordCount.setText(String.format(Locale.getDefault(), "Word Count: %d", textUtil.getWordCount()));
        uniqueCharacters.setText(String.format(Locale.getDefault(), "Unique Characters: %d", textUtil.getUniqueCharacters()));
        specialCharactersCount.setText(String.format(Locale.getDefault(), "Special Characters: %d", textUtil.getSpecialCharacterCount()));
        uniqueWords.setText(String.format(Locale.getDefault(), "Unique Words: %d", textUtil.getUniqueWords()));
        longestWord.setText(String.format(Locale.getDefault(), "Longest Word: %s", textUtil.getLongestWord()));
    }

    void setClickListener() {
        backToInputButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), InputActivity.class);
                startActivity(intent);
            }
        });
    }
}
