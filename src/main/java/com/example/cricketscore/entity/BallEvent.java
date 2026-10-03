package com.example.cricketscore.entity;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
@Data @Entity public class BallEvent {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @ManyToOne @JoinColumn(name="innings_id") private Innings innings;
    private Integer overNumber;
    private Integer ballNumber;
    @ManyToOne @JoinColumn(name="striker_id") private Player striker;
    @ManyToOne @JoinColumn(name="non_striker_id") private Player nonStriker;
    @ManyToOne @JoinColumn(name="bowler_id") private Player bowler;
    @Enumerated(EnumType.STRING) private EventType eventType;
    private Integer runs;
    private Integer extras;
    private Boolean isWicket;
    private LocalDateTime timestamp;
}
