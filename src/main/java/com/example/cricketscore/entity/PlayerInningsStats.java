package com.example.cricketscore.entity;
import jakarta.persistence.*;
import lombok.Data;
@Data @Entity public class PlayerInningsStats {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @ManyToOne @JoinColumn(name="innings_id") private Innings innings;
    @ManyToOne @JoinColumn(name="player_id") private Player player;
    private Integer runs = 0;
    private Integer ballsFaced = 0;
    private Integer fours = 0;
    private Integer sixes = 0;
    private Integer wickets = 0;
    private Integer runsConceded = 0;
    private Integer ballsBowled = 0;
    private String status = "NOT_OUT";
}
