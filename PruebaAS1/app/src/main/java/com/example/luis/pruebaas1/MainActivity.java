package com.example.luis.pruebaas1;

import android.app.ActionBar;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView image;
    ImageButton boton;
    ImageButton boton2,cerrar;
    Button boton3;
    Locale myLocale;
    EditText text;
    MediaPlayer mediaPlayer;
    int primera = 0;
    String nameFile = "lenguaje.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hideStatusBar();
        titleActivity();
        primera = 0;
        if (primera == 0) {
            lenguajePosicion();
            setContentView(R.layout.activity_main);
            primera = 7;
        }
        //mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.sound);
        //mediaPlayer.start();
        //Igualacion de objeto Java con Objeto Xml
        boton = (ImageButton) findViewById(R.id.button);
        boton2 = (ImageButton) findViewById(R.id.settings_button);
        boton3 = (Button) findViewById(R.id.credits_button);
        cerrar = (ImageButton) findViewById(R.id.salir);

        //Esperan el click
        boton.setOnClickListener(this);
        boton2.setOnClickListener(this);
        boton3.setOnClickListener(this);
        cerrar.setOnClickListener(this);
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
            case R.id.salir:
                finish();
                break;
        }
    }
    private  void titleActivity(){
        this.setTitle(getResources().getString(R.string.app_name));
    }

    // solo es una prueba por si lo ocupamos
    private void hideStatusBar(){
        try {
            View decorView = getWindow().getDecorView();
// Hide the status bar.
            int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }catch (NullPointerException e){}
    }int posicionLenguaje = 1;

    public void lenguajePosicion(){
        try {
            FileInputStream fis = openFileInput(nameFile);
            InputStreamReader isr = new InputStreamReader(fis);
            posicionLenguaje = isr.read();
            //Toast.makeText(getApplication(), "" + posicionLenguaje, Toast.LENGTH_SHORT).show();
            if (posicionLenguaje == 1){
                setLocale("");
            }
            if (posicionLenguaje == 2){
                setLocale("fr");
            }
            if (posicionLenguaje == 3){
                setLocale("en");
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    public void setLocale(String lang) {
        myLocale = new Locale(lang);
        Locale.setDefault(myLocale);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        if (primera == 7) {
            Intent refresh = new Intent(this, MainActivity.class);
            primera = 9;
            startActivity(refresh);
        }
    }
}