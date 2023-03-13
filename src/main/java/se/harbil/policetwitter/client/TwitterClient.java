package se.harbil.policetwitter.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import twitter4j.Twitter;
import twitter4j.TwitterException;
@Slf4j
@Service
public class TwitterClient {
    private final Twitter twitterClient;

    public TwitterClient(@Qualifier("twitterClient") Twitter  twitterClient) {
        this.twitterClient = twitterClient;
    }

    public void sendTweet(String message) throws TwitterException {
        twitterClient.updateStatus(message);
      log.info(message);
    }
}
