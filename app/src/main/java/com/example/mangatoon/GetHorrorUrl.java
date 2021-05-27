package com.example.mangatoon;

import android.os.AsyncTask;

import com.example.mangatoon.model.Horror;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GetHorrorUrl extends AsyncTask<Void, List<Horror>, List<Horror>> {

    private Callback callback;

    public GetHorrorUrl(Callback callback) {
        this.callback = callback;
    }

    @Override
    protected List<Horror> doInBackground(Void... voids) {
        List<Horror> list = new ArrayList<>();
        Document doc = null;
        try {
            doc = Jsoup.connect("https://mangatoon.mobi/vi/genre/tags/4?type=1").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements elements = doc.getElementsByClass("content-image").select("img");
        for (int i = 0; i < 10; i++) {
            Horror horror = new Horror();
            Element element = elements.get(i);
            String imageUrl = element.attr("src");
            imageUrl = imageUrl.substring(imageUrl.indexOf(":"));
            StringBuilder url = new StringBuilder(imageUrl);
            url.insert(0, "https");
            imageUrl = url.toString();
            String title = element.attr("alt");


            horror.setImageUrl(imageUrl);
            horror.setTitle(title);
            list.add(horror);
        }
        return list;
    }

    @Override
    protected void onPostExecute(List<Horror> list) {
        super.onPostExecute(list);
        callback.getHorrorUrlDone(list);

    }
}
