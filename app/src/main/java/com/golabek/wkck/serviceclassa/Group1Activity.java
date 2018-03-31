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
        TeamsController teamsController= new TeamsController(this);
        List <Teams> teamsList;
        teamsList = teamsController.getTeamsWithGroups();
        TextView textView = (TextView) findViewById(R.id.group1TextView);
        textView.setText(teamsList.toString());

    }



}
