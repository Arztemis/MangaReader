package com.example.mangatoon.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mangatoon.R;
import com.example.mangatoon.adapter.ChapterAdapter;
import com.example.mangatoon.model.Chapter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class MangaActivity extends AppCompatActivity {

    private String imgUrl, type, title, author, desc, newChapter, countView, transTeam, statusUpdate;
    private ImageView imgBanner;
    private TextView tvType, tvTitle, tvAuthor, tvDesc, tvNewChapter, tvCountView;
    private ImageView info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manga);

        //Change color in status bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.white));
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }


        ImageView back = findViewById(R.id.ic_back);
        back.setOnClickListener(v -> {
            onBackPressed();
        });

        imgUrl = getIntent().getStringExtra("imageUrl");
        type = getIntent().getStringExtra("type");
        title = getIntent().getStringExtra("title").trim();
        author = getIntent().getStringExtra("author");
        desc = getIntent().getStringExtra("desc");
        newChapter = getIntent().getStringExtra("newChapter");
        countView = getIntent().getStringExtra("countView");
        transTeam = getIntent().getStringExtra("transTeam");
        statusUpdate = getIntent().getStringExtra("statusUpdate");

        imgBanner = findViewById(R.id.image_banner);
        Glide.with(this).load(imgUrl).into(imgBanner);
        tvType = findViewById(R.id.tv_type);
        tvTitle = findViewById(R.id.tv_title);
        tvAuthor = findViewById(R.id.tv_author);
        tvDesc = findViewById(R.id.tv_des);
        tvNewChapter = findViewById(R.id.tv_ep);
        tvCountView = findViewById(R.id.tv_viewer);

        tvType.setText(type);
        tvTitle.setText(title);
        tvAuthor.setText(author);
        tvDesc.setText(desc);
        tvNewChapter.setText(newChapter);
        tvCountView.setText(countView);

        info = findViewById(R.id.ic_info);
        info.setOnClickListener(v -> {
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra("desc", desc);
            intent.putExtra("author", author);
            intent.putExtra("transTeam", transTeam);
            intent.putExtra("statusUpdate", statusUpdate);
            startActivity(intent);
        });

        List<Chapter> list = new ArrayList<>();

        list.add(new Chapter("Chapter 1", "21/01/2021"));
        list.add(new Chapter("Chapter 2", "21/01/2021"));
        list.add(new Chapter("Chapter 3", "21/01/2021"));
        list.add(new Chapter("Chapter 4", "21/01/2021"));
        list.add(new Chapter("Chapter 5", "21/01/2021"));
        list.add(new Chapter("Chapter 6", "21/01/2021"));
        list.add(new Chapter("Chapter 7", "21/01/2021"));
        list.add(new Chapter("Chapter 8", "21/01/2021"));
        list.add(new Chapter("Chapter 9", "21/01/2021"));
        list.add(new Chapter("Chapter 10", "21/01/2021"));
        list.add(new Chapter("Chapter 11", "21/01/2021"));
        list.add(new Chapter("Chapter 12", "21/01/2021"));

        RecyclerView rcChapter = findViewById(R.id.rc_chapter);
        rcChapter.setAdapter(new ChapterAdapter(this, list));
        rcChapter.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}