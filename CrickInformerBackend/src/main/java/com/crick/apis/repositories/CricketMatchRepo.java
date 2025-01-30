package com.crick.apis.repositories;

import com.crick.apis.entities.CricketMatch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CricketMatchRepo extends JpaRepository<CricketMatch,Integer>
{
    //fetch match by team
    Optional<CricketMatch> findByTeamHeading(String teamHeading);
}
