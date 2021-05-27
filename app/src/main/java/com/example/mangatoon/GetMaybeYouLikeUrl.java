package com.example.mangatoon;

import android.os.AsyncTask;

import com.example.mangatoon.model.MaybeYouLiked;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GetMaybeYouLikeUrl extends AsyncTask<Void, List<MaybeYouLiked>, List<MaybeYouLiked>> {

    private Callback callback;

    public GetMaybeYouLikeUrl(Callback callback) {
        this.callback = callback;
    }

    @Override
    protected List<MaybeYouLiked> doInBackground(Void... voids) {
        List<MaybeYouLiked> list = new ArrayList<>();
        Document doc = null;
        try {
            doc = Jsoup.connect("https://mangatoon.mobi/vi").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements elements = doc.getElementsByClass("item-2").select("img");
        for (int i = 0; i < 4; i++) {
            MaybeYouLiked maybeYouLiked = new MaybeYouLiked();
            Element e = elements.get(i);
            String image = e.attr("src");
            String title = e.attr("alt").trim();
            if (!image.contains("https")) {
                image = image.substring(image.indexOf("/"));
                StringBuilder str = new StringBuilder(image);
                str.insert(0, "https:");
                image = str.toString().trim();
            }

            maybeYouLiked.setImageUrl(image);
            maybeYouLiked.setTilte(title);
            list.add(maybeYouLiked);
        }
        return list;
    }

    @Override
    protected void onPostExecute(List<MaybeYouLiked> list) {
        super.onPostExecute(list);
        callback.getMaybeYouLikeUrlDone(list);

    }
}
