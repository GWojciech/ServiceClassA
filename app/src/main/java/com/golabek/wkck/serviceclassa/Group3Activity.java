package com.golabek.wkck.serviceclassa;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import com.golabek.wkck.serviceclassa.tabbed.SectionsPageAdapter;
import com.golabek.wkck.serviceclassa.tabbed.group3.ClassificationsGroup3Activity;
import com.golabek.wkck.serviceclassa.tabbed.group3.ResultsGroup3Activity;
import com.golabek.wkck.serviceclassa.tabbed.group3.ScheudleGroup3Activity;
import com.golabek.wkck.serviceclassa.tabbed.group3.TableGroup3Activity;


public class Group3Activity extends AppCompatActivity {

    private static final String TAG = "Group3Activity";

    private SectionsPageAdapter mSectionsPageAdapter;

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group3);

        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container2);
        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new ResultsGroup3Activity(), "Wyniki");
        adapter.addFragment(new TableGroup3Activity(), "Tabela");
        adapter.addFragment(new ScheudleGroup3Activity(), "Terminarz");
        adapter.addFragment(new ClassificationsGroup3Activity(), "Klasyfikacje");
        viewPager.setAdapter(adapter);
    }

}
