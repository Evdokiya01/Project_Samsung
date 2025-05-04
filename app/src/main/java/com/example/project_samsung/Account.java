package com.example.project_samsung;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.squareup.picasso.Picasso;

import java.util.Arrays;
import java.util.List;

public class Account extends AppCompatActivity {

    private ImageButton btn_account_forum, btn_account_market, btn_account_course;
    private ImageView image_view_account;
    private RecyclerView recyclerViewAccount;
    private MyAdapter myAdapter;

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
        image_view_account = findViewById(R.id.imageViewAccount);
        recyclerViewAccount = findViewById(R.id.recyclerView);

        String url = "https://papik.pro/grafic/uploads/posts/2023-04/1681528233_papik-pro-p-lichnii-logotip-vektor-4.png";
        Picasso.get().load(url).into(image_view_account);

        Intent intent_forum = new Intent(this, Forum.class);
        Intent intent_course = new Intent(this, Course.class);
        Intent intent_market = new Intent(this, Market.class);

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

        List<String> imageUrlList = Arrays.asList(image_urls);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL); // 2 колонки, вертикальное расположение
        recyclerViewAccount.setLayoutManager(layoutManager);
        myAdapter = new MyAdapter(imageUrlList);
        recyclerViewAccount.setAdapter(myAdapter);
    }
}