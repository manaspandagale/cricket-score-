package com.example.cricketscore.entity;
import jakarta.persistence.*;
import lombok.Data;
@Data @Entity public class Innings {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @ManyToOne @JoinColumn(name="match_id") private Match match;
    private Integer inningsNumber;
    @ManyToOne @JoinColumn(name="batting_team_id") private Team battingTeam;
    @ManyToOne @JoinColumn(name="bowling_team_id") private Team bowlingTeam;
    private Integer totalRuns = 0;
    private Integer wickets = 0;
    private Integer legalBalls = 0;
    private Integer target;
    private String status;
}
