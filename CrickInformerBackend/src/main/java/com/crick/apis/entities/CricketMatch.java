package com.crick.apis.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "CricketMatch")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CricketMatch
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int matchId;

    private String teamHeading;

    private String matchNumberVenue;

    private String battingTeam;

    private String battingTeamScore;

    private String bowlTeam;

    private String bowlTeamScore;

    private String liveText;

    private String matchLink;

    private String textComplete;

    private CricketMatchStatus_enum status;

    private Date date=new Date();

    public void setMatchStatus()
    {
        if(textComplete.isBlank())
        {
            this.status= CricketMatchStatus_enum.LIVE;
        }
        else
        {
            this.status= CricketMatchStatus_enum.COMPLETED;
        }
    }
}
