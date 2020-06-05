package edu.big.data.consumer.consumer;

import edu.big.data.common.Topics;
import edu.big.data.consumer.dao.ConsumerDao;
import edu.big.data.consumer.db.UserBasicDB;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserBasic {

    private final ConsumerDao consumerDao;

    @Autowired
    public UserBasic(ConsumerDao consumerDao) {
        this.consumerDao = consumerDao;
    }

    @KafkaListener(topics = Topics.USER_BASIC)
    public void consumer(ConsumerRecord<String, String> record) {
        String[] values = record.value().split("\001");
        UserBasicDB userBasicDB = new UserBasicDB();
        userBasicDB.setUId(values[0]);
        userBasicDB.setGender(values[1]);
        userBasicDB.setStatus(values[2]);
        userBasicDB.setFollowNum(Integer.valueOf(values[3]));
        userBasicDB.setFansNum(Integer.valueOf(values[4]));
        userBasicDB.setFriendNum(Integer.valueOf(values[5]));
        //log.info("user basic:{}", userBasicDB);
        consumerDao.insertUserBasic(userBasicDB);
    }
}
