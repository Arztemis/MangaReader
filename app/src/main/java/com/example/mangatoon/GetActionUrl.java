package com.example.mangatoon;

import android.os.AsyncTask;

import com.example.mangatoon.model.Action;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GetActionUrl extends AsyncTask<Void, List<Action>, List<Action>> {

    private Callback callback;

    public GetActionUrl(Callback callback) {
        this.callback = callback;
    }

    @Override
    protected List<Action> doInBackground(Void... voids) {
        List<Action> list = new ArrayList<>();
        Document doc = null;
        try {
            doc = Jsoup.connect("https://mangatoon.mobi/vi/genre/tags/7?type=1").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements elements = doc.getElementsByClass("content-image").select("img");
        for (int i = 0; i < 10; i++) {
            Action action = new Action();
            Element element = elements.get(i);
            String imageUrl = element.attr("src");
            imageUrl = imageUrl.substring(imageUrl.indexOf(":"));
            StringBuilder url = new StringBuilder(imageUrl);
            url.insert(0, "https");
            imageUrl = url.toString();
            System.out.println(imageUrl);
            String title = element.attr("alt");


            action.setImageUrl(imageUrl);
            action.setTitle(title);
            list.add(action);
        }
        return list;
    }

    @Override
    protected void onPostExecute(List<Action> list) {
        super.onPostExecute(list);
        callback.getActionUrlDone(list);

    }
}
