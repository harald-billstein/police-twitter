package se.harbil.policetwitter.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import se.harbil.policetwitter.client.TwitterClient;
import se.harbil.policetwitter.model.PoliceEventKafkaModel;
import se.harbil.policetwitter.model.TwitterRequest;
import se.harbil.policetwitter.util.ContentFilter;
import se.harbil.policetwitter.util.MessageComposer;

@Slf4j
@Service
@RequiredArgsConstructor
public class TwitterService {
    private final TwitterClient twitterClient;
    private final ContentFilter contentFilter;
    private final MessageComposer messageComposer;

    public void sendTweet(final PoliceEventKafkaModel event) {
        try {
            if (contentFilter.hasUnwantedContent(event)) {
                log.debug("Found unwanted content: {} location: {}", event.getSummary(),
                    event.getLocationName());
                return;
            }

            TwitterRequest request = messageComposer.createMessageNotLongerThen280Char(event);

            String response = twitterClient.sendTweetV2(request);
            log.info("Response: {}", response);
        } catch (Exception e) {
            log.warn("Failed to send tweet: {} got error: {}", event, e.getMessage());
        }
    }
}
