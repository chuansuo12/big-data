package edu.big.data.hours.message.producer;

import edu.big.data.common.Topics;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

@Slf4j
@Component
public class BehaviorProducer implements CommandLineRunner {


    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public BehaviorProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        String filePath = "/home/chuansuo12/code-project/big-data/plan3/userBehavior/userBehavior";
        InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(filePath));
        BufferedReader reader = new BufferedReader(inputStreamReader);
        String readStr;
        while ((readStr = reader.readLine()) != null) {
            log.info("read str:{}", readStr);
            kafkaTemplate.send(Topics.USER_BEHAVIOR, readStr);
            Thread.sleep(50);
        }
    }

}
