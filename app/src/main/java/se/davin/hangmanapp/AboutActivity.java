package se.davin.hangmanapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RadioButton;

public class AboutActivity extends AppCompatActivity {

    private SharedPreferences sh;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        Toolbar t = findViewById(R.id.toolbar);
        setSupportActionBar(t);

        sh = getSharedPreferences("settings", MODE_PRIVATE);
        editor = sh.edit();
    }

    public void onRadioButtonClicked(View view) {

        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.defaultTheme:
                if (checked)
                    editor.putBoolean("theme", false);
                    editor.apply();
                    RadioButton b1 = findViewById(R.id.defaultTheme);
                    b1.toggle();
                    break;
            case R.id.defaultHallo:
                if (checked)
                    editor.putBoolean("theme", true);
                    editor.apply();
                    RadioButton b2 = findViewById(R.id.defaultHallo);
                    b2.toggle();
                break;
        }
    }
}
