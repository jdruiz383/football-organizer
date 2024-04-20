package com.footballorg.models;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Teams")
public class Team 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	@Column(name = "league")
	String league;
	
	@Column(name = "conference")
	String conference;
	
	@Column(name = "name")
	String name;
	
	@Column(name = "network")
	String network;
	
	@Column(name = "start")
	LocalDate start;
	
	@Column(name = "time")
	LocalTime time;
	
	@Column(name = "text")
	String text;
	
	@Column(name= "end")
	LocalDate end;
	
	String color;
	

	public Team(Long id, String league, String conference, String name, String network, LocalDate start, LocalTime time,String text, LocalDate end) {
		this.id = id;
		this.league = league;
		this.conference = conference;
		this.name = name;
		this.start = start;
		this.time = time;
		this.text = text;
		this.end = end;
	}
	
	public Team() 
	{
		// TODO Auto-generated constructor stub
	}

	public LocalDate getStart() {
		return start;
	}
	public void setStart(LocalDate start) {
		this.start = start;
	}
	public LocalTime getTime() {
		return time;
	}
	public void setTime(LocalTime time) {
		this.time = time;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLeague() {
		return league;
	}
	public void setLeague(String league) {
		this.league = league;
	}
	public String getConference() {
		return conference;
	}
	public void setConference(String conference) {
		this.conference = conference;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getNetwork() {
		return network;
	}

	public void setNetwork(String network) {
		this.network = network;
	}
	
	public String getText() {
		
		text = name + " " + conference + " " + league + " " + network + " " + start + " " + time; 
		
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
    
    public LocalDate getEnd() {
    	return start;
    }
    
    public void setEnd(LocalDate end)
    {
    	this.end = start;
    }
	
	@Override
	public int hashCode() {
		return Objects.hash(conference, start, id, league, name, network, start, time, end);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Team other = (Team) obj;
		return Objects.equals(conference, other.conference) && Objects.equals(start, other.start)
				&& Objects.equals(id, other.id) && Objects.equals(league, other.league)
				&& Objects.equals(name, other.name) && Objects.equals(network, other.network) && Objects.equals(time, other.time) && Objects.equals(color, other.color) && Objects.equals(end, other.end);
	}

	@Override
	public String toString() {
		return "Team [id=" + id + ", league=" + league + ", conference=" + conference + ", name=" + name + ", network" + network +", Game Date="
				+ start + ", time=" + time + "]";
	}
	
}
