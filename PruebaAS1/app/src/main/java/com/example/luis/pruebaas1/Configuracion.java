package com.example.luis.pruebaas1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.LabeledIntent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ExpandableListView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Locale;


/**
 * Created by Carlos on 01/10/2015.
 */
public class Configuracion extends AppCompatActivity {
    private SeekBar seekBar;
    private TextView textView;
    private AudioManager audioManager;
    private MediaPlayer mediaPlayer;
    private Spinner spinnerctrl;
    Button btn;
    private CheckBox mute;
    private boolean m;
    Locale myLocale;
    private SharedPreferences prefs;
    private int progress = 0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        initializeVariable();
        // Inicializa funciones de audio
        //mediaPlayer = MediaPlayer.create(Configuracion.this,R.raw.sound);
        //mediaPlayer.start();
        Volumen();
        lenguajes();
        chekBoxVolume();
        titleActivity();
       /* super.onCreate(savedInstanceState);
        Button back=(Button) findViewById(R.id.buttonBack);
        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Configuracion.this, MainActivity.class);
                startActivity(intent);
                onBackPressed();
            }
        });*/

    }

    private void Volumen() {

        try {
            seekBar = (SeekBar)findViewById(R.id.seekBar1);
            audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
            seekBar.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
            seekBar.setProgress(audioManager.getStreamVolume(AudioManager.STREAM_MUSIC));

            //textView.setText("Covered: " + seekBar.getProgress() + "/" + seekBar.getMax());
            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

                @Override
                public void onStopTrackingTouch(SeekBar arg0) {
                    //textView.setText("Covered: " + progress + "/" + seekBar.getMax());
                }

                @Override
                public void onStartTrackingTouch(SeekBar arg0) {
                }

                @Override
                public void onProgressChanged(SeekBar arg0, int progresValue, boolean arg2) {
                    progress = progresValue;
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
                    if(progress>0){
                        mute.setChecked(false);
                    }else {mute.setChecked(true);}
                }
            });
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
        // Un metodo privado para ayudar a inicializar las variable
    private void initializeVariable(){

        seekBar = (SeekBar) findViewById(R.id.seekBar1);
        textView =(TextView) findViewById(R.id.textView1);
    }

    private   void lenguajes(){
        spinnerctrl = (Spinner) findViewById(R.id.spinner1);
        spinnerctrl.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {

                if (pos == 2) {
                    setLocale("");
                } else if (pos == 3) {
                    setLocale("fr");
                } else if (pos == 4) {
                    setLocale("en");
                }

            }

            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }

        });

    }
    public void setLocale(String lang) {
        myLocale = new Locale(lang);
        Locale.setDefault(myLocale);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        Intent refresh = new Intent(this, Configuracion.class);
        startActivity(refresh);

    }
    private void chekBoxVolume(){
        prefs = getSharedPreferences("mute", 0);
        m = prefs.getBoolean("mute", false);
        mute= (CheckBox) findViewById(R.id.checkBox);
        mute.setChecked(m);
        mute.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.isChecked() == true) {
                    m = true;
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putBoolean("mute", true);
                    editor.commit();
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 0, 0);
                    Volumen();
                }
                if (buttonView.isChecked() == false) {
                    m = false;
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putBoolean("mute", false);
                    editor.commit();
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 7, 0);
                    Volumen();
                }

            }
        });
    }
    //back button refresca la pantalla principal al presionarlo
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent a = new Intent(this,MainActivity.class);
            a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(a);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private  void titleActivity(){
        this.setTitle(getResources().getString(R.string.configuracion));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_settings, menu);
        return true;
    }
}

