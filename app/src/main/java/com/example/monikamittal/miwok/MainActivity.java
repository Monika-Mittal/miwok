package com.example.monikamittal.miwok;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    ViewPager viewpager=(ViewPager) findViewById(R.id.viewPager);
        TabLayout tabLayout=(TabLayout) findViewById(R.id.sliding_tabs);
        fragmentPageAdapter pageAdapter=new fragmentPageAdapter(getSupportFragmentManager(),this);
        viewpager.setAdapter(pageAdapter);
        tabLayout.setupWithViewPager(viewpager);
    }
}
