package com.golabek.wkck.serviceclassa;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TextView;

import com.golabek.wkck.serviceclassa.controller.GroupsController;
import com.golabek.wkck.serviceclassa.controller.TeamsController;
import com.golabek.wkck.serviceclassa.database.DBHelper;
import com.golabek.wkck.serviceclassa.model.Groups;
import com.golabek.wkck.serviceclassa.model.Teams;

import java.util.ArrayList;
import java.util.List;

public class Group1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group1);
        /*
        TeamsController teamsController= new TeamsController(this);
        List <Teams> teamsList;
        teamsList = teamsController.getTeamsWithGroups();
        TextView textView = (TextView) findViewById(R.id.group1TextView);
        textView.setText(teamsList.toString());
        */
        TabHost tabHost = (TabHost) findViewById(R.id.tabHost1);
        tabHost.setup();

        TabHost.TabSpec spec = tabHost.newTabSpec("results");
        spec.setContent(R.id.tab1);
        spec.setIndicator("Wyniki");
        tabHost.addTab(spec);

        spec = tabHost.newTabSpec("table");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Tabela");
        tabHost.addTab(spec);

        spec = tabHost.newTabSpec("scheudle");
        spec.setContent(R.id.tab3);
        spec.setIndicator("Terminarz");
        tabHost.addTab(spec);

    }



}
