package com.example.project_samsung;

import android.annotation.SuppressLint;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btn_log_in, btn_up;
    private TextView textView;
    private ImageButton btn_account_forum, btn_account_market, btn_account_course, btn_account_account;
    private ImageView image_view_account;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        logIn();
    }

    private void logUp(){
        btn_up = findViewById(R.id.buttonLogUp);
        TextView text = findViewById(R.id.textView15);
        text.setText("lalala");
        btn_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.log_in);
                logIn();
            }
        });
    }

    private void logIn(){
        setContentView(R.layout.log_in);
        btn_log_in = findViewById(R.id.buttonLogIn);
        textView = findViewById(R.id.textViewLogInUp);

        btn_log_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.account);
            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.log_up);
//                LogUp myObject = new LogUp();
//                myObject.onCreate();
                logUp();
            }
        });
    }

    @SuppressLint("MissingInflatedId")
    private void account(){
        setContentView(R.layout.account);
        btn_account_market = findViewById(R.id.imageButtonAccountMarket);
        btn_account_forum = findViewById(R.id.imageButtonAccountForum);
        btn_account_course = findViewById(R.id.imageButtonAccountCourse);
        btn_account_account = findViewById(R.id.imageButtonAccountAccount);
        image_view_account = findViewById(R.id.imageViewAccount);

        //image_view_account.setImageResource(R.drawable.forum);


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
        btn_account_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                account();
            }
        });
    }
}