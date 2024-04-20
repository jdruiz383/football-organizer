package com.footballorg.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Event 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	String teamName;
	String teamLeague;
	String text = teamName + " " + teamLeague;
	
	@Column(name = "event_start")
	LocalDateTime start;
	
	@Column(name = "event_end")
	LocalDateTime end;
	
	String color;
	
	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamLeague() {
		return teamLeague;
	}

	public void setTeamLeague(String teamLeague) {
		this.teamLeague = teamLeague;
	}

	public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
    
}
