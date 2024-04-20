package com.footballorg.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.footballorg.models.Team;
import com.footballorg.services.TeamService;

@Controller
public class TeamController 
{
	@Autowired
	private TeamService teamService;
	
	@GetMapping("/personal")
	public String getPersonalPage()
	{
		return "personal_page";
	}
	
	@GetMapping("/uploadTeams")
	public String getUploadPage()
	{
		return "upload_page";
	}
	
	@GetMapping("/allTeams")
	public String getAllTeams()
	{
		return "allTeams_page";
	}
	
	@GetMapping("/addTeam")
	public String getAddTeamPage(Model model)
	{
		model.addAttribute("addTeamRequest", new Team());
		return "addTeam_page";
	}
	
	@PostMapping("/addTeam")
	public String addTeam(@ModelAttribute Team team, Model model)
	{
		System.out.println("Add Team: " + team);
		
		Team addTeam = teamService.addTeam(team.getId(), team.getName(), team.getConference(), team.getLeague(), team.getNetwork(), team.getStart(), team.getTime(), team.getText(), team.getEnd());
		
		if(addTeam == null)
		{
			return "addTeamFail_page";
		}
		else
		{
			return "addTeamSuccess_page";
		}
	}
	
	@ModelAttribute("teams")
	public List<Team> getDetails()
	{
		return teamService.getAll();
	}

}
