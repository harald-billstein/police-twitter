package se.harbil.policetwitter.kafka;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.stereotype.Component;
import se.harbil.policetwitter.model.PoliceEventKafkaModel;
import se.harbil.policetwitter.service.TwitterService;


@Slf4j
@Component
@RequiredArgsConstructor
public class PoliceEventProcessor {

    private final TwitterService twitterService;

    private final ObjectMapper objectMapper;

    public void processEvent(final ConsumerRecord<String, String> payload) {
        try {
            PoliceEventKafkaModel event = objectMapper.readValue(payload.value(),
                PoliceEventKafkaModel.class);
            twitterService.sendTweet(event);
        } catch (Exception e) {
            log.warn("Failed to process payload");
        }
    }
}
