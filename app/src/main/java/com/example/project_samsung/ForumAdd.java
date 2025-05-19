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
import com.squareup.picasso.Picasso;

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


        image_button_add_forum_account = findViewById(R.id.imageButtonAddCourseAccount);
        image_button_add_forum_forum = findViewById(R.id.imageButtonAddCourseForum);
        image_button_add_forum_course = findViewById(R.id.imageButtonAddCourseCourse);
        image_button_add_forum_market = findViewById(R.id.imageButtonAddCourseMarket);

        String url_account = "https://papik.pro/grafic/uploads/posts/2023-04/1681528233_papik-pro-p-lichnii-logotip-vektor-4.png";
        String url_forum = "https://www.pngarts.com/files/17/Forum-PNG-Pic-HQ.png";
        String url_course = "https://avatars.mds.yandex.net/i?id=5d5c4f1aa3a701195ce40beb93706ade661ab434-9181645-images-thumbs&n=13";
        String url_market = "https://banner2.cleanpng.com/20180531/ioi/kisspng-computer-icons-convenience-shop-5b0f92207789c8.4262469815277471044896.jpg";

        Picasso.get().load(url_account).fit().centerCrop().into(image_button_add_forum_account);
        Picasso.get().load(url_forum).fit().centerCrop().into(image_button_add_forum_forum);
        Picasso.get().load(url_course).fit().centerCrop().into(image_button_add_forum_course);
        Picasso.get().load(url_market).fit().centerCrop().into(image_button_add_forum_market);

        button_publish = findViewById(R.id.AddCourseButtonPublish);

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
//                Activity.setActivity(this);
            }
        });

        image_button_add_forum_forum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent_forum);
//                Activity.setActivity(this);
            }
        });
        image_button_add_forum_market.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent_market);
//                Activity.setActivity(this);
            }
        });

        image_button_add_forum_course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent_course);
//                Activity.setActivity(this);
            }
        });
        button_publish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String topic = edit_text_add_forum_topic.getText().toString().trim();
                String content = edit_text_add_forum_content.getText().toString().trim();

                FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                login = currentUser.getEmail();
//                Activity.setActivity(this);

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
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("Ошибка при сохранении данных в Firestore", e);
                        Toast.makeText(ForumAdd.this, "Ошибка при сохранении данных: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }/*
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent_last_activity = new Intent(this, Activity.getActivity().getClass());
        startActivity(intent_last_activity);
        finish();
    }*/
}