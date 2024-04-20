package com.footballorg.services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.footballorg.interfaces.TeamRepository;
import com.footballorg.models.Team;


@Service
public class TeamService 
{
	private final TeamRepository teamRepository;
	
	public TeamService(TeamRepository teamRepository)
	{
		this.teamRepository = teamRepository;
	}
	
	public Team addTeam(Long id, String league, String conference, String name, String network, LocalDate start, LocalTime time, String text, LocalDate end)
	{
		if(name == null)
		{
			return null;
		}
		else
		{
			if(teamRepository.findByText(text).isPresent())
			{
				return null;
			}
			else
			{
			Team team = new Team();
			team.setId(id);
			team.setName(name);
			team.setConference(conference);
			team.setLeague(league);
			team.setNetwork(network);
			team.setStart(start);
			team.setTime(time);
			team.setText(text);
			team.setEnd(end);
			return teamRepository.save(team);
			}
		}
	}
	
	public Team searchTeamName(String name)
	{
		return teamRepository.findByName(name).orElse(null);
	}
	
	public Team searchTeamLeague(String league)
	{
		return teamRepository.findByLeague(league).orElse(null);
	}
	
	public Team searchTeamConference(String conference)
	{
		return teamRepository.findByConference(conference).orElse(null);
	}
	
	public Team searchTeamNetwork(String network)
	{
		return teamRepository.findByNetwork(network).orElse(null);
	}
	
	public Team searchTeamDate(LocalDate start)
	{
		return teamRepository.findByStart(start).orElse(null);
	}
	
	public List<Team> getAll()
	{
		return teamRepository.findAll();
	}
}
