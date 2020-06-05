package edu.big.data.consumer.consumer;

import edu.big.data.common.Topics;
import edu.big.data.consumer.dao.ConsumerDao;
import edu.big.data.consumer.db.BehaviorDB;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Component
public class Behavior {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.0");
    private final ConsumerDao consumerDao;

    @Autowired
    public Behavior(ConsumerDao consumerDao) {
        this.consumerDao = consumerDao;
    }

    @KafkaListener(topics = Topics.USER_BEHAVIOR)
    public void consumer(ConsumerRecord<String, String> record) {
        String[] values = record.value().split("\001");
        BehaviorDB behaviorDB = new BehaviorDB();
        behaviorDB.setUId(values[0]);
        behaviorDB.setBehavior(values[1]);
        behaviorDB.setAId(values[2]);
        behaviorDB.setBehaviorTime(LocalDateTime.parse(values[3], formatter));
        log.info("behavior:{}", behaviorDB);
        consumerDao.insertBehavior(behaviorDB);
    }
}
