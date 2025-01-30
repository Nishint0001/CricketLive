package com.crick.apis.services;

import com.crick.apis.entities.CricketMatch;
import org.springframework.stereotype.Service;

import java.util.*;


public interface CricketMatchService
{
    //getAllMatches

    List<CricketMatch> getAllMatches();

    //getLiveMatches

    List<CricketMatch> getLiveMatches();

    //getPointsTable

    List<Map<String,String>>  getPointsTable();
}
