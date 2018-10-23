package se.davin.hangmanapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Toolbar t = findViewById(R.id.toolbar);
        setSupportActionBar(t);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        showResults();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.play_arrow:
                playGame(findViewById(R.id.button1));
                return true;
            case R.id.info:
                showAbout(findViewById(R.id.button2));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        mainMenu(findViewById(R.id.backButton));
        return true;
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

    public void playGame(View view){
        Intent intent = new Intent(this,
                GameActivity.class);
        startActivity(intent);
    }

    public void showAbout(View view){
        Intent intent = new Intent(this,
                AboutActivity.class);
        startActivity(intent);
    }
}
