package com.golabek.wkck.serviceclassa;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;


import com.golabek.wkck.serviceclassa.tabbed.SectionsPageAdapter;
import com.golabek.wkck.serviceclassa.tabbed.group2.ClassificationsGroup2Activity;
import com.golabek.wkck.serviceclassa.tabbed.group2.ResultsGroup2Activity;
import com.golabek.wkck.serviceclassa.tabbed.group2.ScheudleGroup2Activity;
import com.golabek.wkck.serviceclassa.tabbed.group2.TableGroup2Activity;

public class Group2Activity extends AppCompatActivity {

    private static final String TAG = "Group2Activity";

    private SectionsPageAdapter mSectionsPageAdapter;

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group2);
        Log.d(TAG, "onCreate: Starting.");

        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container2);
        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new ResultsGroup2Activity(), "Wyniki");
        adapter.addFragment(new TableGroup2Activity(), "Tabela");
        adapter.addFragment(new ScheudleGroup2Activity(), "Terminarz");
        adapter.addFragment(new ClassificationsGroup2Activity(), "Klasyfikacje");
        viewPager.setAdapter(adapter);
    }

}
