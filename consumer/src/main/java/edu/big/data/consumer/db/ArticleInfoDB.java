package edu.big.data.consumer.db;

import lombok.Data;

@Data
public class ArticleInfoDB {
    private String aId;
    private Integer diggCount;
    private Integer buryCount;
    private Integer viewCount;
    private Integer commentCount;
    private String type;
    private String status;
    private Integer isTop;
}
