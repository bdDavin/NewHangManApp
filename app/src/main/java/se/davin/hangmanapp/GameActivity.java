package se.davin.hangmanapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class GameActivity extends AppCompatActivity {

    private HangMan hangman;

    private ImageView imageView;
    private TextView wordView;
    private TextView triesView;
    private TextView guessesView;
    private EditText input;

    private SharedPreferences sh;
    private boolean theme;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Toolbar t = findViewById(R.id.toolbar);
        setSupportActionBar(t);

        sh = getSharedPreferences("settings", MODE_PRIVATE);
        theme = sh.getBoolean("theme", false);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        hangman = new HangMan(getResources());
        hangman.newWord();

        imageView = findViewById(R.id.imageView);
        wordView = findViewById(R.id.wordView);
        triesView = findViewById(R.id.triesIntView);
        guessesView = findViewById(R.id.guessesView);
        input = findViewById(R.id.userInput);
        if (theme){
            Picasso.get()
                    .load("https://bddavin.github.io/HangManApp/hangH10.png")
                    .into(imageView);
        }else {
            Picasso.get()
                    .load("https://bddavin.github.io/HangManApp/hang10.gif")
                    .into(imageView);
        }
        wordView.setText(hangman.getHiddenWord());
        triesView.setText(Integer.toString(hangman.getTriesLeft()));
        guessesView.setText(hangman.getBadLetterUsed());

    }

    @Override
    protected void onResume() {
        super.onResume();
        theme = sh.getBoolean("theme", false);
        if (theme){
            changeImageHallowen();
        }else {
            changeImageDefault();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.game_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.info:
                showAbout(findViewById(R.id.button2));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void guess(View view){

        String guess = input.getText().toString();

        if (guess.length() == 1){

            char guessChar = guess.charAt(0);

            if (Character.isLetter(guessChar)){

                if (!hangman.hasUsedLetter(guessChar)) {
                    hangman.guess(guessChar);
                    if (theme){
                        changeImageHallowen();
                    }else {
                        changeImageDefault();
                    }
                    wordView.setText(hangman.getHiddenWord());
                    triesView.setText(Integer.toString(hangman.getTriesLeft()));
                    guessesView.setText(hangman.getBadLetterUsed());

                }else {
                    alreadyUsedToast();
                }
            }else{
                onlyLettersToast();
            }
        }else{
           oneLetterToast();
        }
        input.setText("");

        if (hangman.hasWon()){
            winner();
        }
        if (hangman.hasLost()){
            loser();
        }
    }

    private void changeImageDefault(){
        int i = hangman.getTriesLeft();
        switch (i){
            case 1:
                Picasso.get()
                        .load("https://bddavin.github.io/HangManApp/hang1.gif")
                        .into(imageView);
                break;
            case 2:
                Picasso.get()
                        .load("https://bddavin.github.io/HangManApp/hang2.gif")
                        .into(imageView);
                break;
            case 3:
                Picasso.get()
                        .load("https://bddavin.github.io/HangManApp/hang3.gif")
                        .into(imageView);
                break;
            case 4:
                Picasso.get()
                        .load("https://bddavin.github.io/HangManApp/hang4.gif")
                        .into(imageView);
                break;
            case 5:
                Picasso.get()
                        .load("https://bddavin.github.io/HangManApp/hang5.gif")
                        .into(imageView);
                break;
            case 6:
                Picasso.get()
                        .load("https://bddavin.github.io/HangManApp/hang6.gif")
                        .into(imageView);
                break;
            case 7:
                Picasso.get()
                        .load("https://bddavin.github.io/HangManApp/hang7.gif")
                        .into(imageView);
                break;
            case 8:
                Picasso.get()
                        .load("https://bddavin.github.io/HangManApp/hang8.gif")
                        .into(imageView);
                break;
            case 9:
                Picasso.get()
                        .load("https://bddavin.github.io/HangManApp/hang9.gif")
                        .into(imageView);
                break;
        }
    }

    private void changeImageHallowen(){
        int i = hangman.getTriesLeft();
        switch (i){
            case 1:
                Picasso.get()
                        .load("https://bddavin.github.io/HangManApp/hangH1.png")
                        .into(imageView);
                break;
            case 2:
                Picasso.get()
                        .load("https://bddavin.github.io/HangManApp/hangH2.png")
                        .into(imageView);
                break;
            case 3:
                Picasso.get()
                        .load("https://bddavin.github.io/HangManApp/hangH3.png")
                        .into(imageView);
                break;
            case 4:
                Picasso.get()
                        .load("https://bddavin.github.io/HangManApp/hangH4.png")
                        .into(imageView);
                break;
            case 5:
                Picasso.get()
                        .load("https://bddavin.github.io/HangManApp/hangH5.png")
                        .into(imageView);
                break;
            case 6:
                Picasso.get()
                        .load("https://bddavin.github.io/HangManApp/hangH6.png")
                        .into(imageView);
                break;
            case 7:
                Picasso.get()
                        .load("https://bddavin.github.io/HangManApp/hangH7.png")
                        .into(imageView);
                break;
            case 8:
                Picasso.get()
                        .load("https://bddavin.github.io/HangManApp/hangH8.png")
                        .into(imageView);
                break;
            case 9:
                Picasso.get()
                        .load("https://bddavin.github.io/HangManApp/hangH9.png")
                        .into(imageView);
                break;
        }
    }

    private void loser() {
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("result", getResources().getString(R.string.you_lost));
        intent.putExtra("word", hangman.getRealWord());
        intent.putExtra("tries", hangman.getTriesLeft());
        startActivity(intent);
    }

    private void winner() {
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("result", getResources().getString(R.string.you_won));
        intent.putExtra("word", hangman.getRealWord());
        intent.putExtra("tries", hangman.getTriesLeft());
        startActivity(intent);
    }

    private void oneLetterToast() {
        Toast myToast = Toast.makeText(this, "You can only enter ONE letter!",
                Toast.LENGTH_SHORT);
        myToast.show();
    }

    private void alreadyUsedToast() {
        Toast myToast = Toast.makeText(this, "You have already used this letter!",
                Toast.LENGTH_SHORT);
        myToast.show();
    }

    private void onlyLettersToast() {
        Toast myToast = Toast.makeText(this, "You can only use letters!",
                Toast.LENGTH_SHORT);
        myToast.show();
    }

    public void showAbout(View view){
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);

    }
}
