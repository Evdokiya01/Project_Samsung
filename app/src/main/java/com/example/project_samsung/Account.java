package com.example.project_samsung;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.project_samsung.Adapters.MyAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import java.util.Arrays;
import java.util.List;

public class Account extends AppCompatActivity {

    private ImageButton btn_account_forum, btn_account_market, btn_account_course, btn_LogOut, btn_account_account;
    private ImageView image_view_account;
    private RecyclerView recyclerViewAccount;
    private MyAdapter myAdapter;
    private TextView text_account_login;

    private final String image_urls[] = {
            "https://www.dovora.com/resources/weather-icons/showcase/modern_showcase/angry_clouds.png",
            "https://www.dovora.com/resources/weather-icons/showcase/modern_showcase/day_clear.png",
            "https://www.dovora.com/resources/weather-icons/showcase/modern_showcase/night_half_moon_partial_cloud.png",
            "https://www.dovora.com/resources/weather-icons/showcase/modern_showcase/night_half_moon_snow.png",
            "https://www.dovora.com/resources/weather-icons/showcase/modern_showcase/sleet.png",
            "https://www.dovora.com/resources/weather-icons/showcase/modern_showcase/mist.png",
            "https://www.dovora.com/resources/weather-icons/showcase/modern_showcase/night_full_moon_clear.png",
            "https://www.dovora.com/resources/weather-icons/showcase/modern_showcase/night_half_moon_rain.png",
            "https://www.dovora.com/resources/weather-icons/showcase/modern_showcase/rain_thunder.png",
            "https://www.dovora.com/resources/weather-icons/showcase/modern_showcase/fog.png",
            "https://www.dovora.com/resources/weather-icons/showcase/modern_showcase/angry_clouds.png"
    };

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        btn_account_market = findViewById(R.id.imageButtonAccountMarket);
        btn_account_forum = findViewById(R.id.imageButtonAccountForum);
        btn_account_course = findViewById(R.id.imageButtonAccountCourse);
        btn_account_account = findViewById(R.id.imageButtonAccountAccount);
        btn_LogOut = findViewById(R.id.imageButtonLogOut);
        image_view_account = findViewById(R.id.imageViewAccount);
        recyclerViewAccount = findViewById(R.id.recyclerView);
        text_account_login = findViewById(R.id.textView17);

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

        if (currentUser != null) {
            String login = currentUser.getDisplayName();
            if (login == null || login.isEmpty()) {
                login = currentUser.getEmail();
            }
            text_account_login.setText(login);
        }


        String url_account = "https://papik.pro/grafic/uploads/posts/2023-04/1681528233_papik-pro-p-lichnii-logotip-vektor-4.png";
        String url_forum = "https://www.pngarts.com/files/17/Forum-PNG-Pic-HQ.png";
        String url_course = "https://avatars.mds.yandex.net/i?id=5d5c4f1aa3a701195ce40beb93706ade661ab434-9181645-images-thumbs&n=13";
        String url_market = "https://banner2.cleanpng.com/20180531/ioi/kisspng-computer-icons-convenience-shop-5b0f92207789c8.4262469815277471044896.jpg";

        Picasso.get().load(url_account).fit().centerCrop().into(btn_account_account);
        Picasso.get().load(url_forum).fit().centerCrop().into(btn_account_forum);
        Picasso.get().load(url_course).fit().centerCrop().into(btn_account_course);
        Picasso.get().load(url_market).fit().centerCrop().into(btn_account_market);

        Picasso.get().load(url_account).into(image_view_account);

        Intent intent_forum = new Intent(this, Forum.class);
        Intent intent_course = new Intent(this, Course.class);
        Intent intent_market = new Intent(this, Market.class);
        Intent intent_log_in = new Intent(this, LogIn.class);

        btn_account_market.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent_market);
                finish();
            }
        });
        btn_account_forum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent_forum);
                finish();
            }
        });
        btn_account_course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent_course);
                finish();
            }
        });
        btn_LogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();
                startActivity(intent_log_in);
                finish();
            }
        });

        List<String> imageUrlList = Arrays.asList(image_urls);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerViewAccount.setLayoutManager(layoutManager);
        myAdapter = new MyAdapter(imageUrlList);
        recyclerViewAccount.setAdapter(myAdapter);
    }
}