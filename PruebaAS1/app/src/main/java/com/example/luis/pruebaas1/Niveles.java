package com.example.luis.pruebaas1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Carlos on 10/11/2015.
 */
public class Niveles extends AppCompatActivity implements View.OnClickListener{

    Button facil,medio,dificil,back;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nivel);
        titleActivity();

        facil = (Button) findViewById(R.id.buttonFacil);
        medio = (Button) findViewById(R.id.buttonMedio);
        dificil = (Button) findViewById(R.id.buttonDificil);
        back = (Button) findViewById(R.id.buttonBack);

        facil.setOnClickListener(this);
        medio.setOnClickListener(this);
        dificil.setOnClickListener(this);
        back.setOnClickListener(this);
        medio.setEnabled(false);
        dificil.setEnabled(false);
        buttonEnabledFacil();
        buttonEnabledMedio();
        buttonEnabledDificil();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.buttonFacil:
                finish();
                Intent facil = new Intent(Niveles.this,Facil.class);
                facil.putExtra("BestScore",getScoreFacil());
                startActivity(facil);
                break;

            case R.id.buttonMedio:
                finish();
                Intent medio = new Intent(Niveles.this,Medio.class);
                medio.putExtra("BestScore",getScoreMedio());
                startActivity(medio);
                break;

            case R.id.buttonDificil:
                finish();
                Intent dificil = new Intent(Niveles.this,Dificil.class);
                dificil.putExtra("BestScore",getScoreDificil());
                startActivity(dificil);
                break;

            case R.id.buttonBack:
                finish();
                break;
        }
    }
    private  void titleActivity(){
        this.setTitle(getResources().getString(R.string.nivel));
    }
    String facilFile = "facilScore.txt";
    String medioFile = "medioScore.txt";
    String dificilFile = "dificilScore.txt";
    public int facilScore = 0,bestFacil = 0;
    int medioScore = 0,bestMedio = 0;
    int dificilScore = 0,bestDificil = 0;
    public void buttonEnabledFacil(){
        try {
            FileInputStream fis = openFileInput(facilFile);
            InputStreamReader isr = new InputStreamReader(fis);
            facilScore = isr.read();
            //Toast.makeText(getApplicationContext(), "score: " + facilScore, Toast.LENGTH_SHORT).show();

            setScoreFacil(facilScore);
            //Toast.makeText(getApplicationContext(), "score: " + getScoreFacil(), Toast.LENGTH_SHORT).show();
            if (facilScore>=10){
                medio.setEnabled(true);
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    public void buttonEnabledMedio(){
        try {
            FileInputStream fis = openFileInput(medioFile);
            InputStreamReader isr = new InputStreamReader(fis);
            medioScore = isr.read();
            //Toast.makeText(getApplicationContext(), "score: " + medioScore, Toast.LENGTH_SHORT).show();
            setScoreMedio(medioScore);
            if (medioScore>=15){
                dificil.setEnabled(true);
            }


        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    public void buttonEnabledDificil(){
        try {
            FileInputStream fis = openFileInput(dificilFile);
            InputStreamReader isr = new InputStreamReader(fis);
            dificilScore = isr.read();
            Toast.makeText(getApplicationContext(), "score: " + dificilScore, Toast.LENGTH_SHORT).show();
            setScoreDificil(dificilScore);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    public int getScoreFacil(){
        return bestFacil;
    }
    public void setScoreFacil(int score){
        this.bestFacil = score;
    }
    public int getScoreMedio(){
        return bestMedio;
    }
    public void setScoreMedio(int score){
        this.bestMedio = score;
    }
    public int getScoreDificil(){
        return bestDificil;
    }
    public void setScoreDificil(int score){
        this.bestDificil = score;
    }
}
