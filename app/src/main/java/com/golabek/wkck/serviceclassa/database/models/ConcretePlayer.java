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
public class ConcretePlayer implements Serializable {
        
    private Integer id;
    private String name;
    private String surname;
    private String birthDate;
    private Integer goals;
    private Integer assists;
    private String website;
    private String teamName;

    public ConcretePlayer() {
    }

    public ConcretePlayer(Integer id, String name, String surname, String birthDate, Integer goals, Integer assists, String website, String teamName) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.goals = goals;
        this.assists = assists;
        this.website = website;
        this.teamName = teamName;
    }

    public ConcretePlayer(Integer id, String name, String surname, Integer goals, Integer assists, String teamName) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.goals = goals;
        this.assists = assists;
        this.teamName = teamName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
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

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    @Override
    public String toString() {
        return "ConcretePlayer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthDate=" + birthDate +
                ", goals=" + goals +
                ", assists=" + assists +
                ", website='" + website + '\'' +
                ", teamName='" + teamName + '\'' +
                '}';
    }
}
