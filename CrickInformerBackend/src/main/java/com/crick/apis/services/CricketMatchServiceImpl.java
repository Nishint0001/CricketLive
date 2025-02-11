package com.crick.apis.services;

import com.crick.apis.entities.CricketMatch;
import com.crick.apis.repositories.CricketMatchRepo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class CricketMatchServiceImpl implements CricketMatchService
{
    private final CricketMatchRepo cricketMatchRepo;

    public CricketMatchServiceImpl(CricketMatchRepo cricketMatchRepo) {
        this.cricketMatchRepo = cricketMatchRepo;
    }

    @Override
    public List<CricketMatch> getAllMatches()
    {
        return cricketMatchRepo.findAll();
    }

@Override
public List<CricketMatch> getLiveMatches()
{
    List<CricketMatch> matches = new ArrayList<>();
    try {
        String url = "https://www.cricbuzz.com/cricket-match/live-scores";
        Document document = Jsoup.connect(url).get();
        Elements liveScoreElements = document.select("div.cb-mtch-lst.cb-tms-itm");
        for (Element match : liveScoreElements) {
            HashMap<String, String> liveMatchInfo = new LinkedHashMap<>();
            String teamsHeading = match.select("h3.cb-lv-scr-mtch-hdr").select("a").text();
            String matchNumberVenue = match.select("span").text();
            Elements matchBatTeamInfo = match.select("div.cb-hmscg-bat-txt");
            String battingTeam = matchBatTeamInfo.select("div.cb-hmscg-tm-nm").text();
            String score = matchBatTeamInfo.select("div.cb-hmscg-tm-nm+div").text();
            Elements bowlTeamInfo = match.select("div.cb-hmscg-bwl-txt");
            String bowlTeam = bowlTeamInfo.select("div.cb-hmscg-tm-nm").text();
            String bowlTeamScore = bowlTeamInfo.select("div.cb-hmscg-tm-nm+div").text();
            String textLive = match.select("div.cb-text-live").text();
            String textComplete = match.select("div.cb-text-complete").text();
            //getting match link
            String matchLink = match.select("a.cb-lv-scrs-well.cb-lv-scrs-well-live").attr("href").toString();

            CricketMatch match1 = new CricketMatch();
            match1.setTeamHeading(teamsHeading);
            match1.setMatchNumberVenue(matchNumberVenue);
            match1.setBattingTeam(battingTeam);
            match1.setBattingTeamScore(score);
            match1.setBowlTeam(bowlTeam);
            match1.setBowlTeamScore(bowlTeamScore);
            match1.setLiveText(textLive);
            match1.setMatchLink(matchLink);
            match1.setTextComplete(textComplete);
            match1.setMatchStatus();


            matches.add(match1);

//          update the match in database
           updateCricketMatchInDB(match1);


        }
    }
    catch (IOException e) {
        throw new RuntimeException(e);
    }
    return matches;
}

    private void updateCricketMatchInDB(CricketMatch match1)
    {
       CricketMatch match= cricketMatchRepo.findByTeamHeading(match1.getTeamHeading()).orElse(null);
        if(match==null)
        {
            cricketMatchRepo.save(match1);
        }
        else
        {
            match1.setMatchId(match.getMatchId());
            cricketMatchRepo.save(match1);
        }
    }

    @Override
    public List<Map<String, String>> getPointsTable() {
        return List.of();
    }
}
