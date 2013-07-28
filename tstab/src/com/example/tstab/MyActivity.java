package com.example.tstab;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MyActivity extends Activity implements ActionBar.TabListener {
    private Fragment fr1=null;
    private Fragment fr2=null;
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        addTab1();
    }

    private void addFr1(){
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment myfr=new MyFragment();
        ft.add(R.id.container,myfr);
        ft.commit();
    }

    private void addTab1(){
        ActionBar.Tab tab1=getActionBar().newTab().setText("tab1").setTag(0).setTabListener(this);
        getActionBar().addTab(tab1);
        ActionBar.Tab tab2=getActionBar().newTab().setText("tab2").setTag(1).setTabListener(this);
        getActionBar().addTab(tab2);
    }

    private void selTab0(FragmentTransaction ft){
            if(fr1==null){
                 fr1=new MyFragment();
                 ft.add(R.id.container,fr1);
            } else{
                ft.attach(fr1);
            }
    }

    private void selTab1(FragmentTransaction ft){
        if(fr2==null){
            fr2=new MyFragment2();
            ft.add(R.id.container,fr2);
        } else{
            ft.attach(fr2);
        }
    }

    private void unSelTab0(FragmentTransaction ft){
            if(fr1!=null){
                ft.detach(fr1);
            }
    }
    private void unSelTab1(FragmentTransaction ft){
            if(fr2!=null){
                ft.detach(fr2);
            }
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        Log.d("test", tab.getTag().toString());
        switch((Integer)tab.getTag()){
            case 0: {selTab0(fragmentTransaction);break;}
            case 1: {selTab1(fragmentTransaction);break;}
        }



    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

        switch((Integer)tab.getTag()){
            case 0: {unSelTab0(fragmentTransaction);break;}
            case 1: {unSelTab1(fragmentTransaction);break;}
        }
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        Toast.makeText(this,"tab :"+tab.getText(),0).show();
    }
}
