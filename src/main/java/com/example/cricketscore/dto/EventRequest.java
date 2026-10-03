package com.example.cricketscore.dto;
import lombok.Data;
@Data public class EventRequest {
    private Long inningsId;
    private Long strikerId;
    private Long nonStrikerId;
    private Long bowlerId;
    private String eventType;
    private Integer runs;
}
