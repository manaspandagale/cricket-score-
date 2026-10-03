package com.example.cricketscore.service;
import com.example.cricketscore.entity.*;
import com.example.cricketscore.repository.*;
import com.example.cricketscore.dto.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ScoreService {
    private final InningsRepository inningsRepo;
    private final BallEventRepository eventRepo;
    private final PlayerInningsStatsRepository statsRepo;
    private final PlayerRepository playerRepo;
    
    public ScoreService(InningsRepository ir, BallEventRepository ber, PlayerInningsStatsRepository psr, PlayerRepository pr) {
        this.inningsRepo = ir; this.eventRepo = ber; this.statsRepo = psr; this.playerRepo = pr;
    }
    
    @Transactional
    public BallEvent processEvent(EventRequest req) {
        Innings innings = inningsRepo.findById(req.getInningsId()).orElseThrow();
        BallEvent ev = new BallEvent();
        ev.setInnings(innings);
        ev.setStriker(playerRepo.findById(req.getStrikerId()).orElseThrow());
        ev.setNonStriker(playerRepo.findById(req.getNonStrikerId()).orElseThrow());
        ev.setBowler(playerRepo.findById(req.getBowlerId()).orElseThrow());
        ev.setEventType(EventType.valueOf(req.getEventType()));
        ev.setRuns(req.getRuns() != null ? req.getRuns() : 0);
        ev.setTimestamp(LocalDateTime.now());
        
        PlayerInningsStats strikerStats = getOrCreateStats(innings, ev.getStriker());
        PlayerInningsStats bowlerStats = getOrCreateStats(innings, ev.getBowler());
        
        boolean isLegal = true;
        int teamRuns = ev.getRuns();
        int strikerRuns = 0;
        
        switch (ev.getEventType()) {
            case FOUR:
                teamRuns = 4; strikerRuns = 4; strikerStats.setFours(strikerStats.getFours() + 1); break;
            case SIX:
                teamRuns = 6; strikerRuns = 6; strikerStats.setSixes(strikerStats.getSixes() + 1); break;
            case WIDE:
            case NO_BALL:
                isLegal = false; teamRuns += 1; break;
            case WICKET:
                innings.setWickets(innings.getWickets() + 1);
                bowlerStats.setWickets(bowlerStats.getWickets() + 1);
                strikerStats.setStatus("OUT");
                break;
            case ONE: teamRuns = 1; strikerRuns = 1; break;
            case TWO: teamRuns = 2; strikerRuns = 2; break;
            case THREE: teamRuns = 3; strikerRuns = 3; break;
            default: break;
        }
        
        innings.setTotalRuns(innings.getTotalRuns() + teamRuns);
        strikerStats.setRuns(strikerStats.getRuns() + strikerRuns);
        bowlerStats.setRunsConceded(bowlerStats.getRunsConceded() + teamRuns);
        
        if (isLegal) {
            innings.setLegalBalls(innings.getLegalBalls() + 1);
            strikerStats.setBallsFaced(strikerStats.getBallsFaced() + 1);
            bowlerStats.setBallsBowled(bowlerStats.getBallsBowled() + 1);
        }
        
        inningsRepo.save(innings);
        statsRepo.save(strikerStats);
        statsRepo.save(bowlerStats);
        return eventRepo.save(ev);
    }
    
    private PlayerInningsStats getOrCreateStats(Innings i, Player p) {
        return statsRepo.findByInningsIdAndPlayerId(i.getId(), p.getId()).orElseGet(() -> {
            PlayerInningsStats s = new PlayerInningsStats();
            s.setInnings(i); s.setPlayer(p); return statsRepo.save(s);
        });
    }
    
    public ScoreResponse getScore(Long matchId) {
        List<Innings> inningsList = inningsRepo.findByMatchId(matchId);
        if(inningsList.isEmpty()) return new ScoreResponse();
        Innings current = inningsList.get(inningsList.size()-1);
        
        ScoreResponse res = new ScoreResponse();
        res.setMatchId(matchId);
        res.setScore(current.getTotalRuns());
        res.setWickets(current.getWickets());
        res.setOvers(current.getLegalBalls() / 6 + "." + current.getLegalBalls() % 6);
        res.setStatus("LIVE");
        if (current.getLegalBalls() > 0) {
            res.setRunRate((current.getTotalRuns() / (double)current.getLegalBalls()) * 6);
        } else {
            res.setRunRate(0.0);
        }
        return res;
    }

    public List<PlayerInningsStats> getStats(Long matchId) {
        List<Innings> inningsList = inningsRepo.findByMatchId(matchId);
        if(inningsList.isEmpty()) return List.of();
        Long inningsId = inningsList.get(inningsList.size()-1).getId();
        return statsRepo.findAll().stream().filter(s -> s.getInnings().getId().equals(inningsId)).toList();
    }
    
    public List<BallEvent> getEvents(Long matchId) {
        List<Innings> inningsList = inningsRepo.findByMatchId(matchId);
        if(inningsList.isEmpty()) return List.of();
        return eventRepo.findByInningsIdOrderByTimestampDesc(inningsList.get(inningsList.size()-1).getId());
    }
    
    public Match getMatchSummary(Long matchId) {
        return inningsRepo.findByMatchId(matchId).isEmpty() ? null : inningsRepo.findByMatchId(matchId).get(0).getMatch();
    }
}
