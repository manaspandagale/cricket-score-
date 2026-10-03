package com.example.cricketscore.controller;
import com.example.cricketscore.entity.Team;
import com.example.cricketscore.repository.TeamRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/teams")
public class TeamController {
    private final TeamRepository teamRepo;
    public TeamController(TeamRepository teamRepo) { this.teamRepo = teamRepo; }
    
    @GetMapping
    public List<Team> getAll() { return teamRepo.findAll(); }
    
    @PostMapping
    public Team create(@RequestBody Team team) { return teamRepo.save(team); }
}
