package com.example.mangatoon.model;

public class MaybeYouLiked {
    String tilte;
    String imageUrl;

    public MaybeYouLiked() {

    }

    public MaybeYouLiked(String tilte, String imageUrl) {
        this.tilte = tilte;
        this.imageUrl = imageUrl;
    }

    public String getTilte() {
        return tilte;
    }

    public void setTilte(String tilte) {
        this.tilte = tilte;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
