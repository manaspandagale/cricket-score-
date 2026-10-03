package com.example.cricketscore.controller;
import com.example.cricketscore.entity.*;
import com.example.cricketscore.repository.*;
import com.example.cricketscore.dto.*;
import com.example.cricketscore.service.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/matches")
public class MatchController {
    private final MatchRepository matchRepo;
    private final ScoreService scoreService;
    private final InningsRepository inningsRepo;
    
    public MatchController(MatchRepository matchRepo, ScoreService scoreService, InningsRepository inningsRepo) {
        this.matchRepo = matchRepo; this.scoreService = scoreService; this.inningsRepo = inningsRepo;
    }
    
    @GetMapping
    public List<Match> getAll() { return matchRepo.findAll(); }
    
    @GetMapping("/{id}/score")
    public ScoreResponse getScore(@PathVariable Long id) {
        return scoreService.getScore(id);
    }
    
    @PostMapping("/{id}/events")
    public BallEvent addEvent(@PathVariable Long id, @RequestBody EventRequest req) {
        return scoreService.processEvent(req);
    }

    @GetMapping("/{id}/stats")
    public List<PlayerInningsStats> getStats(@PathVariable Long id) {
        return scoreService.getStats(id);
    }
    
    @GetMapping("/{id}/events/recent")
    public List<BallEvent> getRecentEvents(@PathVariable Long id) {
        return scoreService.getEvents(id);
    }
    
    @GetMapping("/{id}/summary")
    public Match getSummary(@PathVariable Long id) {
        return scoreService.getMatchSummary(id);
    }
}
