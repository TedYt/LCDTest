package com.ted.lcdtest;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private boolean isInPlayFrag = false;
    private boolean isPlaying = true;

    PlayFragment playFragment;

    FloatingActionButton mFab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(null);

        mFab = (FloatingActionButton) findViewById(R.id.fab);
        mFab.setOnClickListener(this);

        setDefaultFrag();
    }

    private void setDefaultFrag() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        HintFragment hintFragment = new HintFragment();
        ft.replace(R.id.frag_area,hintFragment);
        ft.commit();
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

    @Override
    public void onClick(View v) {

        if (!isInPlayFrag){
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            playFragment = new PlayFragment();
            ft.replace(R.id.frag_area, playFragment);
            ft.commit();

            isInPlayFrag = true;
        }else {
            if (isPlaying){
                mFab.setImageResource(android.R.drawable.ic_media_pause);
                Toast.makeText(MainActivity.this, "Test is paused", Toast.LENGTH_SHORT).show();

                playFragment.pauseTask();

                isPlaying = false;
            }else{
                mFab.setImageResource(android.R.drawable.ic_media_play);

                playFragment.reStartTask();

                isPlaying = true;
            }
        }

    }

}
