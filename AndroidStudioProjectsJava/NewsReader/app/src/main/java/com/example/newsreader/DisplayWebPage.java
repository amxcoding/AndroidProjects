package com.example.newsreader;

import androidx.appcompat.app.AppCompatActivity;
import android.net.http.*;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class DisplayWebPage extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_web_page);

        webView = new WebView(getApplicationContext());
        setContentView(webView);
        setWebView();
    }

    private String getUrl() {
        Intent intent = getIntent();
        int position = intent.getIntExtra("position", -1);
        String url = "<html><body><h1>Error</h1><p>Can't find url</p></body></html>";

        if (position >= 0) {
            url = MainActivity.getWebPagesList().get(position).getUrl();
        }
        return url;
    }

    private void setWebView() {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient(){

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error){
                handler.proceed();
            }
        });
        String url = getUrl();
        System.out.println(url);
        webView.loadUrl(url);

    }
}