package com.emilb.web;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    WebView wv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        wv = (WebView) findViewById(R.id.webView);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (wv.canGoBack()) {
                        wv.goBack();
                    } else {
                        finish();
                    }
                    return true;
            }

        }
        return super.onKeyDown(keyCode, event);
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
        /*if (id == R.id.action_settings) {
            return true;
        }*/

        if (id ==R.id.action_contacts){

            Intent i = new Intent(MainActivity.this, About.class);
            startActivity(i);
        }

        if (id == R.id.action_quit){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    public void goClick(View v) {
        EditText et = (EditText) findViewById(R.id.editText);

        String s = et.getText().toString();


        if (s.equals("")) {
            Toast.makeText(MainActivity.this, "Enter URL", Toast.LENGTH_SHORT).show();

        } else {

            if (s.startsWith("http://") || s.startsWith("https://")) {

                wv.getSettings().setJavaScriptEnabled(true);
                wv.setWebViewClient(new WebViewClient());
                wv.setWebChromeClient(new WebChromeClient());
                wv.loadUrl(s);


            } else {

                String sUrl = "http://" + s;
                wv.getSettings().setJavaScriptEnabled(true);
                wv.setWebViewClient(new WebViewClient());
                wv.setWebChromeClient(new WebChromeClient());
                wv.loadUrl(sUrl);

            }


        }
    }
}
