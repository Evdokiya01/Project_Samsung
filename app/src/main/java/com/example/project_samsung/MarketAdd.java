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

import com.example.project_samsung.FirbaseClass.Markets;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

public class MarketAdd extends AppCompatActivity {

    private ImageButton imageButtonAddMarketMarket, imageButtonAddMarketForum, imageButtonAddMarketCourse, imageButtonAddMarketAccount;
    private Button AddMArketButtonPublish;
    private EditText editTextAddMarketName, editTextAddMarketFoto;
    private String login;
    private CollectionReference collectionUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market_add);

        imageButtonAddMarketMarket = findViewById(R.id.imageButtonAddCourseMarket);
        imageButtonAddMarketForum = findViewById(R.id.imageButtonAddCourseForum);
        imageButtonAddMarketCourse = findViewById(R.id.imageButtonAddCourseCourse);
        imageButtonAddMarketAccount = findViewById(R.id.imageButtonAddCourseAccount);

        String url_account = "https://papik.pro/grafic/uploads/posts/2023-04/1681528233_papik-pro-p-lichnii-logotip-vektor-4.png";
        String url_forum = "https://www.pngarts.com/files/17/Forum-PNG-Pic-HQ.png";
        String url_course = "https://avatars.mds.yandex.net/i?id=5d5c4f1aa3a701195ce40beb93706ade661ab434-9181645-images-thumbs&n=13";
        String url_market = "https://banner2.cleanpng.com/20180531/ioi/kisspng-computer-icons-convenience-shop-5b0f92207789c8.4262469815277471044896.jpg";

        Picasso.get().load(url_account).fit().centerCrop().into(imageButtonAddMarketAccount);
        Picasso.get().load(url_forum).fit().centerCrop().into(imageButtonAddMarketForum);
        Picasso.get().load(url_course).fit().centerCrop().into(imageButtonAddMarketCourse);
        Picasso.get().load(url_market).fit().centerCrop().into(imageButtonAddMarketMarket);

        AddMArketButtonPublish = findViewById(R.id.AddCourseButtonPublish);

        editTextAddMarketName = findViewById(R.id.editTextAddCourseName);
        editTextAddMarketFoto = findViewById(R.id.editTextAddCoursePrice);

        Intent intent_account = new Intent(this, Account.class);
        Intent intent_course = new Intent(this, Course.class);
        Intent intent_market = new Intent(this, Market.class);
        Intent intent_forum = new Intent(this, Forum.class);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        imageButtonAddMarketAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent_account);
                finish();
            }
        });

        imageButtonAddMarketForum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent_forum);
                finish();
            }
        });
        imageButtonAddMarketMarket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent_market);
                finish();
            }
        });

        imageButtonAddMarketCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent_course);
                finish();
            }
        });
        AddMArketButtonPublish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTextAddMarketName.getText().toString().trim();
                String foto = editTextAddMarketFoto.getText().toString().trim();

                FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                login = currentUser.getEmail();

                Markets newMarkets = new Markets(name, foto, login);
                doSave(newMarkets);
            }
        });
    }

    private void doSave(Markets markets) {
        collectionUsers = FirebaseFirestore.getInstance().collection("market");
        collectionUsers.add(markets)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(MarketAdd.this, "Сохранение данных успешно!", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(MarketAdd.this, Market.class);
                        startActivity(intent);
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("Ошибка при сохранении данных в Firestore", e);
                        Toast.makeText(MarketAdd.this, "Ошибка при сохранении данных: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}