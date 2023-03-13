package se.harbil.policetwitter.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PoliceEventConsumer {

    private final PoliceEventProcessor policeEventProcessor;

    public PoliceEventConsumer(PoliceEventProcessor policeEventProcessor) {
        this.policeEventProcessor = policeEventProcessor;
    }

    @KafkaListener(topics = "police-event", groupId = "police-twitter")
    public void consumePoliceEvent(ConsumerRecord<String, String> payload) {
        policeEventProcessor.processEvent(payload);
    }
}
