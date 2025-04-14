package com.example.project_samsung;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;



public class MainActivity extends AppCompatActivity {

    private Button btn_log_in;
    private TextView textView;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Status statuss = new Status();
        if (statuss.status == 0){
            setContentView(R.layout.log_in);


            setContentView(R.layout.log_in);
            btn_log_in = findViewById(R.id.buttonLogIn);
            textView = findViewById(R.id.textViewLogInUp);

            Intent intent_account = new Intent(this, Account.class);
            Intent intent_log_up = new Intent(this, LogUp.class);

            btn_log_in.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    statuss.status = 1;
                    startActivity(intent_account);
                }
            });
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(intent_log_up);
                }
            });
        }
        else{
            Intent intent_account = new Intent(this, Account.class);
            startActivity(intent_account);
        }
    }
}