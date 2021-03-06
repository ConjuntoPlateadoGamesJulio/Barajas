package com.example.luis.pruebaas1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Carlos on 10/11/2015.
 */
public class Niveles extends AppCompatActivity implements View.OnClickListener{

    ImageButton facil,medio,dificil,back;
    String mensajeMedio = "";
    String mensajeDificil = "";
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nivel);
        titleActivity();
        hideStatusBar();
        mensajeMedio = getApplicationContext().getString(R.string.bloqueadoMedio);
        mensajeDificil = getApplicationContext().getString(R.string.bloqueadoDificil);
        facil = (ImageButton) findViewById(R.id.buttonFacil);
        medio = (ImageButton) findViewById(R.id.buttonMedio);
        dificil = (ImageButton) findViewById(R.id.buttonDificil);
        back = (ImageButton) findViewById(R.id.buttonBack);

        facil.setOnClickListener(this);
        medio.setOnClickListener(this);
        dificil.setOnClickListener(this);
        back.setOnClickListener(this);
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
                if (facilScore  < 20){
                    Toast.makeText(getApplicationContext(), mensajeMedio, Toast.LENGTH_SHORT).show();
                }else{
                Intent medio = new Intent(Niveles.this,Medio.class);
                medio.putExtra("MedioScore",getScoreMedio());
                startActivity(medio);}
                break;

            case R.id.buttonDificil:
                if (medioScore < 30){
                    Toast.makeText(getApplicationContext(), mensajeDificil, Toast.LENGTH_SHORT).show();
                }else{
                Intent dificil = new Intent(Niveles.this,Dificil.class);
                dificil.putExtra("DificilScore",getScoreDificil());
                startActivity(dificil);}
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
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    boolean m = false;
    public void buttonEnabledMedio(){
        try {
            FileInputStream fis = openFileInput(medioFile);
            InputStreamReader isr = new InputStreamReader(fis);
            medioScore = isr.read();
            //Toast.makeText(getApplicationContext(), "score: " + medioScore, Toast.LENGTH_SHORT).show();
            setScoreMedio(medioScore);
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
            //Toast.makeText(getApplicationContext(), "score: " + dificilScore, Toast.LENGTH_SHORT).show();
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
    private void hideStatusBar(){
        try {
            View decorView = getWindow().getDecorView();
// Hide the status bar.
            int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }catch (NullPointerException e){}
    }
}
