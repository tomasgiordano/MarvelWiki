package com.example.marvelwiki.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.marvelwiki.R;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;

public class CharacterDetailActivity extends AppCompatActivity {
    ImageView arrowBack;
    ImageView imageView;
    TextView textView;
    TextView characterName;
    String name;
    String detail;
    String imageUrl;
    Bitmap bitmap;
    Button detailLink;
    Button wiki;
    Button comics;
    String detailURL;
    String wikiURL;
    String comicsURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_detail);

        imageView = findViewById(R.id.characterImage);
        textView = findViewById(R.id.characterDetail);
        arrowBack = findViewById(R.id.arrowBack);
        characterName = findViewById(R.id.characterName);
        detailLink = findViewById(R.id.btnDetail);
        wiki = findViewById(R.id.btnWiki);
        comics = findViewById(R.id.btnComicLink);

        textView.setMovementMethod(new ScrollingMovementMethod());

        Intent intent = getIntent();
        name = intent.getStringExtra("CharacterName");
        detail = intent.getStringExtra("CharacterDetail");
        imageUrl = intent.getStringExtra("CharacterImageURL");
        detailURL = intent.getStringExtra("UrlDetail");
        wikiURL = intent.getStringExtra("UrlWiki");
        comicsURL = intent.getStringExtra("UrlComics");

        if(detail.isEmpty() || detail == " ")
            textView.setText("There is no description about "+name+" yet, but we are working on it \uD83D\uDE42");
        else
            textView.setText(detail);

        characterName.setText(name);

        Picasso.get()
                .load(imageUrl)
                .resize(600, 900)
                .centerCrop()
                .into(imageView);

        arrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        if(detailURL != null){
            detailLink.setOnClickListener(new View.OnClickListener() {
                public void onClick(View arg0) {
                    Intent viewIntent =
                            new Intent("android.intent.action.VIEW",
                                    Uri.parse(detailURL));
                    startActivity(viewIntent);
                }
            });
        }
        else{
            detailLink.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(CharacterDetailActivity.this, "There is no link on Detail for this character", Toast.LENGTH_SHORT).show();
                }
            });
        }


        if(wikiURL != null){
            wiki.setOnClickListener(new View.OnClickListener() {
                public void onClick(View arg0) {
                    Intent viewIntent =
                            new Intent("android.intent.action.VIEW",
                                    Uri.parse(wikiURL));
                    startActivity(viewIntent);
                }
            });
        }
        else{
            wiki.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(CharacterDetailActivity.this, "There is no link on Wiki for this character", Toast.LENGTH_SHORT).show();
                }
            });
        }


        if(comicsURL != null){
            comics.setOnClickListener(new View.OnClickListener() {
                public void onClick(View arg0) {
                    Intent viewIntent =
                            new Intent("android.intent.action.VIEW",
                                    Uri.parse(comicsURL));
                    startActivity(viewIntent);
                }
            });
        }
        else {
            comics.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(CharacterDetailActivity.this, "There is no link on Comics for this character", Toast.LENGTH_SHORT).show();
                }
            });
        }


    }


}