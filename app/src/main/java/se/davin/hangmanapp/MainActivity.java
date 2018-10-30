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

public class MainActivity extends Fragment implements View.OnClickListener{

    public MainActivity() {}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_main, container, false);

        Button playButton = v.findViewById(R.id.playButton);
        playButton.setOnClickListener(this);

        Button showButton = v.findViewById(R.id.showButton);
        showButton.setOnClickListener(this);
        setHasOptionsMenu(true);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.playButton:
                playGame();
                break;
            case R.id.showButton:
                showAbout();
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
