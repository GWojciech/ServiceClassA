package com.golabek.wkck.serviceclassa;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.golabek.wkck.serviceclassa.tabbed.RedirectActivity;
import com.golabek.wkck.serviceclassa.tabbed.SectionsPageAdapter;


public class Group2Activity extends AppCompatActivity {

    private static final String TAG = "Group2Activity";

    private SectionsPageAdapter mSectionsPageAdapter;

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group2);

        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container2);
        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new RedirectActivity(), "Wyniki");
        adapter.addFragment(new RedirectActivity(), "Tabela");
        adapter.addFragment(new RedirectActivity(), "Terminarz");
        adapter.addFragment(new RedirectActivity(), "Klasyfikacje");
        viewPager.setAdapter(adapter);
    }

}
