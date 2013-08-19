package com.example.testwebview;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.webkit.ConsoleMessage;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Toast;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    WebView webView;
    Context context;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        context=this.getApplicationContext();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        webView= (WebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new MyWebChromeClient());
        webView.loadUrl("file:///android_asset/test.html");
       // webView.loadUrl("file:///android_asset/testhammer.html");
       // webView.loadUrl("file:///android_asset/tstquojs.html");

    }

    private class MyWebChromeClient extends WebChromeClient {
        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
            return true;
        }

        @Override
        public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            Log.e("test", consoleMessage.message());
            return true;
        }
    }
}
