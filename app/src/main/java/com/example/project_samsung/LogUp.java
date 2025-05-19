package com.example.project_samsung;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class LogUp extends AppCompatActivity {

    private FirebaseAuth auth;
    private EditText editText_LogUp_Email, editText_Number_Password_LogUp, editText_Number_Password_Confirmation_LogUp;
    private Button btn_up;

    private static final String TAG = "LogUpActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_up);

        auth = FirebaseAuth.getInstance();

        btn_up = findViewById(R.id.buttonLogUp);
        editText_LogUp_Email = findViewById(R.id.editTextLogUpEmail);
        editText_Number_Password_LogUp = findViewById(R.id.editTextNumberPasswordLogUp);
        editText_Number_Password_Confirmation_LogUp = findViewById(R.id.editTextNumberPasswordConfirmationLogUp);

        btn_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editText_LogUp_Email.getText().toString().trim();
                String password = editText_Number_Password_LogUp.getText().toString().trim();
                String passwordConfirmation = editText_Number_Password_Confirmation_LogUp.getText().toString().trim();

                if (email.isEmpty() || password.isEmpty() || passwordConfirmation.isEmpty()) {
                    Toast.makeText(LogUp.this, "Пожалуйста, заполните все поля", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!password.equals(passwordConfirmation)) {
                    Toast.makeText(LogUp.this, "Пароли не совпадают", Toast.LENGTH_SHORT).show();
                    return;
                }

                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(LogUp.this, task -> {
                            if (task.isSuccessful()) {
                                FirebaseUser user = auth.getCurrentUser();
                                Log.d(TAG, "Регистрация успешна: " + user.getUid());
                                Toast.makeText(LogUp.this, "Регистрация успешна!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LogUp.this, MainActivity.class);
                                startActivity(intent);
                            } else {
                                Log.w(TAG, "Ошибка регистрации", task.getException());
                                Toast.makeText(LogUp.this, "Ошибка регистрации: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }/*
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent_last_activity = new Intent(this, Activity.getActivity().getClass());
        startActivity(intent_last_activity);
        finish();
    }
    */
}