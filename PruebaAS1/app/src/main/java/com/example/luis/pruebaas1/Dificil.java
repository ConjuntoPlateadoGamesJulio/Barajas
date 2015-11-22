package com.example.luis.pruebaas1;

import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Random;


public class Dificil extends AppCompatActivity implements View.OnClickListener{

    Random random = new Random();

    Chronometer cronometro;

    TextView Pregunta,Score;
    Button Resp1,Resp2,Resp3;

    int rand1, rand2, randCase, rand3, rand4, realResp,nRand, SignoRand, contScore =0;
    Boolean Unavez = true;
    int score =0,bestScore = 0;
    String nameFile = "medioScore.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medio);

        cronometro = (Chronometer) findViewById(R.id.chronometer);

        Pregunta = (TextView) findViewById(R.id.Pregunta);
        Score = (TextView) findViewById(R.id.Score);

        Resp1 = (Button) findViewById(R.id.Resp1);
        Resp2 = (Button) findViewById(R.id.Resp2);
        Resp3 = (Button) findViewById(R.id.Resp3);

        //Esperan el click
        Resp1.setOnClickListener(this);
        Resp2.setOnClickListener(this);
        Resp3.setOnClickListener(this);

        if(Unavez){
            GeneracionRandom();
            Cronometro();
            Unavez = false;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_medio, menu);
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

    @Override
    public void onClick(View v) {

        //El id del boton seleccionado es v.getId
        switch (v.getId()){

            case R.id.Resp1:
                //Presionó el  boton 1
                if(randCase == 1 || randCase == 4 || randCase == 7) {
                    //La respuesta era correcta
                    Toast.makeText(getApplicationContext(), "Respuesta correcta", Toast.LENGTH_SHORT).show();
                    contScore++;
                    Score.setText("" + contScore);
                    GeneracionRandom();
                    Cronometro();
                    setScore(contScore);
                    cacheMemory();
                }
                //La respuesta era incorrecta
                else
                    Perder();

                break;

            case R.id.Resp2:
                //Presionó el  boton 2
                if(randCase == 2 || randCase == 5 || randCase == 8) {
                    //La respuesta era correcta
                    Toast.makeText(getApplicationContext(), "Respuesta correcta", Toast.LENGTH_SHORT).show();
                    contScore++;
                    Score.setText("" + contScore);
                    GeneracionRandom();
                    Cronometro();
                    setScore(contScore);
                    cacheMemory();
                }
                //La respuesta era incorrecta
                else
                    Perder();

                break;

            case R.id.Resp3:
                //Presionó el  boton 3
                if(randCase == 3 || randCase == 6 || randCase == 9) {
                    //La respuesta era correcta
                    Toast.makeText(getApplicationContext(), "Respuesta correcta", Toast.LENGTH_SHORT).show();
                    contScore++;
                    Score.setText(""+ contScore);
                    GeneracionRandom();
                    Cronometro();
                    setScore(contScore);
                    cacheMemory();
                }
                //La respuesta era incorrecta
                else
                    Perder();

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
                    cronometro.stop();
                    Perder();
                }
            }

        });
    }

    public void Perder(){
        setScore(contScore);
        cacheMemory();
        finish();
        Intent perder = new Intent(Dificil.this,ScoreFinal.class);
        perder.putExtra("BestScore",bestScore);
        perder.putExtra("Score", getScore());
        startActivity(perder);
    }

    public void GeneracionRandom() {

        rand1 = (int) (random.nextDouble() * 16) + 5;//Primer valor random A
        rand2 = (int) (random.nextDouble() * 16) + 5;//Segundo valor random B
        rand3 = (int) (random.nextDouble() * 16) + 5;//Tercer valor C, Cuando avance en la partida
        rand4 = (int) (random.nextDouble() * 16) + 5;

        if(contScore <= 10) {

            SignoRand = (int) (random.nextDouble() * 2) + 1;//Valor random para seleccionar signo: + o -

            if (SignoRand == 1) {
                realResp = rand1 + rand2 + rand3;// A+B = Respuesta
                Pregunta.setText(rand1 + "+" + rand2 + "+" + rand3); // Objeto Pregunta(TextView) = " A + B "
                randCase = (int) (random.nextDouble() * 3) + 1;//Valor random
            } else {
                if ( (rand1+rand2) > rand3) {
                    realResp = rand1 + rand2 - rand3;// A+B-C = Respuesta
                    Pregunta.setText(rand1 + "+" + rand2 + "-" + rand3); // Objeto Pregunta(TextView) = " A + B "
                    randCase = (int) (random.nextDouble() * 3) + 4;//Valor random
                } else
                    GeneracionRandom();
            }
        }
        else{

            SignoRand = (int) (random.nextDouble() * 3) + 1;
            randCase = (int) (random.nextDouble() * 3) + 7;

            switch (SignoRand){

                case 1:
                    realResp = rand1 + rand2 + rand3 + rand4;//A+B+C = Respuesta
                    Pregunta.setText(rand1 + "+" + rand2 + "+" + rand3 + "+" + rand4);
                    break;

                case 2:
                    if( (rand1+rand2+rand4) > rand3) {
                        realResp = rand1 + rand2 - rand3 + rand4;//A+B-C = Respuesta
                        Pregunta.setText(rand1 + "+" + rand2 + "-" + rand3 + "+" + rand4);
                    }
                    else
                        GeneracionRandom();

                    break;

                case 3:
                    if( (rand1+rand3+rand4) > rand2) {
                        realResp = rand1 - rand2 + rand3 + rand4;//A-B+C = Respuesta
                        Pregunta.setText(rand1 + "-" + rand2 + "+" + rand3 + "+" + rand4);
                    }
                    else
                        GeneracionRandom();

                    break;
            }

        }

        //Tercer valor aleatorio elije: en que boton se ecuentra la respuesta
        switch (randCase) {

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
                Resp1.setText("" + realResp);
                Resp2.setText("" + (realResp - 1));
                Resp3.setText("" + (realResp +  1));
                break;

            case 5:
                nRand = (int) (random.nextDouble() * 2) + 1;
                Resp1.setText("" + (realResp + nRand));
                Resp2.setText("" + realResp);
                Resp3.setText("" + (realResp - (nRand)));
                break;

            case 6:
                nRand = (int) (random.nextDouble() * 2) + 1;
                Resp1.setText("" + (realResp + nRand + 1));
                Resp2.setText("" + (realResp - 1));
                Resp3.setText("" + realResp);
                break;

            case 7:
                Resp1.setText("" + realResp);
                Resp2.setText("" + (realResp - 1));
                Resp3.setText("" + (realResp +  1));
                break;

            case 8:
                nRand = (int) (random.nextDouble() * 2) + 1;
                Resp1.setText("" + (realResp + nRand));
                Resp2.setText("" + realResp);
                Resp3.setText("" + (realResp - (nRand)));
                break;

            case 9:
                nRand = (int) (random.nextDouble() * 2) + 1;
                Resp1.setText("" + (realResp + nRand + 1));
                Resp2.setText("" + (realResp - 1));
                Resp3.setText("" + realResp);
                break;
        }
    }

    private void cacheMemory(){

        try {
            Bundle extras = getIntent().getExtras();
            FileOutputStream fos = openFileOutput(nameFile, Context.MODE_PRIVATE);
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            bestScore = (Integer) extras.get("MedioScore");
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