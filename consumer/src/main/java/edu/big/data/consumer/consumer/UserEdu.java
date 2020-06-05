package edu.big.data.consumer.consumer;

import edu.big.data.common.Topics;
import edu.big.data.consumer.dao.ConsumerDao;
import edu.big.data.consumer.db.UserEduDB;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserEdu {
    private final ConsumerDao consumerDao;

    @Autowired
    public UserEdu(ConsumerDao consumerDao) {
        this.consumerDao = consumerDao;
    }

    @KafkaListener(topics = Topics.USER_EDU)
    public void consumer(ConsumerRecord<String, String> record) {
        String[] values = record.value().split("\001");
        UserEduDB userEduDB = new UserEduDB();
        userEduDB.setUId(values[0]);
        userEduDB.setDegree(getValue(values, 1));
        userEduDB.setSchoolName(getValue(values, 2));
        userEduDB.setMajorStr(getValue(values, 3));
        //log.info("user edu:{}", userEduDB);
        consumerDao.insertUserEdu(userEduDB);
    }

    private String getValue(String[] values, int index) {
        if (index < values.length) {
            return values[index];
        }
        return null;
    }

}
