package com.example.project_samsung;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_samsung.Adapters.MarketAdapter;
import com.example.project_samsung.FirbaseClass.Markets;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Market extends AppCompatActivity {

    private ImageButton btn_market_forum, btn_market_account, btn_market_course, imageButtonMarketMarket;
    private RecyclerView recyclerViewMarket;
    private List<Markets> marketsList = new ArrayList<>();
    private MarketAdapter marketAdapter;
    private FirebaseFirestore db;
    private FloatingActionButton forumButtonPlusMarket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market);


        btn_market_forum = findViewById(R.id.imageButtonMarketForum);
        btn_market_account = findViewById(R.id.imageButtonMarketAccount);
        btn_market_course = findViewById(R.id.imageButtonMarketCourse);
        forumButtonPlusMarket = findViewById(R.id.forumButtonPlusMarket);
        imageButtonMarketMarket = findViewById(R.id.imageButtonMarketMarket);

        String url_account = "https://papik.pro/grafic/uploads/posts/2023-04/1681528233_papik-pro-p-lichnii-logotip-vektor-4.png";
        String url_forum = "https://www.pngarts.com/files/17/Forum-PNG-Pic-HQ.png";
        String url_course = "https://avatars.mds.yandex.net/i?id=5d5c4f1aa3a701195ce40beb93706ade661ab434-9181645-images-thumbs&n=13";
        String url_market = "https://banner2.cleanpng.com/20180531/ioi/kisspng-computer-icons-convenience-shop-5b0f92207789c8.4262469815277471044896.jpg";

        Picasso.get().load(url_account).fit().centerCrop().into(btn_market_account);
        Picasso.get().load(url_forum).fit().centerCrop().into(btn_market_forum);
        Picasso.get().load(url_course).fit().centerCrop().into(btn_market_course);
        Picasso.get().load(url_market).fit().centerCrop().into(imageButtonMarketMarket);

        Intent intent_forum = new Intent(this, Forum.class);
        Intent intent_course = new Intent(this, Course.class);
        Intent intent_account = new Intent(this, Account.class);
        Intent intent_add_tovar = new Intent(this, MarketAdd.class);


        recyclerViewMarket = findViewById(R.id.recyclerViewCourse);
        recyclerViewMarket.setLayoutManager(new LinearLayoutManager(this));
        marketAdapter = new MarketAdapter(marketsList);
        recyclerViewMarket.setAdapter(marketAdapter);
        db = FirebaseFirestore.getInstance();
        fetchForums();


        btn_market_forum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent_forum);
//                Activity.setActivity(this);
            }
        });
        btn_market_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent_account);
//                Activity.setActivity(this);
            }
        });
        btn_market_course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent_course);
//                Activity.setActivity(this);
            }
        });
        forumButtonPlusMarket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent_add_tovar);
                finish();
//                Activity.setActivity(this);
            }
        });
    }

    private void fetchForums() {
        db.collection("market")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        marketsList.clear();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            String name = document.getString("name");
                            String foto = document.getString("foto");
                            String login = document.getString("login");
                            if (name != null && foto != null && login != null) {
                                marketsList.add(new Markets(name, foto, login));
                            }
                        }
                        marketAdapter.updateData(marketsList);
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