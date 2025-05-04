package com.example.project_samsung;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Course extends AppCompatActivity {
    private ImageButton imageButtonCourseMarket, imageButtonCourseForum, imageButtonCourseAccount;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        imageButtonCourseMarket = findViewById(R.id.imageButtonCourseMarket);
        imageButtonCourseForum = findViewById(R.id.imageButtonCourseForum);
        imageButtonCourseAccount = findViewById(R.id.imageButtonCourseAccount);

        Intent intent_forum = new Intent(this, Forum.class);
        Intent intent_account = new Intent(this, Account.class);
        Intent intent_market = new Intent(this, Market.class);

        imageButtonCourseMarket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent_market);
            }
        });

        imageButtonCourseForum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent_forum);
            }
        });

        imageButtonCourseAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent_account);
            }
        });

    }
}