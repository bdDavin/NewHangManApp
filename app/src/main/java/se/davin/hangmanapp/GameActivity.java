package se.davin.hangmanapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;

public class GameActivity extends Fragment implements View.OnClickListener {

    private HangMan hangman = HangMan.getInstance();

    private ImageView imageView;
    private TextView wordView;
    private TextView triesView;
    private TextView guessesView;
    private EditText input;

    private SharedPreferences sh;
    private boolean theme;

    public GameActivity() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_game, container, false);

        Button guessButton = v.findViewById(R.id.guessButton);
        guessButton.setOnClickListener(this);
        setHasOptionsMenu(true);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        sh = getActivity().getSharedPreferences("settings", Context.MODE_PRIVATE);
        theme = sh.getBoolean("theme", false);

        String[] temp = getResources().getStringArray(R.array.words);
        ArrayList<String> words = new ArrayList<>(Arrays.asList(temp));
        hangman.setWords(words);
        hangman.newWord();

        imageView = getView().findViewById(R.id.imageView);
        wordView = getView().findViewById(R.id.wordView);
        triesView = getView().findViewById(R.id.triesIntView);
        guessesView = getView().findViewById(R.id.guessesView);
        input = getView().findViewById(R.id.userInput);

        changeImage(theme);

        wordView.setText(hangman.getHiddenWord());
        triesView.setText(Integer.toString(hangman.getTriesLeft()));
        guessesView.setText(hangman.getBadLetterUsed());
    }

    @Override
    public void onResume() {
        super.onResume();
        theme = sh.getBoolean("theme", false);
        changeImage(theme);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.guessButton:
                guessButton();
                break;
            default:
                break;
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.game_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.info:
                showAbout();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void guessButton(){

        String guess = input.getText().toString();

        if (guess.length() == 1){
            char guessChar = guess.charAt(0);

            if (Character.isLetter(guessChar)){
                if (!hangman.hasUsedLetter(guessChar)) {
                    hangman.guess(guessChar);

                    changeImage(theme);

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

    private void changeImage(boolean theme){
        int i = hangman.getTriesLeft();
        if (theme){
            Picasso.get()
                    .load("https://bddavin.github.io/HangManApp/hangH" + i + ".png")
                    .into(imageView);
        }else {
            Picasso.get()
                    .load("https://bddavin.github.io/HangManApp/hang" + i + ".gif")
                    .into(imageView);
        }
    }

    private void loser() {
        hangman.setResult(false);
        ResultActivity fragment = new ResultActivity();

        FragmentManager fM = getFragmentManager();
        FragmentTransaction fT = fM.beginTransaction();

        fT.replace(R.id.framelayout,fragment);
        fT.addToBackStack(null);
        fT.commit();
    }

    private void winner() {
        hangman.setResult(true);
        ResultActivity fragment = new ResultActivity();

        FragmentManager fM = getFragmentManager();
        FragmentTransaction fT = fM.beginTransaction();

        fT.replace(R.id.framelayout,fragment);
        fT.addToBackStack(null);
        fT.commit();
    }

    private void oneLetterToast() {
        Toast myToast = Toast.makeText(getContext(), "You can only enter ONE letter!",
                Toast.LENGTH_SHORT);
        myToast.show();
    }

    private void alreadyUsedToast() {
        Toast myToast = Toast.makeText(getContext(), "You have already used this letter!",
                Toast.LENGTH_SHORT);
        myToast.show();
    }

    private void onlyLettersToast() {
        Toast myToast = Toast.makeText(getContext(), "You can only use letters!",
                Toast.LENGTH_SHORT);
        myToast.show();
    }

    public void showAbout(){
        AboutActivity fragment = new AboutActivity();

        FragmentManager fM = getFragmentManager();
        FragmentTransaction fT = fM.beginTransaction();

        fT.replace(R.id.framelayout,fragment);
        fT.addToBackStack(null);
        fT.commit();
    }
}
