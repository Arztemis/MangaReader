package com.example.mangatoon.activity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mangatoon.R;
import com.example.mangatoon.adapter.DetailChapterApdater;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DetailChapterActivity extends AppCompatActivity {

    private List<String> imgUrl = new ArrayList<>();
    private DetailChapterApdater apdater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_chapter);

        RecyclerView rcdetailChapter = findViewById(R.id.rc_detailChapter);
        apdater = new DetailChapterApdater(imgUrl, this);
        rcdetailChapter.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference imgRef = storage.getReference().child("/voluyendinhphong").child("chapter1");
        imgRef.listAll().addOnSuccessListener(listResult -> {
            for (StorageReference item : listResult.getItems()) {
                Log.d("DUCKHANH", item.getName() + "");
                item.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        imgUrl.add(uri.toString());
                        rcdetailChapter.setAdapter(apdater);
                        apdater.notifyDataSetChanged();
                        Collections.sort(imgUrl);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("DUCKHANH", e.getMessage() + "");
                    }
                });
            }
        }).addOnFailureListener(e -> {
            // Uh-oh, an error occurred!
            Log.d("DUCKHANH", "LỖI");
        });


    }
}