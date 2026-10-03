package com.example.cricketscore.config;

import com.example.cricketscore.entity.*;
import com.example.cricketscore.repository.*;
import com.example.cricketscore.dto.EventRequest;
import com.example.cricketscore.service.ScoreService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {
    private final TeamRepository teamRepo;
    private final PlayerRepository playerRepo;
    private final MatchRepository matchRepo;
    private final InningsRepository inningsRepo;
    private final ScoreService scoreService;
    
    public DataSeeder(TeamRepository t, PlayerRepository p, MatchRepository m, InningsRepository i, ScoreService s) {
        this.teamRepo = t; this.playerRepo = p; this.matchRepo = m; this.inningsRepo = i; this.scoreService = s;
    }
    
    @Override
    public void run(String... args) throws Exception {
        if(teamRepo.count() == 0) {
            Team ind = new Team(); ind.setName("India"); ind.setShortName("IND"); ind = teamRepo.save(ind);
            Team aus = new Team(); aus.setName("Australia"); aus.setShortName("AUS"); aus = teamRepo.save(aus);
            
            Player p1 = new Player(); p1.setName("Virat Sharma"); p1.setTeam(ind); p1.setRole(Role.BATSMAN); p1 = playerRepo.save(p1);
            Player p2 = new Player(); p2.setName("Rohit Patil"); p2.setTeam(ind); p2.setRole(Role.BATSMAN); p2 = playerRepo.save(p2);
            Player p3 = new Player(); p3.setName("Rahul Patil"); p3.setTeam(aus); p3.setRole(Role.BOWLER); p3 = playerRepo.save(p3);
            Player p4 = new Player(); p4.setName("Pat Cummins"); p4.setTeam(aus); p4.setRole(Role.BOWLER); p4 = playerRepo.save(p4);
            
            Match m = new Match(); 
            m.setTeamA(ind); 
            m.setTeamB(aus); 
            m.setMatchStatus(MatchStatus.LIVE); 
            m.setMatchType(MatchType.T20);
            m.setVenue("Pune Stadium");
            m.setTossWinner(ind);
            m.setTossDecision("BAT");
            m = matchRepo.save(m);
            
            Innings i = new Innings(); 
            i.setMatch(m); 
            i.setBattingTeam(ind); 
            i.setBowlingTeam(aus); 
            i.setInningsNumber(1); 
            i = inningsRepo.save(i);

            // Add events
            addEvent(i.getId(), p1.getId(), p2.getId(), p3.getId(), "FOUR", 4);
            addEvent(i.getId(), p1.getId(), p2.getId(), p3.getId(), "ONE", 1);
            addEvent(i.getId(), p2.getId(), p1.getId(), p3.getId(), "SIX", 6);
            addEvent(i.getId(), p2.getId(), p1.getId(), p3.getId(), "DOT", 0);
            addEvent(i.getId(), p2.getId(), p1.getId(), p3.getId(), "TWO", 2);
            addEvent(i.getId(), p2.getId(), p1.getId(), p3.getId(), "WICKET", 0);
            
            // Next over
            addEvent(i.getId(), p1.getId(), p2.getId(), p4.getId(), "FOUR", 4);
            addEvent(i.getId(), p1.getId(), p2.getId(), p4.getId(), "SIX", 6);
        }
    }
    
    private void addEvent(Long inId, Long strId, Long nStrId, Long bowlId, String type, int runs) {
        EventRequest req = new EventRequest();
        req.setInningsId(inId);
        req.setStrikerId(strId);
        req.setNonStrikerId(nStrId);
        req.setBowlerId(bowlId);
        req.setEventType(type);
        req.setRuns(runs);
        scoreService.processEvent(req);
    }
}
