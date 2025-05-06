package com.example.project_samsung;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class LogIn extends AppCompatActivity {

    private Button btn_log_in;
    private TextView textView;
    private ImageView image_view_log_in;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);

        setContentView(R.layout.log_in);


        setContentView(R.layout.log_in);
        btn_log_in = findViewById(R.id.buttonLogIn);
        textView = findViewById(R.id.textViewLogInUp);
        image_view_log_in = findViewById(R.id.imageView2);

        String url = "https://papik.pro/grafic/uploads/posts/2023-04/1681528233_papik-pro-p-lichnii-logotip-vektor-4.png";
        Picasso.get().load(url).into(image_view_log_in);

        Intent intent_account = new Intent(this, Account.class);
        Intent intent_log_up = new Intent(this, LogUp.class);

        btn_log_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("username", "имя_пользователя");
                editor.putString("login", "логин");
                editor.putBoolean("isLoggedIn", true);
                editor.apply();

                startActivity(intent_account);
                finish();
            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent_log_up);
                finish();
            }
        });

    }
}