package com.golabek.wkck.serviceclassa.database.models;


public class Matches {
    private Integer id;
    private String dateOfMatch;
    private String homeTeam;
    private String awayTeam;
    private Boolean played;
    private Integer homeTeamGoals;
    private Integer awayTeamGoals;

    public Matches(){ }

    public Matches(Integer id, String dateOfMatch, String homeTeam, String awayTeam, Boolean played, Integer homeTeamGoals, Integer awayTeamGoals) {
        this.id = id;
        this.dateOfMatch = dateOfMatch;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.played = played;
        this.homeTeamGoals = homeTeamGoals;
        this.awayTeamGoals = awayTeamGoals;
    }

    public Matches(Integer id, String dateOfMatch, String homeTeam, String awayTeam, Boolean played) {
        this.id = id;
        this.dateOfMatch = dateOfMatch;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.played = played;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDateOfMatch() {
        return dateOfMatch;
    }

    public void setDateOfMatch(String dateOfMatch) {
        this.dateOfMatch = dateOfMatch;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    public Boolean getPlayed() {
        return played;
    }

    public void setPlayed(Boolean played) {
        this.played = played;
    }

    public Integer getHomeTeamGoals() {
        return homeTeamGoals;
    }

    public void setHomeTeamGoals(Integer homeTeamGoals) {
        this.homeTeamGoals = homeTeamGoals;
    }

    public Integer getAwayTeamGoals() {
        return awayTeamGoals;
    }

    public void setAwayTeamGoals(Integer awayTeamGoals) {
        this.awayTeamGoals = awayTeamGoals;
    }

    @Override
    public String toString() {
        return "Matches{" +
                "dateOfMatch=" + dateOfMatch +
                ", homeTeam='" + homeTeam + '\'' +
                ", awayTeam='" + awayTeam + '\'' +
                ", played=" + played +
                ", homeTeamGoals=" + homeTeamGoals +
                ", awayTeamGoals=" + awayTeamGoals +
                '}';
    }
}
