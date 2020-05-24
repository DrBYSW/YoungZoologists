package com.example.youngzoologists;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class OptionsActivity extends AppCompatActivity {

    SeekBar sbMaster;
    Button btnBack;
    public static MediaPlayer mediaPlayer;
    public Float[] volumeArray = { 0.00f, 0.005f, 0.01f, 0.02f, 0.05f };
    static boolean sActive;

    @Override
    protected void onPause() {
        sActive = false;

        super.onPause();
    }

    @Override
    protected void onStop() {

        // pausing the player in case of exiting from the app
        if (MainMenuActivity.mediaPlayer.isPlaying() && !(MainMenuActivity.sActive || ChapterSelectionActivity.sActive)) {
            MainMenuActivity.mediaPlayer.pause();
        }

        super.onStop();
    }

    @Override
    protected void onResume() {
        sActive = true;

        if (!MainMenuActivity.mediaPlayer.isPlaying()) {
            MainMenuActivity.mediaPlayer.start();
            MainMenuActivity.mediaPlayer.setLooping(true);
        }

        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_options);
        mediaPlayer = MainMenuActivity.mediaPlayer;
        mediaPlayer.start();
        btnBack = findViewById(R.id.btnBack);
        sbMaster = findViewById(R.id.sbMaster);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectTo(MainMenuActivity.class);
            }
        });

        sbMaster.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChangedValue = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                changeVolume(mediaPlayer, progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


    private void changeVolume(MediaPlayer mediaPlayer, int progress){
        float vol = volumeArray[progress];
        mediaPlayer.setVolume(vol, vol);
    }

    private void redirectTo(Class final_dest){
        Intent intent = new Intent(OptionsActivity.this, final_dest);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
