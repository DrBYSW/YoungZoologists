package com.example.youngzoologists;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class ChapterSelectionActivity extends AppCompatActivity {

    private Button btnBack;
    private LinearLayout btnChapter1;
    private LinearLayout btnChapter2;
    private LinearLayout btnChapter3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_chapter_selection);

        btnChapter1 = findViewById(R.id.btnChapter1);
        btnChapter2 = findViewById(R.id.btnChapter2);
        btnChapter3 = findViewById(R.id.btnChapter3);
        btnBack = findViewById(R.id.btnBack);

        btnChapter1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                redirectTo(Chapter1Activity.class);
                Log.e("TAG", "onClick: go to chapter 1 activity");
            }
        });

        btnChapter2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                redirectTo(Chapter2Activity.class);
                Log.e("TAG", "onClick: go to chapter 2 activity");
            }
        });

        btnChapter3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                redirectTo(Chapter3Activity.class);
                Log.e("TAG", "onClick: go to chapter 3 activity");
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectTo(MainMenuActivity.class);
            }
        });

    }

    private void redirectTo(Class final_dest){
        Intent intent = new Intent(ChapterSelectionActivity.this, final_dest);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }


}
