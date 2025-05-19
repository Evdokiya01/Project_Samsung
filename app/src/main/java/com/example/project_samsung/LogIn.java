package com.example.project_samsung;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

public class LogIn extends AppCompatActivity {

    private Button btn_log_in;
    private TextView textView;
    private ImageView image_view_log_in;
    private EditText edit_text_log_in_email, edit_text_log_in_password;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);

        auth = FirebaseAuth.getInstance();

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

                auth.signInWithEmailAndPassword(emailInput, passwordInput).addOnCompleteListener(LogIn.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser user = auth.getCurrentUser();
                                SharedPreferences sharedPreferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("login", user.getEmail());
                                editor.putBoolean("isLoggedIn", true);
                                editor.apply();

                                startActivity(intent_account);
                            } else {
                                Log.e("FirebaseAuth", "Ошибка входа", task.getException());
                                Toast.makeText(LogIn.this, "Неверный логин или пароль", Toast.LENGTH_SHORT).show();
                            }
                    }
                });
            }
        });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent_log_up);
//                Activity.setActivity(this);
            }
        });
    }
}