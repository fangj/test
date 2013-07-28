package com.example.tstsearch;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Button btn= (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                       onSearchRequested();
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
     if(Intent.ACTION_SEARCH.equals(intent.getAction())){
         String query=intent.getStringExtra(SearchManager.QUERY);
         doSearch(query);
     }
    }

    private void doSearch(String query) {
        Log.d("test", "query:" + query);
        Toast.makeText(this,query,0).show();
    }
}
