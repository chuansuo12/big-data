package edu.big.data.consumer.db;

import lombok.Data;

@Data
public class UserBasicDB {
    private String uId;
    private String gender;
    private String status;
    private Integer followNum;
    private Integer fansNum;
    private Integer friendNum;
}
