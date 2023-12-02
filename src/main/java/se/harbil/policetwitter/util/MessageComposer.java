package se.harbil.policetwitter.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import se.harbil.policetwitter.model.PoliceEventKafkaModel;
import se.harbil.policetwitter.model.TwitterRequest;
import se.harbil.policetwitter.service.HashTagCreator;

@Service
@RequiredArgsConstructor
public class MessageComposer {

    private final int maxMessageSize = 280;
    private final HashTagCreator hashTagCreator;

    private String createMessage(final PoliceEventKafkaModel event, final String hashTag, final int messageSize) {
        if (messageSize > maxMessageSize) {
            int i = 3 + event.getSummary().length() + hashTag.length() + event.getUrl().length() - maxMessageSize;
            return event.getSummary().substring(0, (event.getSummary().length() - i)) + "..." + hashTag + event.getUrl();
        } else {
            return event.getSummary() + hashTag + event.getUrl();
        }
    }

    public TwitterRequest createMessageNotLongerThen280Char(final PoliceEventKafkaModel event) {
        String hashTag = hashTagCreator.getHashTag(event);
        int urlSize = 23;
        int messageSize = event.getSummary().length() + hashTag.length() + urlSize;
        return new TwitterRequest(createMessage(event, hashTag, messageSize));
    }
}
