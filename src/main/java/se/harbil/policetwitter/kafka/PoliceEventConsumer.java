package se.harbil.policetwitter.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PoliceEventConsumer {

    private final PoliceEventProcessor policeEventProcessor;

    @KafkaListener(topics = "police-event", groupId = "police-twitter")
    public void consumePoliceEvent(final ConsumerRecord<String, String> payload) {
        policeEventProcessor.processEvent(payload);
    }
}
