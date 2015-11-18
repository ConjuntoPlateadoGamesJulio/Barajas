package com.example.luis.pruebaas1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.buttonFacil:
                finish();
                Intent facil = new Intent(Niveles.this,Facil.class);
                startActivity(facil);
                break;

            case R.id.buttonMedio:
                finish();
                Intent medio = new Intent(Niveles.this,Medio.class);
                startActivity(medio);
                break;

            case R.id.buttonDificil:
                finish();
                Intent dificil = new Intent(Niveles.this,Dificil.class);
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
}
