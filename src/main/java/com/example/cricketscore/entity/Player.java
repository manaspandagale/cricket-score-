package com.example.cricketscore.entity;
import jakarta.persistence.*;
import lombok.Data;
@Data @Entity public class Player {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    private String name;
    private Integer jerseyNumber;
    @Enumerated(EnumType.STRING) private Role role;
    private String battingStyle;
    private String bowlingStyle;
    @ManyToOne @JoinColumn(name="team_id") private Team team;
}
