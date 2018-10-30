package se.davin.hangmanapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;

public class AboutFragment extends Fragment implements View.OnClickListener {

    private SharedPreferences sh;
    private SharedPreferences.Editor editor;

    public AboutFragment() {}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_about, container, false);

        Button playButton = v.findViewById(R.id.defaultTheme);
        playButton.setOnClickListener(this);

        Button showButton = v.findViewById(R.id.halloweenTheme);
        showButton.setOnClickListener(this);

        setHasOptionsMenu(true);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        sh = getActivity().getSharedPreferences("settings", Context.MODE_PRIVATE);
        editor = sh.edit();

        boolean theme = sh.getBoolean("theme", false);

        RadioButton r1 = getView().findViewById(R.id.defaultTheme);
        RadioButton r2 = getView().findViewById(R.id.halloweenTheme);
        if (theme){
            r2.setChecked(true);
        }else {
            r1.setChecked(true);
        }
    }

    @Override
    public void onClick(View v) {
        boolean checked = ((RadioButton) v).isChecked();

        switch(v.getId()) {
            case R.id.defaultTheme:
                if (checked)
                    editor.putBoolean("theme", false);
                editor.apply();;
                break;
            case R.id.halloweenTheme:
                if (checked)
                    editor.putBoolean("theme", true);
                editor.apply();
                break;
        }
    }
}
