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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Forum extends AppCompatActivity {

    private ImageButton imageButtonForumMarket, imageButtonForumCourse, imageButtonForumAccount, imageButtonForumForum;
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
        imageButtonForumForum = findViewById(R.id.imageButtonForumForum);

        String url_account = "https://papik.pro/grafic/uploads/posts/2023-04/1681528233_papik-pro-p-lichnii-logotip-vektor-4.png";
        String url_forum = "https://www.pngarts.com/files/17/Forum-PNG-Pic-HQ.png";
        String url_course = "https://avatars.mds.yandex.net/i?id=5d5c4f1aa3a701195ce40beb93706ade661ab434-9181645-images-thumbs&n=13";
        String url_market = "https://banner2.cleanpng.com/20180531/ioi/kisspng-computer-icons-convenience-shop-5b0f92207789c8.4262469815277471044896.jpg";

        Picasso.get().load(url_account).fit().centerCrop().into(imageButtonForumAccount);
        Picasso.get().load(url_forum).fit().centerCrop().into(imageButtonForumForum);
        Picasso.get().load(url_course).fit().centerCrop().into(imageButtonForumCourse);
        Picasso.get().load(url_market).fit().centerCrop().into(imageButtonForumMarket);

        forumButtonPlus = findViewById(R.id.forumButtonPlus);

        Intent intent_account = new Intent(this, Account.class);
        Intent intent_course = new Intent(this, Course.class);
        Intent intent_market = new Intent(this, Market.class);
        Intent intent_add_forum = new Intent(this, ForumAdd.class);

        recyclerViewForum = findViewById(R.id.recyclerViewCourse);
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