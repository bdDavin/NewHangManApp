package se.davin.hangmanapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
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
import android.widget.TextView;

public class ResultActivity extends Fragment implements View.OnClickListener {

    public ResultActivity() {}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_result, container, false);

        Button playButton = v.findViewById(R.id.backButton);
        playButton.setOnClickListener(this);

        Button showButton = v.findViewById(R.id.newGame);
        showButton.setOnClickListener(this);

        setHasOptionsMenu(true);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showResults();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backButton:
                mainMenu();
                break;
            case R.id.newGame:
                playGame();
                break;
            default:
                break;
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.main_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.play_arrow:
                playGame();
                return true;
            case R.id.info:
                showAbout();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showResults() {
        HangMan hang = HangMan.getInstance();
        boolean winner = hang.getResult();
        String result;

        if (winner){
            result = getString(R.string.you_won);
        }else{
            result = getString(R.string.you_lost);
        }

        String word = hang.getRealWord();
        int triesLeft = hang.getTriesLeft();

        TextView resultView = getView().findViewById(R.id.resultView);
        TextView wordView = getView().findViewById(R.id.resWordView);
        TextView triesView = getView().findViewById(R.id.resTriesView);

        resultView.setText(result);
        wordView.setText(word);
        triesView.setText(Integer.toString(triesLeft));

        hang.setWord(null);
    }

    public void mainMenu(){
        MainActivity fragment = new MainActivity();

        FragmentManager fM = getFragmentManager();
        FragmentTransaction fT = fM.beginTransaction();

        fT.replace(R.id.framelayout,fragment);
        fT.addToBackStack(null);
        fT.commit();
    }

    public void playGame(){
        GameActivity fragment = new GameActivity();

        FragmentManager fM = getFragmentManager();
        FragmentTransaction fT = fM.beginTransaction();

        fT.replace(R.id.framelayout,fragment);
        fT.addToBackStack(null);
        fT.commit();
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
