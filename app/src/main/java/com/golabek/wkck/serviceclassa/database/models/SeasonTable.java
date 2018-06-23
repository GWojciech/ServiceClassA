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
public class SeasonTable implements Serializable {
    private Integer id;
    private Integer pos;
    private String name;
    private Integer playedMatches;
    private Integer points;
    private Integer scoredGoals;
    private Integer lostGoals;

    public SeasonTable() {
    }

    public SeasonTable(Integer id, Integer pos, String name, Integer playedMatches, Integer points, Integer scoredGoals, Integer lostGoals) {
        this.id = id;
        this.pos = pos;
        this.name = name;
        this.playedMatches = playedMatches;
        this.points = points;
        this.scoredGoals = scoredGoals;
        this.lostGoals = lostGoals;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public Integer getPos() {
        return pos;
    }

    public void setPos(Integer pos) {
        this.pos = pos;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "SeasonTable{" +
                "id=" + id +
                ", pos=" + pos +
                ", name='" + name + '\'' +
                ", playedMatches=" + playedMatches +
                ", points=" + points +
                ", scoredGoals=" + scoredGoals +
                ", lostGoals=" + lostGoals +
                '}';
    }
}
