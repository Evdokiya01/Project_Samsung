package com.example.project_samsung;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_samsung.Adapters.ForumsAdapter;
import com.example.project_samsung.FirbaseClass.Forums;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class Forum extends AppCompatActivity {

    private ImageButton imageButtonForumMarket, imageButtonForumCourse, imageButtonForumAccount;
    private FloatingActionButton forumButtonPlus;
    private RecyclerView recyclerViewForum;
    private ForumsAdapter forumsAdapter;
    private List<Forums> forumsList = new ArrayList<>();
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);

        imageButtonForumMarket = findViewById(R.id.imageButtonForumMarket);
        imageButtonForumCourse = findViewById(R.id.imageButtonForumCourse);
        imageButtonForumAccount = findViewById(R.id.imageButtonForumAccount);

        forumButtonPlus = findViewById(R.id.forumButtonPlus);

        Intent intent_account = new Intent(this, Account.class);
        Intent intent_course = new Intent(this, Course.class);
        Intent intent_market = new Intent(this, Market.class);
        Intent intent_add_forum = new Intent(this, ForumAdd.class);

        recyclerViewForum = findViewById(R.id.recyclerViewForum);
        recyclerViewForum.setLayoutManager(new LinearLayoutManager(this));
        forumsAdapter = new ForumsAdapter(forumsList);
        recyclerViewForum.setAdapter(forumsAdapter);
        db = FirebaseFirestore.getInstance();
        fetchForums();

        imageButtonForumAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent_account);
                finish();
            }
        });

        imageButtonForumMarket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent_market);
                finish();
            }
        });

        imageButtonForumCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent_course);
                finish();
            }
        });
        forumButtonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent_add_forum);
                finish();
            }
        });
    }
    private void fetchForums() {
        db.collection("forum")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        forumsList.clear();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            String topic = document.getString("topic");
                            String content = document.getString("content");
                            String login = document.getString("login");
                            if (topic != null && content != null && login != null) {
                                forumsList.add(new Forums(topic, content, login));
                            }
                        }
                        forumsAdapter.updateData(forumsList);
                    } else {
                        // Обработка ошибок
                    }
                });
    }
}