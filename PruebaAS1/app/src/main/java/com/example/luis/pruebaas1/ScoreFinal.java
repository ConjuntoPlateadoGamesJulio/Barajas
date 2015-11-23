package com.example.luis.pruebaas1;

import android.media.MediaPlayer;
import android.os.SystemClock;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;


public class ScoreFinal extends ActionBarActivity {
    TextView score, bestScore;
    int bScore = 0,score2 = 0;
    ImageView bala1,bala2,bala3,bala4;
    Chronometer cronometro ;
    private MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_score);
        hideStatusBar();
        score = (TextView) findViewById(R.id.scoreFinal2);
        bestScore = (TextView) findViewById(R.id.bestScore);
        bala1 = (ImageView) findViewById(R.id.bala1);
        bala2 = (ImageView) findViewById(R.id.bala2);
        bala3 = (ImageView) findViewById(R.id.bala3);
        bala4 = (ImageView) findViewById(R.id.bala4);
        bala4.setVisibility(View.INVISIBLE);
        bala1.setVisibility(View.INVISIBLE);
        bala2.setVisibility(View.INVISIBLE);
        bala3.setVisibility(View.INVISIBLE);
        cronometro = (Chronometer) findViewById(R.id.chronometer) ;
        mediaPlayer = MediaPlayer.create(ScoreFinal.this, R.raw.shotgun);
        Cronometro();
        Bundle extras = getIntent().getExtras();
        bScore = (Integer) extras.get("BestScore");
        score2 = (Integer) extras.get("Score");
        score.setText(""+score2);
        bestScore.setText(""+bScore);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_final_score, menu);
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

    private void Cronometro() {
        //Inciar cronometro
        cronometro.setBase(SystemClock.elapsedRealtime());
        cronometro.start();
        mediaPlayer.start();
        cronometro.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {

            @Override
            public void onChronometerTick(Chronometer chronometer) {
                long elapsedTime = SystemClock.elapsedRealtime() - cronometro.getBase();
                mediaPlayer.start();
                if (elapsedTime > 10) {
                    bala1.setVisibility(View.VISIBLE);

                }
                if (elapsedTime > 400) {
                    bala2.setVisibility(View.VISIBLE);
                }
                if (elapsedTime > 950) {
                    bala4.setVisibility(View.VISIBLE);
                }
                if (elapsedTime > 1650) {
                    bala3.setVisibility(View.VISIBLE);
                    mediaPlayer.stop();
                    cronometro.stop();
                }
            }

        });
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
