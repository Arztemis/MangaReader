package com.example.mangatoon.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mangatoon.R;

public class DetailActivity extends AppCompatActivity {

    private ImageView goBack;
    private TextView tvDesc, tvStatusUpdate, tvAuthor, tvTransTeam;
    private String desc, statusUpdate, author, transTeam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        author = getIntent().getStringExtra("author");
        desc = getIntent().getStringExtra("desc");
        transTeam = getIntent().getStringExtra("transTeam");
        statusUpdate = getIntent().getStringExtra("statusUpdate");

        tvDesc = findViewById(R.id.tv_desc);
        tvStatusUpdate = findViewById(R.id.tv_statusUpdate);
        tvTransTeam = findViewById(R.id.tv_transTeam);
        tvAuthor = findViewById(R.id.tv_authorManga);

        tvDesc.setText(desc);
        tvStatusUpdate.setText(statusUpdate);
        tvTransTeam.setText(transTeam);
        tvAuthor.setText(author);


        goBack = findViewById(R.id.ic_goBack);
        goBack.setOnClickListener(v -> {
            onBackPressed();
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}