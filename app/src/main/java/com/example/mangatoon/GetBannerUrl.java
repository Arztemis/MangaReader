package com.example.mangatoon;

import android.os.AsyncTask;
import android.util.Log;

import com.example.mangatoon.model.Banner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GetBannerUrl extends AsyncTask<Void, List<Banner>, List<Banner>> {

    public Callback callback = null;

    @Override
    protected List<Banner> doInBackground(Void... voids) {
        List<Banner> list = new ArrayList<>();
        Document doc = null;
        Document document = null;
        try {
            doc = Jsoup.connect("https://truyentranhaudio.online/").get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert doc != null;
        Elements elements = doc.getElementsByClass("popular-thumb-item col-12");
        for (Element e : elements) {
            Banner banner = new Banner();
            String text = e.childNode(1).childNode(1).childNode(1).childNode(1).attr("style");
            int first = text.indexOf("\"");
            int last = text.lastIndexOf("\"");
            text = text.substring(first + 1, last);
            if (!text.contains("https")) {
                text = text.substring(text.indexOf("/"));
                StringBuilder str = new StringBuilder(text);
                str.insert(0, "https:");
                text = str.toString();
            }

            String text1 = e.text();
            String[] a = text1.split(" ");
            int first1 = text1.indexOf("trước");
            String title = text1.substring(first1 + 5);
            String chap = a[0] + " " + a[1];
            String time = a[2] + " " + a[3] + " " + a[4];

            String href = e.childNode(1).childNode(1).attr("href").trim();
            String idBanner = href.substring(href.indexOf("-") + 1, href.indexOf(".")).replace("-", "");

            try {
                document = Jsoup.connect("https://truyentranhaudio.online/" + href).get();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

            assert document != null;
            Elements ele = document.getElementsByClass("manga-info");
            Elements ele1 = document.getElementsByClass("summary-content");

            String textHref = ele.text() + ":@" + ele1.text();
            Log.d("DUCKHANH", textHref + "");
            String desc = textHref.substring(textHref.indexOf("@") + 1);
            String[] arr = textHref.split(":");
            String author = arr[2].substring(0, arr[2].indexOf("Thể loại")).trim();
            String type = arr[3].substring(0, arr[3].indexOf("Tình trạng")).trim();
            String countView = arr[6].trim();
            String transTeam = arr[5].substring(0, arr[5].indexOf("Lượt xem")).trim();
            String statusUpdate = arr[4].substring(0, arr[4].indexOf("Nhóm dịch")).trim();

            banner.setDesc(desc);
            banner.setAuthor(author);
            banner.setType(type);
            banner.setCountView(countView);
            banner.setTransTeam(transTeam);
            banner.setStatusUpdate(statusUpdate);
            banner.setIdBanner(idBanner);
            banner.setHref(href);
            banner.setImage(text);
            banner.setTitle(title);
            banner.setNewChapter(chap);
            banner.setTimeUpdate(time);
            list.add(banner);
        }
        return list;
    }

    @Override
    protected void onPostExecute(List<Banner> banners) {
        super.onPostExecute(banners);
        callback.getBannerUrlDone(banners);
    }
}

