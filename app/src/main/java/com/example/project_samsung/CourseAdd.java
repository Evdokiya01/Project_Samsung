package com.example.project_samsung;

import android.annotation.SuppressLint;
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

import com.example.project_samsung.FirbaseClass.Courses;
import com.example.project_samsung.FirbaseClass.Forums;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

public class CourseAdd extends AppCompatActivity {

    private ImageButton imageButtonAddCourseMarket, imageButtonAddCourseForum, imageButtonAddCourseCourse, imageButtonAddCourseAccount;
    private Button AddCourseButtonPublish;
    private EditText editTextAddCourseName, editTextAddCoursePrice;
    private String login;
    private CollectionReference collectionUsers;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_add);

        imageButtonAddCourseMarket = findViewById(R.id.imageButtonAddCourseMarket);
        imageButtonAddCourseForum = findViewById(R.id.imageButtonAddCourseForum);
        imageButtonAddCourseCourse = findViewById(R.id.imageButtonAddCourseCourse);
        imageButtonAddCourseAccount = findViewById(R.id.imageButtonAddCourseAccount);

        String url_account = "https://papik.pro/grafic/uploads/posts/2023-04/1681528233_papik-pro-p-lichnii-logotip-vektor-4.png";
        String url_forum = "https://www.pngarts.com/files/17/Forum-PNG-Pic-HQ.png";
        String url_course = "https://avatars.mds.yandex.net/i?id=5d5c4f1aa3a701195ce40beb93706ade661ab434-9181645-images-thumbs&n=13";
        String url_market = "https://banner2.cleanpng.com/20180531/ioi/kisspng-computer-icons-convenience-shop-5b0f92207789c8.4262469815277471044896.jpg";

        Picasso.get().load(url_account).fit().centerCrop().into(imageButtonAddCourseAccount);
        Picasso.get().load(url_forum).fit().centerCrop().into(imageButtonAddCourseForum);
        Picasso.get().load(url_course).fit().centerCrop().into(imageButtonAddCourseCourse);
        Picasso.get().load(url_market).fit().centerCrop().into(imageButtonAddCourseMarket);

        AddCourseButtonPublish = findViewById(R.id.AddCourseButtonPublish);

        editTextAddCourseName = findViewById(R.id.editTextAddCourseName);
        editTextAddCoursePrice = findViewById(R.id.editTextAddCoursePrice);

        Intent intent_account = new Intent(this, Account.class);
        Intent intent_course = new Intent(this, Course.class);
        Intent intent_market = new Intent(this, Market.class);
        Intent intent_forum = new Intent(this, Forum.class);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        imageButtonAddCourseAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent_account);
                finish();
            }
        });

        imageButtonAddCourseForum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent_forum);
                finish();
            }
        });
        imageButtonAddCourseMarket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent_market);
                finish();
            }
        });

        imageButtonAddCourseCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent_course);
                finish();
            }
        });
        AddCourseButtonPublish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTextAddCourseName.getText().toString().trim();
                String price = editTextAddCoursePrice.getText().toString().trim();


                Courses newCourses = new Courses(name, price);
                doSave(newCourses);
            }
        });
    }

    private void doSave(Courses courses) {
        collectionUsers = FirebaseFirestore.getInstance().collection("courses");
        collectionUsers.add(courses)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(CourseAdd.this, "Сохранение данных успешно!", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(CourseAdd.this, Course.class);
                        startActivity(intent);
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("Ошибка при сохранении данных в Firestore", e);
                        Toast.makeText(CourseAdd.this, "Ошибка при сохранении данных: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}