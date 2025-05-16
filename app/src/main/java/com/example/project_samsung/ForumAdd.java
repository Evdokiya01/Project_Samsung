package com.example.project_samsung;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.project_samsung.FirbaseClass.Forums;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class ForumAdd extends AppCompatActivity {

    private ImageButton image_button_add_forum_account, image_button_add_forum_forum, image_button_add_forum_course, image_button_add_forum_market;
    private Button button_publish;
    private EditText edit_text_add_forum_topic, edit_text_add_forum_content;
    private String login;
    private CollectionReference collectionUsers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_add);

        image_button_add_forum_account = findViewById(R.id.imageButtonAddForumAccount);
        image_button_add_forum_forum = findViewById(R.id.imageButtonAddForumForum);
        image_button_add_forum_course = findViewById(R.id.imageButtonAddForumCourse);
        image_button_add_forum_market = findViewById(R.id.imageButtonAddForumMarket);

        button_publish = findViewById(R.id.AddForumButtonPublish);

        edit_text_add_forum_topic = findViewById(R.id.editTextAddForumTopic);
        edit_text_add_forum_content = findViewById(R.id.editTextAddForumContent);

        Intent intent_account = new Intent(this, Account.class);
        Intent intent_course = new Intent(this, Course.class);
        Intent intent_market = new Intent(this, Market.class);
        Intent intent_forum = new Intent(this, Forum.class);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        image_button_add_forum_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent_account);
                finish();
            }
        });

        image_button_add_forum_forum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent_forum);
                finish();
            }
        });
        image_button_add_forum_market.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent_market);
                finish();
            }
        });

        image_button_add_forum_course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent_course);
                finish();
            }
        });
        button_publish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String topic = edit_text_add_forum_topic.getText().toString().trim();
                String content = edit_text_add_forum_content.getText().toString().trim();

                FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                login = currentUser.getEmail();

                Forums newForums = new Forums(topic, content, login);
                doSave(newForums);
            }
        });
    }

    private void doSave(Forums forum) {
        collectionUsers = FirebaseFirestore.getInstance().collection("forum");
        collectionUsers.add(forum)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(ForumAdd.this, "Сохранение данных успешно!", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(ForumAdd.this, Forum.class);
                        startActivity(intent);
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("Ошибка при сохранении данных в Firestore", e);
                        Toast.makeText(ForumAdd.this, "Ошибка при сохранении данных: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}