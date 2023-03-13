package se.harbil.policetwitter.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

@Configuration
public class TwitterClientConfiguration {

    private final String twitterConsumerKey;
    private final String twitterConsumerSecret;
    private final String twitterAccessToken;
    private final String twitterTokenSecret;

    public TwitterClientConfiguration(@Value("${twitterConsumerKey}") String twitterConsumerKey,
        @Value("${twitterConsumerSecret}") String twitterConsumerSecret,
        @Value("${twitterAccessToken}") String twitterAccessToken,
        @Value("${twitterTokenSecret}") String twitterTokenSecret) {
        this.twitterConsumerKey = twitterConsumerKey;
        this.twitterConsumerSecret = twitterConsumerSecret;
        this.twitterAccessToken = twitterAccessToken;
        this.twitterTokenSecret = twitterTokenSecret;
    }


    @Bean
    @Qualifier("twitterClient")
    public Twitter getTwitterClient() {
        ConfigurationBuilder twitterConfiguration = new ConfigurationBuilder();
        twitterConfiguration.setDebugEnabled(true)
            .setOAuthConsumerKey(twitterConsumerKey)
            .setOAuthConsumerSecret(twitterConsumerSecret)
            .setOAuthAccessToken(twitterAccessToken)
            .setOAuthAccessTokenSecret(twitterTokenSecret);
        return new TwitterFactory(twitterConfiguration.build()).getInstance();
    }

}
