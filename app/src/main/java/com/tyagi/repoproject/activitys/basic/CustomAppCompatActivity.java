package com.tyagi.repoproject.activitys.basic;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.tyagi.repoproject.R;
import com.tyagi.repoproject.interfaces.basic.InstancesInterface;


/**
 * Created by Rajneesh on 15-02-2017.
 */

public abstract class CustomAppCompatActivity extends AppCompatActivity implements InstancesInterface {

    abstract public void setContentView();

    abstract public void initView(Bundle savedInstanceState);

    final public Activity currActivity = this;
    final public Context currContext = this;

    final public String TAG = "CustomAppCompatActivity";

    LinearLayout ll_main_bottom_parent;
    LinearLayout ll_inflator_container;
    // UtilProgressDialog utilProgressDialog_custom= new UtilProgressDialog();

    public View view;

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Fabric.with(this, new Crashlytics());
        setContentView(R.layout.custom_actionbaractivity_with_back);
        ShowBack();

        init(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void init(Bundle savedInstanceState) {
        try {
            ll_inflator_container = (LinearLayout) findViewById(R.id.ll_inflator_container);

            setContentView();
            initView(savedInstanceState);
        } catch (Exception e) {
            mUtilExceptionHandler.printStackTrace(e);
        }
    }

    public void ShowBack() {
        try {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);

        } catch (Exception e) {
            mUtilExceptionHandler.printStackTrace(e);
        }
    }

    public void setTitle(String actionbar_title) {
        try {
            getSupportActionBar().setTitle("" + actionbar_title);
        } catch (Exception e) {

        }
    }

    public View initializeView(int inner_view) {
        try {
            //  ll_inflator_container.removeAllViews();

            View inflatingInfo = getLayoutInflater().inflate(inner_view, ll_inflator_container, false);
            ll_inflator_container.addView(inflatingInfo);
            view = ll_inflator_container;

        } catch (Exception e) {
            view = null;
            e.printStackTrace();
        }

        return view;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
    }


}
