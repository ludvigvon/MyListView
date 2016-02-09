package com.example.h.mylistview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;


public class FirstActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnFilms;
    Button btnDocumentaires;
    Button btnEpisodes;
    Button btnEmissions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        btnFilms = (Button)findViewById(R.id.buttonFilms);
        btnFilms.setOnClickListener(this);
        btnDocumentaires = (Button)findViewById(R.id.buttonDocumentaires);
        btnDocumentaires.setOnClickListener(this);
        btnEmissions = (Button)findViewById(R.id.buttonEmissions);
        btnEmissions.setOnClickListener(this);
        btnEpisodes = (Button)findViewById(R.id.buttonEpisodes);
        btnEpisodes.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch(v.getId()){
            case R.id.buttonFilms:
                intent = new Intent(this, MainActivity.class);
                intent.putExtra("typeemission", "Films");
                startActivity(intent);
                break;
            case R.id.buttonDocumentaires:
                intent = new Intent(this, MainActivity.class);
                intent.putExtra("typeemission", "Documentaires");
                startActivity(intent);
                break;
            case R.id.buttonEmissions:
                intent = new Intent(this, MainActivity.class);
                intent.putExtra("typeemission", "Émissions");
                startActivity(intent);
                break;
            case R.id.buttonEpisodes:
                intent = new Intent(this, MainActivity.class);
                intent.putExtra("typeemission", "Épisodes");
                startActivity(intent);
                break;
        }
    }
}
