package com.golabek.wkck.serviceclassa.database.operations.mock;

import android.content.Context;

import com.golabek.wkck.serviceclassa.database.models.Teams;
import com.golabek.wkck.serviceclassa.database.models.mock.Matches;
import com.golabek.wkck.serviceclassa.database.operations.TeamsController;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MatchdayScheudle {
    private Context context;
    public MatchdayScheudle(Context context){
        this.context = context;
    }

    public List<Matches> getMatchdayScheudle (int matchdayNumber){
        List<Matches> matchesList = new ArrayList<>();
        Matches matches;
        TeamsController teamsController = new TeamsController(context);
        List <Teams> listOfTeams = teamsController.getTeamsWithGroups();
        Teams teamToList;
        Date date = new Date();
        date.setMonth(8);
        date.setDate(23);
        date.setHours(15);
        date.setMinutes(0);

        int randNumberHomeTeam, randNumberAwayTeam;
        Random random;
        for(int i=0; i<6; i++){
            matches = new Matches();
            random = new Random();
            randNumberHomeTeam = 1+random.nextInt(11);
            teamToList = listOfTeams.get(randNumberHomeTeam);
            matches.setHomeTeam(teamToList.getName());
            randNumberAwayTeam = 1+random.nextInt(11);
            if(randNumberAwayTeam == randNumberHomeTeam){
                randNumberAwayTeam = 1+random.nextInt(11);
            }
            teamToList = listOfTeams.get(randNumberAwayTeam);
            matches.setAwayTeam(teamToList.getName());
            matches.setDateOfMatch(date);
            matchesList.add(matches);

        }
        return matchesList;
    }

}
