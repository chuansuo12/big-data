package edu.big.data.consumer.consumer;

import edu.big.data.common.Topics;
import edu.big.data.consumer.dao.ConsumerDao;
import edu.big.data.consumer.db.ArticleInfoDB;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Article {

    private final ConsumerDao consumerDao;

    @Autowired
    public Article(ConsumerDao consumerDao) {
        this.consumerDao = consumerDao;
    }

    @KafkaListener(topics = Topics.ARTICLE_INFO)
    public void consumer(ConsumerRecord<String, String> record) {
        String[] values = record.value().split("\001");
        ArticleInfoDB articleInfoDB = new ArticleInfoDB();
        articleInfoDB.setAId(values[0]);
        articleInfoDB.setDiggCount(Integer.valueOf(values[1]));
        articleInfoDB.setBuryCount(Integer.valueOf(values[2]));
        articleInfoDB.setViewCount(Integer.valueOf(values[3]));
        articleInfoDB.setCommentCount(Integer.valueOf(values[4]));
        articleInfoDB.setType(values[5]);
        articleInfoDB.setIsTop(Integer.valueOf(values[6]));
        articleInfoDB.setStatus(values[7]);
        //log.info("article :{}", articleInfoDB);
        consumerDao.insertArticle(articleInfoDB);
    }
}
