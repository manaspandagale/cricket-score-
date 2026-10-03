package com.example.cricketscore.dto;
import lombok.Data;
@Data public class ScoreResponse {
    private Long matchId;
    private String team;
    private Integer score;
    private Integer wickets;
    private String overs;
    private Double runRate;
    private Integer target;
    private Integer runsRequired;
    private Integer ballsRemaining;
    private Double requiredRunRate;
    private String status;
}
