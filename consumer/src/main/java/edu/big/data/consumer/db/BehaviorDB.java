package edu.big.data.consumer.db;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BehaviorDB {

    private String uId;
    private String behavior;
    private String aId;
    private LocalDateTime behaviorTime;
}
