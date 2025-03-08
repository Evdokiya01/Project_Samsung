package com.example.project_samsung;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;

public class LogUp extends MainActivity{
    private Button btn_up;
    private Bundle savedInstanceState;

    protected void onCreate() {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        btn_up = findViewById(R.id.buttonLogUp);
        TextView text = findViewById(R.id.textView15);
        text.setText("lalala");
        btn_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.log_in);
            }
        });

    }
}
