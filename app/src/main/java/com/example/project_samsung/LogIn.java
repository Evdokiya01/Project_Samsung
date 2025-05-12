package com.example.project_samsung;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;
import com.google.firebase.auth.AuthResult;
import android.util.Log;
import android.widget.Toast;


public class LogIn extends AppCompatActivity {

    private Button btn_log_in;
    private TextView textView;
    private ImageView image_view_log_in;
    private EditText edit_text_log_in_email, edit_text_log_in_password;
    private FirebaseDatabase db;
    private DatabaseReference table, passwords;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);


        db = FirebaseDatabase.getInstance();
        table = db.getReference("Users");


        setContentView(R.layout.log_in);
        btn_log_in = findViewById(R.id.buttonLogIn);
        textView = findViewById(R.id.textViewLogInUp);
        image_view_log_in = findViewById(R.id.imageView2);
        edit_text_log_in_email = findViewById(R.id.editTextLogInEmail);
        edit_text_log_in_password = findViewById(R.id.editTextLogInPassword);

        String url = "https://papik.pro/grafic/uploads/posts/2023-04/1681528233_papik-pro-p-lichnii-logotip-vektor-4.png";
        Picasso.get().load(url).into(image_view_log_in);

        Intent intent_account = new Intent(this, Account.class);
        Intent intent_log_up = new Intent(this, LogUp.class);



        btn_log_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailInput = edit_text_log_in_email.getText().toString().trim();
                String passwordInput = edit_text_log_in_password.getText().toString().trim();

                if (emailInput.isEmpty() || passwordInput.isEmpty()) {
                    Toast.makeText(LogIn.this, "Пожалуйста, введите логин и пароль", Toast.LENGTH_SHORT).show();
                    return;
                }

                Toast.makeText(LogIn.this, "Проверка данных...", Toast.LENGTH_SHORT).show();



                Log.d("FirebaseTest", "Перед вызовом addListener");
                table.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Log.d("FirebaseUser", "мяу");
                        boolean found = false;
                        for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                            String email = childSnapshot.child("email").getValue(String.class);
                            String password = childSnapshot.child("password").getValue(String.class);
                            Log.d("FirebaseUser", "email: " + email + ", password: " + password);

                            if (email != null && email.equals(emailInput) && password != null && password.equals(passwordInput)) {
                                found = true;
                                SharedPreferences sharedPreferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                String username = childSnapshot.child("name").getValue(String.class);
                                editor.putString("username", username != null ? username : "");
                                editor.putString("login", email);
                                editor.putBoolean("isLoggedIn", true);
                                editor.apply();

                                startActivity(new Intent(LogIn.this, Account.class));
                                finish();
                                break;
                            }
                        }
                        if (!found) {
                            Toast.makeText(LogIn.this, "Неверный логин или пароль", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.d("FirebaseUser", "мяу мяу");
                        Log.e("FirebaseError", "Ошибка: " + error.getMessage());
                        Toast.makeText(LogIn.this, "Ошибка при загрузке данных", Toast.LENGTH_SHORT).show();
                    }
                });
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