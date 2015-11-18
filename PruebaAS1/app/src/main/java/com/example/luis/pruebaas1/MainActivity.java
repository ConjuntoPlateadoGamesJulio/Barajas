package com.example.luis.pruebaas1;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView image;
    ImageButton boton;
    ImageButton boton2;
    Button boton3;
    EditText text;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        titleActivity();
        //mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.sound);
        //mediaPlayer.start();
        //Igualacion de objeto Java con Objeto Xml
        boton = (ImageButton) findViewById(R.id.button);
        boton2 = (ImageButton) findViewById(R.id.settings_button);
        boton3 = (Button) findViewById(R.id.credits_button);

        //Esperan el click
        boton.setOnClickListener(this);
        boton2.setOnClickListener(this);
        boton3.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
        switch (v.getId()) {

            //El id del boton seleccionado es v.getId
            case R.id.button:
                //Se manda a llamar la Facil
                Intent intent = new Intent(MainActivity.this,Niveles.class);
                startActivity(intent);
                break;

            //Esto también
            case R.id.settings_button:
               // Toast.makeText(getApplicationContext(),R.string.Pao,Toast.LENGTH_SHORT).show();
                Intent settings = new Intent(MainActivity.this,Configuracion.class);
                startActivity(settings);
                break;

            case  R.id.credits_button:
                Intent credits = new Intent(MainActivity.this,Creditos.class);
                startActivity(credits);
                break;
        }
    }
    private  void titleActivity(){
        this.setTitle(getResources().getString(R.string.app_name));
    }

}
