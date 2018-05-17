package com.golabek.wkck.serviceclassa;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import com.golabek.wkck.serviceclassa.tabbed.SectionsPageAdapter;
import com.golabek.wkck.serviceclassa.tabbed.group1.ClassificationsGroup1Activity;
import com.golabek.wkck.serviceclassa.tabbed.group1.ResultsGroup1Activity;
import com.golabek.wkck.serviceclassa.tabbed.group1.ScheudleGroup1Activity;
import com.golabek.wkck.serviceclassa.tabbed.group1.TableGroup1Activity;

public class Group1Activity extends AppCompatActivity {

    private static final String TAG = "Group1Activity";

    private SectionsPageAdapter mSectionsPageAdapter;

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group1);

        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container2);
        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new ResultsGroup1Activity(), "Wyniki");
        adapter.addFragment(new TableGroup1Activity(), "Tabela");
        adapter.addFragment(new ScheudleGroup1Activity(), "Terminarz");
        adapter.addFragment(new ClassificationsGroup1Activity(), "Klasyfikacje");
        viewPager.setAdapter(adapter);
    }



}
