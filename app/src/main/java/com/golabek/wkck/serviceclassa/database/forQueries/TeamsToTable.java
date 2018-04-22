package com.golabek.wkck.serviceclassa.database.forQueries;

public class TeamsToTable {

    private String teamName;
    private Integer points;
    private Integer matchesPlayed;
    private Integer scoredGoals;
    private Integer lostGoals;

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Integer getMatchesPlayed() {
        return matchesPlayed;
    }

    public void setMatchesPlayed(Integer matchesPlayed) {
        this.matchesPlayed = matchesPlayed;
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

    @Override
    public String toString(){
        return this.teamName + " | " + this.points + " | " + this.matchesPlayed + " | " + this.scoredGoals + " | " + this.lostGoals + " \n ";
    }

}
