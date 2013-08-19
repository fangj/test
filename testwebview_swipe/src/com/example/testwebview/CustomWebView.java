package com.example.testwebview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.webkit.WebView;

/**
 * Created with IntelliJ IDEA.
 * User: jack_fang
 * Date: 13-8-19
 * Time: 下午3:19
 */
public final class CustomWebView extends WebView {

    private GestureDetector gestureDetector;

    /**
     * @param context
     * @param attrs
     * @param defStyle
     */
    public CustomWebView(Context context) {
        super(context);
    }

    /**
     * @param context
     * @param attrs
     * @param defStyle
     */
    public CustomWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * @param context
     * @param attrs
     * @param defStyle
     */
    public CustomWebView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /*
     * @see android.webkit.WebView#onScrollChanged(int, int, int, int)
     */
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
    }

    /*
     * @see android.webkit.WebView#onTouchEvent(android.view.MotionEvent)
     */
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return gestureDetector.onTouchEvent(ev) || super.onTouchEvent(ev);
    }

    public void setGestureDetector(GestureDetector gestureDetector) {
        this.gestureDetector = gestureDetector;
    }
}