package com.golabek.wkck.serviceclassa.database.forQueries.mock;

import android.support.annotation.NonNull;

public class ScorersRank implements Comparable<ScorersRank> {
    private String name;
    private String surname;
    private String teamName;
    private Integer goals;
    private Integer assists;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Integer getGoals() {
        return goals;
    }

    public void setGoals(Integer goals) {
        this.goals = goals;
    }

    public Integer getAssists() {
        return assists;
    }

    public void setAssists(Integer assists) {
        this.assists = assists;
    }

    @Override
    public int compareTo(@NonNull ScorersRank scorersRank) {
        return scorersRank.getGoals()-this.goals;
    }

    @Override
    public String toString() {
        return "ScorersRank{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", teamName='" + teamName + '\'' +
                ", goals=" + goals +
                ", assists=" + assists +
                '}';
    }
}
