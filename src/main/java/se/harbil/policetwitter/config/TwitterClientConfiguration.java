package se.harbil.policetwitter.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class TwitterClientConfiguration {

    @Value("${app.twitter.baseurl}")
    private String twitterBaseUrl;

    @Bean
    @Qualifier("twitterClientV2")
    public WebClient getTwitterClientV2() {
        return WebClient.builder()
            .baseUrl(twitterBaseUrl)
            .build();
    }
}
