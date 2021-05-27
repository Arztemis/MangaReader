package com.example.mangatoon;

import android.os.AsyncTask;

import com.example.mangatoon.model.Action;
import com.example.mangatoon.model.Fiction;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GetFictionUrl extends AsyncTask<Void, List<Fiction>, List<Fiction>> {

    private Callback callback;

    public GetFictionUrl(Callback callback) {
        this.callback = callback;
    }

    @Override
    protected List<Fiction> doInBackground(Void... voids) {
        List<Fiction> list = new ArrayList<>();
        Document doc = null;
        try {
            doc = Jsoup.connect("https://mangatoon.mobi/vi/genre/tags/14?type=1").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements elements = doc.getElementsByClass("content-image").select("img");
        for (int i = 0; i < 10; i++) {
            Fiction fiction = new Fiction();
            Element element = elements.get(i);
            String imageUrl = element.attr("src");
            imageUrl = imageUrl.substring(imageUrl.indexOf(":"));
            StringBuilder url = new StringBuilder(imageUrl);
            url.insert(0, "https");
            imageUrl = url.toString();
            System.out.println(imageUrl);
            String title = element.attr("alt");


            fiction.setImageUrl(imageUrl);
            fiction.setTitle(title);
            list.add(fiction);
        }
        return list;
    }

    @Override
    protected void onPostExecute(List<Fiction> list) {
        super.onPostExecute(list);
        callback.getFictionUrlDone(list);

    }
}
