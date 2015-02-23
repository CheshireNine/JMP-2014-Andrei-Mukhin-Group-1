package com.epam.ticketsmanagement.vo;

import java.util.List;

import com.epam.ticketsmanagement.model.FilmSession;


public class FilmVO {

    private Long id;

    private String name;

    private String description;

    private List<FilmSession> sessions;

    public FilmVO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<FilmSession> getSessions() {
        return sessions;
    }

    public void setSessions(List<FilmSession> sessions) {
        this.sessions = sessions;
    }

}
