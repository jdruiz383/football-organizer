package com.footballorg.interfaces;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Repository;

import com.footballorg.models.Team;

@Repository
public interface TeamRepository extends JpaRepository <Team, Long>
{
	Optional<Team> findByName(String name);
	
	Optional<Team> findByConference(String conference);
	
	Optional<Team> findByLeague(String league);
	
	Optional<Team> findByNetwork(String network);

	Optional<Team> findById(Long id);

	Optional<Team> findByStart(LocalDate start);
	
	Optional<Team> findByText(String text);
	
	List<Team> findAll();
	
	@Query("from Team e where not(e.end < :from or e.start > :to)")
	public List<Team> findBetween(@Param("from") @DateTimeFormat(iso=ISO.DATE_TIME) LocalDate start, @Param("to") @DateTimeFormat(iso=ISO.DATE_TIME) LocalDate end);

	Optional<Team> deleteByText(String text);
	
}
