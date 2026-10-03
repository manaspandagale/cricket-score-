package com.example.cricketscore.entity;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
@Data @Entity @Table(name="cricket_match") public class Match {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @ManyToOne @JoinColumn(name="team_a_id") private Team teamA;
    @ManyToOne @JoinColumn(name="team_b_id") private Team teamB;
    private String venue;
    private LocalDateTime matchDate;
    @Enumerated(EnumType.STRING) private MatchType matchType;
    private Integer numberOfOvers;
    @ManyToOne @JoinColumn(name="toss_winner_id") private Team tossWinner;
    private String tossDecision;
    @Enumerated(EnumType.STRING) private MatchStatus matchStatus;
}
