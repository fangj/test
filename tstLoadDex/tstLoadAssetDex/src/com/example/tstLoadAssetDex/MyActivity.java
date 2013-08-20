package com.example.tstLoadAssetDex;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import com.fangjian.ITest;
import dalvik.system.DexClassLoader;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class MyActivity extends Activity {
    private Context context;
    private File optimizedDexOutputPath;
    private File dexInternalStoragePath;
    private String SECONDARY_DEX_NAME;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        context=this.getApplicationContext();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        SECONDARY_DEX_NAME="test_out.jar";
        dexInternalStoragePath = new File(getDir("dex", Context.MODE_PRIVATE),SECONDARY_DEX_NAME);
        optimizedDexOutputPath = getDir("outdex", Context.MODE_PRIVATE);
        try {
            cpFiles();
            loadClazz();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void cpFiles() throws IOException {
        InputStream is=getAssets().open("test_out.jar");
        FileUtils.copyInputStreamToFile(is,dexInternalStoragePath);
    }
    private void loadClazz(){
        DexClassLoader cl = new DexClassLoader(dexInternalStoragePath.getAbsolutePath(),
                optimizedDexOutputPath.getAbsolutePath(),
                null,
                getClassLoader());

        try {
            Class T1Clazz=cl.loadClass("com.fangjian.Test1");
            ITest t2=(ITest)T1Clazz.newInstance();
           // System.out.println("t2:"+t2.hello());
            Toast.makeText(this,t2.hello(),Toast.LENGTH_LONG).show();
            Log.d("test",t2.hello() );

        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
