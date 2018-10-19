package se.davin.hangmanapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        showResults();
    }

    private void showResults() {
        String result = getIntent().getStringExtra("result");
        String word = getIntent().getStringExtra("word");
        int triesLeft = getIntent().getIntExtra("tries", 0);

        TextView resultView = findViewById(R.id.resultView);
        TextView wordView = findViewById(R.id.resWordView);
        TextView triesView = findViewById(R.id.resTriesView);

        resultView.setText(result);
        wordView.setText(word);
        triesView.setText(Integer.toString(triesLeft));

    }
    public void mainMenu(View view){
        Intent intent = new Intent(this,
                MainActivity.class);
        startActivity(intent);
    }
}
