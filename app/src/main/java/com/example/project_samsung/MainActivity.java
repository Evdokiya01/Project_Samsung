package com.example.project_samsung;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    private Button btn_log_in;
    private TextView textView;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPreferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
        boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);

        if (isLoggedIn) {
            // Пользователь уже вошел, переход на главный экран
            Intent intent_account = new Intent(this, Account.class);
            startActivity(intent_account);
            finish();
        } else {
            // Пользователь не вошел, показать экран авторизации
            Intent intent_account = new Intent(this, LogIn.class);
            startActivity(intent_account);
            finish();
        }
    }
}