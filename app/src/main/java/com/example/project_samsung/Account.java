package com.example.project_samsung;

import android.annotation.SuppressLint;
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

    private ImageButton btn_account_forum, btn_account_market, btn_account_course, btn_account_account;
    private ImageView image_view_account;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_account);

        btn_account_market = findViewById(R.id.imageButtonAccountMarket);
        btn_account_forum = findViewById(R.id.imageButtonAccountForum);
        btn_account_course = findViewById(R.id.imageButtonAccountCourse);
        btn_account_account = findViewById(R.id.imageButtonAccountAccount);
        image_view_account = findViewById(R.id.imageViewAccount);

        image_view_account.setImageResource(R.drawable.forum);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(200, 150); // Ширина и высота в пикселях
        image_view_account.setLayoutParams(params);

        btn_account_market.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.product);
            }
        });
        btn_account_forum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.forum);
            }
        });
        btn_account_course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.courses_singular);
            }
        });
    }
}