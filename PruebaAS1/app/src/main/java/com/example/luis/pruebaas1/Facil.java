package com.example.luis.pruebaas1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Random;


public class Facil extends AppCompatActivity implements View.OnClickListener {

    Random random = new Random();

    Chronometer cronometro;

    TextView Pregunta,Score,Tiempo;
    Button Resp1,Resp2,Resp3;

    int rand1, rand2, rand3, rand4,nRand, realResp, contScore =0;
    Boolean Unavez = true;
    int score =0,bestScore = 0;
    String nameFile = "facilScore.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facil);
        titleActivity();
        //Igualacion de objeto Java con Objeto Xml
        cronometro = (Chronometer) findViewById(R.id.chronometer);

        Pregunta = (TextView) findViewById(R.id.Pregunta);
        Score = (TextView) findViewById(R.id.Score);
        Tiempo = (TextView) findViewById(R.id.Tiempo);

        Resp1 = (Button) findViewById(R.id.Resp1);
        Resp2 = (Button) findViewById(R.id.Resp2);
        Resp3 = (Button) findViewById(R.id.Resp3);

        //Esperan el click
        Resp1.setOnClickListener(this);
        Resp2.setOnClickListener(this);
        Resp3.setOnClickListener(this);

        //Sí funcionó, puto
        if(Unavez){
            GeneracionRandom();
            Cronometro();
            Unavez = false;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_second, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //Cuando se de click a un boton:
    public void onClick(View v){

        //El id del boton seleccionado es v.getId
        switch (v.getId()){

            case R.id.Resp1:
                //Presionó el  boton 1
                if(rand3==1 || rand3==4) {
                    //La respuesta era correcta
                    Toast.makeText(getApplicationContext(), "Respuesta correcta", Toast.LENGTH_SHORT).show();
                    contScore++;
                    Score.setText(""+ contScore);
                    setScore(contScore);
                    cacheMemory();
                }
                //La respuesta era incorrecta
                else
                    Perder();

                GeneracionRandom();
                Cronometro();
                break;

            case R.id.Resp2:
                //Presionó el  boton 2
                if(rand3==2 || rand3==5) {
                    //La respuesta era correcta
                    Toast.makeText(getApplicationContext(), "Respuesta correcta", Toast.LENGTH_SHORT).show();
                    contScore++;
                    Score.setText(""+ contScore);
                    setScore(contScore);
                    cacheMemory();
                }
                //La respuesta era incorrecta
                else
                    Perder();

                GeneracionRandom();
                Cronometro();
                break;

            case R.id.Resp3:
                //Presionó el  boton 3
                if(rand3==3 || rand3==6) {
                    //La respuesta era correcta
                    Toast.makeText(getApplicationContext(), "Respuesta correcta", Toast.LENGTH_SHORT).show();
                    contScore++;
                    Score.setText(""+ contScore);
                    setScore(contScore);
                    cacheMemory();
                }
                //La respuesta era incorrecta
                else
                    Perder();

                GeneracionRandom();
                Cronometro();
                break;
        }
    }
    public void Cronometro() {
        //Inciar cronometro
        cronometro.setBase(SystemClock.elapsedRealtime());
        cronometro.start();
        cronometro.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {

            @Override
            public void onChronometerTick(Chronometer chronometer) {
                long elapsedTime = SystemClock.elapsedRealtime() - cronometro.getBase();
                if (elapsedTime > 5000) {
                    Perder();
                    cronometro.stop();
                }
            }

        });
    }

    public void Perder(){
        setScore(contScore);
        cacheMemory();
        finish();
        Intent perder = new Intent(Facil.this,ScoreFinal.class);
        perder.putExtra("BestScore",bestScore);
        perder.putExtra("Score",getScore());
        startActivity(perder);
    }

    public void GeneracionRandom() {

        rand1 = (int) (random.nextDouble() * 5) + 1;//Primer valor random A
        rand2 = (int) (random.nextDouble() * 5) + 1;//Segundo valor random B
        rand3 = (int) (random.nextDouble() * 3) + 1;//Valor random

        rand4 = (int) (random.nextDouble() * 5) + 1;//Tercer valor C, Cuando avance en la partida

        realResp = rand1 + rand2;// A+B = Respuesta

        Pregunta.setText(rand1 + "+" + rand2); // Objeto Pregunta(TextView) = " A + B "


        if(contScore > 5){
            rand3 = (int) (random.nextDouble() * 3) + 4;
            realResp = rand1 + rand2 + rand4;
        }

        //Tercer valor aleatorio elije: en que boton se ecuentra la respuesta
        switch (rand3) {

            case 1:
                //Imprecion de respuestas en los botones.
                Resp1.setText("" + realResp);
                Resp2.setText("" + (realResp - 1));
                Resp3.setText("" + (realResp +  1));
                break;

            case 2:
                nRand = (int) (random.nextDouble() * 2) + 1;
                Resp1.setText("" + (realResp + nRand));
                Resp2.setText("" + realResp);
                Resp3.setText("" + (realResp - (nRand)));
                break;

            case 3:
                nRand = (int) (random.nextDouble() * 2) + 1;
                Resp1.setText("" + (realResp + nRand + 1));
                Resp2.setText("" + (realResp - 1));
                Resp3.setText("" + realResp);
                break;

            case 4:
                Pregunta.setText(rand1 + "+" + rand2 + "+" + rand4);
                Resp1.setText("" + realResp);
                Resp2.setText("" + (realResp - 1));
                Resp3.setText("" + (realResp +  1));
                break;

            case 5:
                Pregunta.setText(rand1 + "+" + rand2 + "+" + rand4);
                nRand = (int) (random.nextDouble() * 2) + 1;
                Resp1.setText("" + (realResp + nRand));
                Resp2.setText("" + realResp);
                Resp3.setText("" + (realResp - (nRand)));
                break;

            case 6:
                Pregunta.setText(rand1 + "+" + rand2 + "+" + rand4);
                nRand = (int) (random.nextDouble() * 2) + 1;
                Resp1.setText("" + (realResp + nRand + 1));
                Resp2.setText("" + (realResp - 1));
                Resp3.setText("" + realResp);
                break;
        }
    }

    private  void titleActivity(){
        this.setTitle(getResources().getString(R.string.title_activity_second));
    }

    private void cacheMemory(){

        try {
            Bundle extras = getIntent().getExtras();
            FileOutputStream fos = openFileOutput(nameFile, Context.MODE_PRIVATE);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            bestScore = (Integer) extras.get("BestScore");
            //Toast.makeText(getApplicationContext(),"bestscore: " + bestScore, Toast.LENGTH_SHORT).show();
            score = getScore();
            //Toast.makeText(getApplicationContext(),"SCORES: " + score, Toast.LENGTH_SHORT).show();
            if (score>bestScore){
                bestScore = score;
            osw.write(score);
            osw.flush();
            osw.close();}
            else {
                osw.write(bestScore);
                osw.flush();
                osw.close();
            }
            //Toast.makeText(getApplicationContext(),"score: " + score, Toast.LENGTH_SHORT).show();

        }
        catch (IOException e){}
    }
    public int getScore(){
        return score;
    }
    public void setScore(int score){
        this.score = score;
    }
}
//Fin :3