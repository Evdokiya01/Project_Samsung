package com.example.project_samsung;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.annotation.NonNull;


public class LogUp extends AppCompatActivity {
    private FirebaseFirestore db;
    private EditText editText_LogUp_Email, editText_Number_Password_LogUp, editText_Number_Password_Confirmation_LogUp;
    private Button btn_up;
    private CollectionReference collectionUsers;
    private static final String TAG = "LogUpActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_up);

        btn_up = findViewById(R.id.buttonLogUp);
        db = FirebaseFirestore.getInstance();
        editText_LogUp_Email = findViewById(R.id.editTextLogUpEmail);
        editText_Number_Password_LogUp = findViewById(R.id.editTextNumberPasswordLogUp);
        editText_Number_Password_Confirmation_LogUp = findViewById(R.id.editTextNumberPasswordConfirmationLogUp);
        collectionUsers = db.collection("Users");


        btn_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editText_LogUp_Email.getText().toString().trim();
                String password = editText_Number_Password_LogUp.getText().toString().trim();
                String password_confirmation = editText_Number_Password_Confirmation_LogUp.getText().toString().trim();

                if (email.isEmpty() || password.isEmpty() || password_confirmation.isEmpty()) {
                    Toast.makeText(LogUp.this, "Пожалуйста, заполните все поля", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!password.equals(password_confirmation)) {
                    Toast.makeText(LogUp.this, "Пароли не совпадают", Toast.LENGTH_SHORT).show();
                    return;
                }

                User newUser = new User(email, password_confirmation);
                doSave(newUser);
            }
        });
    }

    private void doSave(User user) {
        collectionUsers.add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "Данные пользователя успешно сохранены в Firestore с ID: " + documentReference.getId());
                        Toast.makeText(LogUp.this, "Регистрация успешна!", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(LogUp.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Ошибка при сохранении данных пользователя в Firestore", e);
                        Toast.makeText(LogUp.this, "Ошибка при регистрации: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}