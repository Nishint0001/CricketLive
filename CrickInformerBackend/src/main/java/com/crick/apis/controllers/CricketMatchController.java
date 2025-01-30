package com.crick.apis.controllers;

import com.crick.apis.entities.CricketMatch;
import com.crick.apis.services.CricketMatchService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cricketMatch")
public class CricketMatchController
{
private final CricketMatchService cricketMatchService;

    public CricketMatchController(CricketMatchService cricketMatchService)
    {
        this.cricketMatchService = cricketMatchService;
    }

    //get Live matches
    @GetMapping("/live")
    public ResponseEntity<List<CricketMatch>> getLiveMatches()
    {
        return new ResponseEntity<>(cricketMatchService.getLiveMatches(), HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<List<CricketMatch>> getAllMatches()
    {
        return new ResponseEntity<>(cricketMatchService.getAllMatches(),HttpStatus.OK);
    }




}
