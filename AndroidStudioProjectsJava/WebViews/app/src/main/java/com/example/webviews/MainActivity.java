package com.example.webviews;

import androidx.appcompat.app.AppCompatActivity;

import android.net.http.SslError;
import android.os.Bundle;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.webView);

        webView.getSettings().setJavaScriptEnabled(true); // you need true to allow certain websites to work
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onReceivedSslError (WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }
        });
        String url = "https://www.google.com";
        //url =  "https://github.com";
        //webView.loadUrl(url);
        String html = "<html><body><h1>Hello World!</h1><p>This is my website!</p></body></html>";
        webView.loadUrl(url);
        this.setContentView(webView);
        //webView.loadData(url, "text/html", "UTF-8");


    }

}