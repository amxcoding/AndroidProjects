package com.example.newsreader;

public class WebPages {
    private final String title;
    private final String url;
    private final String html;

    public WebPages(String title, String url, String html) {
        this.title = title;
        this.url = url;
        this.html = html;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }
}
