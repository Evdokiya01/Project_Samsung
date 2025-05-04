package com.example.project_samsung;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Market extends AppCompatActivity {

    private ImageButton btn_market_forum, btn_market_account, btn_market_course;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market);

        btn_market_forum = findViewById(R.id.imageButtonMarketForum);
        btn_market_account = findViewById(R.id.imageButtonMarketAccount);
        btn_market_course = findViewById(R.id.imageButtonMarketCourse);

        Intent intent_forum = new Intent(this, Forum.class);
        Intent intent_course = new Intent(this, Course.class);
        Intent intent_account = new Intent(this, Account.class);

        btn_market_forum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent_forum);
            }
        });
        btn_market_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent_account);
            }
        });
        btn_market_course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent_course);
            }
        });
    }
}