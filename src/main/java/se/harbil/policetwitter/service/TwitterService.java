package se.harbil.policetwitter.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import se.harbil.policetwitter.client.TwitterClient;
import se.harbil.policetwitter.model.PoliceEventKafkaModel;
import se.harbil.policetwitter.util.ContentFilter;
import se.harbil.policetwitter.util.MessageComposer;

@Slf4j
@Service
public class TwitterService {

    private final TwitterClient twitterClient;
    private final HashTagCreator hashTagCreator;
    private final ContentFilter contentFilter;
    private final MessageComposer messageComposer;


    public TwitterService(TwitterClient twitterClient,
        HashTagCreator hashTagCreator, ContentFilter contentFilter,
        MessageComposer messageComposer) {
        this.twitterClient = twitterClient;
        this.hashTagCreator = hashTagCreator;
        this.contentFilter = contentFilter;
        this.messageComposer = messageComposer;
    }

    public void sendTweet(PoliceEventKafkaModel event) {
        try {
            if (contentFilter.hasUnwantedContent(event)) {
                log.debug("Found unwanted content: {} location: {}", event.getSummary(),
                    event.getLocationName());
                return;
            }
            String hashTag = hashTagCreator.getHashTag(event);

            String message = messageComposer.createMessageNotLongerThen280Char(event, hashTag);

            twitterClient.sendTweet(message);
        } catch (Exception e) {
            log.warn("Failed to send tweet: {} got error: {}", event, e);

        }
    }
}
