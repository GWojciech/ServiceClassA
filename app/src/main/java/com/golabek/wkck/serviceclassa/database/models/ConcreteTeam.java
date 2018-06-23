/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.golabek.wkck.serviceclassa.database.models;


import java.io.Serializable;

/**
 *
 * @author Odbiorca
 */
public class ConcreteTeam implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String teamName;
    private String website;
    private String nameOfGroup;
    private Integer playedMatches;
    private Integer points;
    private Integer scoredGoals;
    private Integer lostGoals;
    private Integer yearOfEstablishment;
    private String highestLeague;

    public ConcreteTeam() {
    }

    public ConcreteTeam(Integer id, String teamName, String website, String nameOfGroup, Integer playedMatches, Integer points, Integer scoredGoals, Integer lostGoals, Integer yearOfEstablishment, String highestLeague) {
        this.id = id;
        this.teamName = teamName;
        this.website = website;
        this.nameOfGroup = nameOfGroup;
        this.playedMatches = playedMatches;
        this.points = points;
        this.scoredGoals = scoredGoals;
        this.lostGoals = lostGoals;
        this.yearOfEstablishment = yearOfEstablishment;
        this.highestLeague = highestLeague;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getNameOfGroup() {
        return nameOfGroup;
    }

    public void setNameOfGroup(String nameOfGroup) {
        this.nameOfGroup = nameOfGroup;
    }

    public Integer getPlayedMatches() {
        return playedMatches;
    }

    public void setPlayedMatches(Integer playedMatches) {
        this.playedMatches = playedMatches;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Integer getScoredGoals() {
        return scoredGoals;
    }

    public void setScoredGoals(Integer scoredGoals) {
        this.scoredGoals = scoredGoals;
    }

    public Integer getLostGoals() {
        return lostGoals;
    }

    public void setLostGoals(Integer lostGoals) {
        this.lostGoals = lostGoals;
    }

    public Integer getYearOfEstablishment() {
        return yearOfEstablishment;
    }

    public void setYearOfEstablishment(Integer yearOfEstablishment) {
        this.yearOfEstablishment = yearOfEstablishment;
    }

    public String getHighestLeague() {
        return highestLeague;
    }

    public void setHighestLeague(String highestLeague) {
        this.highestLeague = highestLeague;
    }

    @Override
    public String toString() {
        return "ConcreteTeam{" +
                "teamName='" + teamName + '\'' +
                ", website='" + website + '\'' +
                ", nameOfGroup='" + nameOfGroup + '\'' +
                ", playedMatches=" + playedMatches +
                ", points=" + points +
                ", scoredGoals=" + scoredGoals +
                ", lostGoals=" + lostGoals +
                ", yearOfEstablishment=" + yearOfEstablishment +
                '}';
    }
}
