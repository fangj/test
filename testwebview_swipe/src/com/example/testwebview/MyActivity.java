package com.example.testwebview;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.webkit.ConsoleMessage;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Toast;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    CustomWebView webView;
    Context context;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        context=this.getApplicationContext();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        webView= (CustomWebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new MyWebChromeClient());
        webView.setGestureDetector(new GestureDetector(new CustomeGestureDetector()));
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

    private class CustomeGestureDetector extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if(e1 == null || e2 == null) return false;
            if(e1.getPointerCount() > 1 || e2.getPointerCount() > 1) return false;
            else {
                try { // right to left swipe .. go to next page
                    if(e1.getX() - e2.getX() > 100 && Math.abs(velocityX) > 800) {
                        //do your stuff
                        Toast.makeText(context,"swipe left",Toast.LENGTH_SHORT).show();
                        return true;
                    } //left to right swipe .. go to prev page
                    else if (e2.getX() - e1.getX() > 100 && Math.abs(velocityX) > 800) {
                        //do your stuff
                        return true;
                    } //bottom to top, go to next document
                    else if(e1.getY() - e2.getY() > 100 && Math.abs(velocityY) > 800
                            && webView.getScrollY() >= webView.getScale() * (webView.getContentHeight() - webView.getHeight())) {
                        //do your stuff
                        return true;
                    } //top to bottom, go to prev document
                    else if (e2.getY() - e1.getY() > 100 && Math.abs(velocityY) > 800 ) {
                        //do your stuff
                        return true;
                    }
                } catch (Exception e) { // nothing
                }
                return false;
            }
        }
    }


}
