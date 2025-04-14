package com.example.project_samsung;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Account extends AppCompatActivity {

    private ImageButton btn_account_forum, btn_account_market, btn_account_course;
    private ImageView image_view_account;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        btn_account_market = findViewById(R.id.imageButtonAccountMarket);
        btn_account_forum = findViewById(R.id.imageButtonAccountForum);
        btn_account_course = findViewById(R.id.imageButtonAccountCourse);
        image_view_account = findViewById(R.id.imageViewAccount);

        image_view_account.setImageResource(R.drawable.forum);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(200, 150); // Ширина и высота в пикселях
        image_view_account.setLayoutParams(params);

        Intent intent_forum = new Intent(this, Forum.class);
        Intent intent_course = new Intent(this, Course.class);
        Intent intent_market = new Intent(this, Market.class);

        btn_account_market.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent_market);
            }
        });
        btn_account_forum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent_forum);
            }
        });
        btn_account_course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent_course);
            }
        });
    }
}