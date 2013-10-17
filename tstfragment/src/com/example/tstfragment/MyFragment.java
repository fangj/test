package com.example.tstfragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created with IntelliJ IDEA.
 * User: fangjian
 * Date: 13-7-27
 * Time: 下午4:47
 */
public class MyFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.myfr, container, false);
        return v;
    }
}
