package com.example.mdoctor.firstandroid;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import java.util.UUID;


public class MainActivity extends Activity {

    String device_model;
   String device_type;
    private static final String UNIQUE_ID = "unique_id";
    SharedPreferences prefs = null;
    SharedPreferences.Editor editor = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView device_name = (TextView) findViewById(R.id.device_name);
       // device_model = Integer.toString( Build.VERSION.SDK_INT);
        device_model = android.os.Build.DEVICE + "," + Build.MODEL + "," + android.os.Build.PRODUCT + "," + Build.MANUFACTURER;
        device_name.setText("Device Model is:" + device_model);

        int screensize = getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK;
        TextView device_type = (TextView) findViewById(R.id.device_type);

     //   SharedPreferences prefs = PreferenceManager
       //         .getDefaultSharedPreferences(this);
        prefs = PreferenceManager
                .getDefaultSharedPreferences(this);
        editor = prefs.edit();

        String uniqueID = null;


        uniqueID = prefs.getString(UNIQUE_ID,null);

        if (uniqueID == null)
        {
            uniqueID = UUID.randomUUID().toString().replace("-","");
            editor.putString(UNIQUE_ID,uniqueID);
            editor.commit();
        }


        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        device_type.setText("Device Size:" + screensize);
        System.out.println("Width:" +width);
        System.out.println("Height:" +height);
    }

    public void retrieveID(View view)
    {
       TextView visitor_id = (TextView) findViewById(R.id.visitor_id);
        String value = prefs.getString(UNIQUE_ID,null);
        visitor_id.setText("Unique ID from shared Pref:" +value);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
