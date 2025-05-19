package com.example.project_samsung;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_samsung.Adapters.CoursesAdapter;
import com.example.project_samsung.FirbaseClass.Courses;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Course extends AppCompatActivity {
    private ImageButton imageButtonCourseMarket, imageButtonCourseForum, imageButtonCourseAccount, imageButtonCourseCourse;
    private RecyclerView recyclerViewCourse;
    private CoursesAdapter coursesAdapter;
    private List<Courses> coursesList = new ArrayList<>();
    private FirebaseFirestore db;
    private FloatingActionButton forumButtonPlusCourse;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        imageButtonCourseMarket = findViewById(R.id.imageButtonCourseMarket);
        imageButtonCourseForum = findViewById(R.id.imageButtonCourseForum);
        imageButtonCourseAccount = findViewById(R.id.imageButtonCourseAccount);
        imageButtonCourseCourse = findViewById(R.id.imageButtonCourseCourse);
        recyclerViewCourse = findViewById(R.id.recyclerViewCourse);
        forumButtonPlusCourse = findViewById(R.id.forumButtonPlusCourse);

        Intent intent_forum = new Intent(this, Forum.class);
        Intent intent_account = new Intent(this, Account.class);
        Intent intent_market = new Intent(this, Market.class);
        Intent intent_add_course = new Intent(this, CourseAdd.class);

        String url_account = "https://papik.pro/grafic/uploads/posts/2023-04/1681528233_papik-pro-p-lichnii-logotip-vektor-4.png";
        String url_forum = "https://www.pngarts.com/files/17/Forum-PNG-Pic-HQ.png";
        String url_course = "https://avatars.mds.yandex.net/i?id=5d5c4f1aa3a701195ce40beb93706ade661ab434-9181645-images-thumbs&n=13";
        String url_market = "https://banner2.cleanpng.com/20180531/ioi/kisspng-computer-icons-convenience-shop-5b0f92207789c8.4262469815277471044896.jpg";

        Picasso.get().load(url_account).fit().centerCrop().into(imageButtonCourseAccount);
        Picasso.get().load(url_forum).fit().centerCrop().into(imageButtonCourseForum);
        Picasso.get().load(url_course).fit().centerCrop().into(imageButtonCourseCourse);
        Picasso.get().load(url_market).fit().centerCrop().into(imageButtonCourseMarket);

        recyclerViewCourse = findViewById(R.id.recyclerViewCourse);
        recyclerViewCourse.setLayoutManager(new LinearLayoutManager(this));
        coursesAdapter = new CoursesAdapter(coursesList);
        recyclerViewCourse.setAdapter(coursesAdapter);
        db = FirebaseFirestore.getInstance();
        fetchCourses();

        imageButtonCourseMarket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Activity.setActivity(this);
                startActivity(intent_market);
            }
        });

        imageButtonCourseForum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Activity.setActivity(this);
                startActivity(intent_forum);
            }
        });

        imageButtonCourseAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Activity.setActivity(this);
                startActivity(intent_account);
            }
        });
        forumButtonPlusCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Activity.setActivity(this);
                startActivity(intent_add_course);
            }
        });

    }

    private void fetchCourses() {
        db.collection("courses")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        coursesList.clear();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            String name = document.getString("name");
                            String price = document.getString("price");
                            if (name != null && price != null) {
                                coursesList.add(new Courses(name, price));
                            }
                        }
                        coursesAdapter.updateData(coursesList);
                    } else {
                        // Обработка ошибок
                    }
                });
    }
    /*
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent_last_activity = new Intent(this, Activity.getActivity().getClass());
        startActivity(intent_last_activity);
        finish();
    }
     */
}