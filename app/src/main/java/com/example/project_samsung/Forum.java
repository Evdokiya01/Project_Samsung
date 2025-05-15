package com.example.project_samsung;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Forum extends AppCompatActivity {

    private ImageButton imageButtonForumMarket, imageButtonForumCourse, imageButtonForumAccount;
    private FloatingActionButton forumButtonPlus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);

        imageButtonForumMarket = findViewById(R.id.imageButtonForumMarket);
        imageButtonForumCourse = findViewById(R.id.imageButtonForumCourse);
        imageButtonForumAccount = findViewById(R.id.imageButtonForumAccount);

        forumButtonPlus = findViewById(R.id.forumButtonPlus);

        Intent intent_account = new Intent(this, Account.class);
        Intent intent_course = new Intent(this, Course.class);
        Intent intent_market = new Intent(this, Market.class);
        Intent intent_add_forum = new Intent(this, ForumAdd.class);

        imageButtonForumAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent_account);
                finish();
            }
        });

        imageButtonForumMarket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent_market);
                finish();
            }
        });

        imageButtonForumCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent_course);
                finish();
            }
        });
        forumButtonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent_add_forum);
                finish();
            }
        });
    }
}