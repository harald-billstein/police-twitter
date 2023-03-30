package se.harbil.policetwitter.util;

import org.springframework.stereotype.Service;
import se.harbil.policetwitter.model.PoliceEventKafkaModel;
import se.harbil.policetwitter.service.HashTagCreator;

@Service
public class MessageComposer {

    private final int maxMessageSize = 280;
    private final HashTagCreator hashTagCreator;

    public MessageComposer(HashTagCreator hashTagCreator) {
        this.hashTagCreator = hashTagCreator;
    }

    private String createMessage(PoliceEventKafkaModel event, String hashTag, int messageSize) {
        if (messageSize > maxMessageSize) {
            int i = 3 + event.getSummary().length() + hashTag.length() + event.getUrl().length() - maxMessageSize;
            return event.getSummary().substring(0, (event.getSummary().length() - i)) + "..." + event.getUrl() + hashTag;
        } else {
            return event.getSummary() + " " + event.getUrl() + " " + hashTag;
        }
    }

    public String createMessageNotLongerThen280Char(PoliceEventKafkaModel event) {
        String hashTag = hashTagCreator.getHashTag(event);
        int urlSize = 23;
        int spaceInMessage = 2;
        int messageSize = event.getSummary().length() + hashTag.length() + urlSize+ spaceInMessage;
        return createMessage(event, hashTag, messageSize);
    }
}
