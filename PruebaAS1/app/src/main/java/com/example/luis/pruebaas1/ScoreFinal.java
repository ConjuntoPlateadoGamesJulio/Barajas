package com.example.luis.pruebaas1;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class ScoreFinal extends ActionBarActivity {
    TextView score, bestScore;
    int bScore = 0,score2 = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_score);
        score = (TextView) findViewById(R.id.scoreFinal2);
        bestScore = (TextView) findViewById(R.id.bestScore);
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
}
