package se.davin.hangmanapp;

import android.content.Intent;
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

public class GameActivity extends AppCompatActivity {

    private HangMan hangman;

    protected ImageView imageView;
    protected TextView wordView;
    protected TextView triesView;
    protected TextView guessesView;
    protected EditText input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Toolbar t = findViewById(R.id.toolbar);
        setSupportActionBar(t);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        hangman = new HangMan(getResources());
        hangman.newWord();

        imageView = findViewById(R.id.imageView);
        wordView = findViewById(R.id.wordView);
        triesView = findViewById(R.id.triesIntView);
        guessesView = findViewById(R.id.guessesView);
        input = findViewById(R.id.userInput);

        imageView.setImageResource(R.drawable.hang10);
        wordView.setText(hangman.getHiddenWord());
        triesView.setText(Integer.toString(hangman.getTriesLeft()));
        guessesView.setText(hangman.getBadLetterUsed());
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
                    changeImage();
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

    private void changeImage(){
        int i = hangman.getTriesLeft();
        switch (i){
            case 1:
                imageView.setImageResource(R.drawable.hang1);
                break;
            case 2:
                imageView.setImageResource(R.drawable.hang2);
                break;
            case 3:
                imageView.setImageResource(R.drawable.hang3);
                break;
            case 4:
                imageView.setImageResource(R.drawable.hang4);
                break;
            case 5:
                imageView.setImageResource(R.drawable.hang5);
                break;
            case 6:
                imageView.setImageResource(R.drawable.hang6);
                break;
            case 7:
                imageView.setImageResource(R.drawable.hang7);
                break;
            case 8:
                imageView.setImageResource(R.drawable.hang8);
                break;
            case 9:
                imageView.setImageResource(R.drawable.hang9);
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
