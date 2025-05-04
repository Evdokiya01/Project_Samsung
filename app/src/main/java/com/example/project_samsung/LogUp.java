package com.example.project_samsung;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;



public class LogUp extends AppCompatActivity {
    private FirebaseFirestore db;
    private EditText editText_LogUp_Email, editText_Number_Password_LogUp, editText_Number_Password_Confirmation_LogUp;
    private Button btn_up;
    private CollectionReference collectionUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_up);

        btn_up = findViewById(R.id.buttonLogUp);
        TextView text = findViewById(R.id.textView15);
        db = FirebaseFirestore.getInstance();
        editText_LogUp_Email = findViewById(R.id.editTextLogUpEmail);
        editText_Number_Password_LogUp = findViewById(R.id.editTextNumberPasswordLogUp);
        editText_Number_Password_Confirmation_LogUp = findViewById(R.id.editTextNumberPasswordConfirmationLogUp);

        Intent intent = new Intent(this, MainActivity.class);

        btn_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editText_LogUp_Email.getText().toString();
                String password = editText_Number_Password_LogUp.getText().toString();
                String password_confirmation = editText_Number_Password_Confirmation_LogUp.getText().toString();
                doSave(new User(email, password_confirmation));
                startActivity(intent);
                finish();
            }
        });
    }
    private void doSave(User user) {
        collectionUsers = db.collection("Users");
        collectionUsers.add(user);
    }
}