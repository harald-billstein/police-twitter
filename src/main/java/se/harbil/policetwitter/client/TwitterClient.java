package se.harbil.policetwitter.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import se.harbil.policetwitter.model.TwitterRequest;
import se.harbil.policetwitter.service.TwitterOauthHeaderGenerator;

@Slf4j
@Component
@RequiredArgsConstructor
public class TwitterClient {

    @Qualifier("twitterClientV2")
    private final WebClient twitterClientV2;
    private final ObjectMapper objectMapper;
    private final TwitterOauthHeaderGenerator twitterOauthHeaderGenerator;

    public String sendTweetV2(final TwitterRequest twitterRequest) throws JsonProcessingException {
        return twitterClientV2.post()
            .headers(httpHeaders -> {
                httpHeaders.set("Content-Type", "application/json");
                httpHeaders.set("Authorization", twitterOauthHeaderGenerator.generateHeader(Collections.emptyMap()));
            })
            .bodyValue(objectMapper.writeValueAsString(twitterRequest))
            .retrieve()
            .bodyToMono(String.class)
            .block();
    }

}
