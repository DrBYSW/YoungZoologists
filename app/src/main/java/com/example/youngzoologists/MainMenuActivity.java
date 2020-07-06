package com.example.youngzoologists;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainMenuActivity extends AppCompatActivity {
    private Button btnStart;
    private Button btnOptions;
    private Button btnExit;
    private AlertDialog.Builder builder;
    public static MediaPlayer mediaPlayer;
    public static boolean sActive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_menu);

        mediaPlayer = playsoundtrack(R.raw.main_soundtrack_standin);



//        btnStart = findViewById(R.id.btnStart);
        btnOptions = findViewById(R.id.btnOptions);
//        btnExit = findViewById(R.id.btnExit);
//
//        btnStart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                redirectTo(ChapterSelectionActivity.class);
//            }
//        });

        btnOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectTo(OptionsActivity.class);
            }
        });
//
//        btnExit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showExitDialog();
//            }
//        });

    }

    @Override
    protected void onResume() {

        // starting the player if it is not playing
        if (!mediaPlayer.isPlaying()) {
            mediaPlayer.start();
            mediaPlayer.setLooping(true);
        }

        // true when activity is active
        sActive = true;
        super.onResume();
    }

    @Override
    protected void onPause() {

        sActive = false;
        super.onPause();
    }

    @Override
    protected void onStop() {

        if (mediaPlayer.isPlaying()
                && !(OptionsActivity.sActive || ChapterSelectionActivity.sActive)) {
            mediaPlayer.pause();
        }

        super.onStop();

    }

    private void showExitDialog(){
        builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.exit_message)
                .setTitle(R.string.app_name)
                .setPositiveButton("Leave", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialogClick(dialog, 1);
                    }
                })
                .setNegativeButton("Stay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialogClick(dialog, 0);
                    }
                });
        AlertDialog alert = builder.create();

        alert.show();
    }

    private void dialogClick(DialogInterface dialog, int result){
        switch (result) {
            case 1:
                finish();
                break;
            default:
                dialog.cancel();
                break;
        }
    }

    private void redirectTo(Class final_dest){
        Intent intent = new Intent(MainMenuActivity.this, final_dest);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    private MediaPlayer playsoundtrack(int soundtrack){
        MediaPlayer mediaPlayer = MediaPlayer.create(this, soundtrack);
        mediaPlayer.setVolume(0.02f,0.02f);
        mediaPlayer.start();

        return mediaPlayer;
    }

}
