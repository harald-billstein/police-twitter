package se.harbil.policetwitter.util;

import org.springframework.stereotype.Service;
import se.harbil.policetwitter.model.PoliceEventKafkaModel;

@Service
public class MessageComposer {

    private final int maxMessageSize = 280;

    private String createMessage(String message, String hashTag, int messageSize) {
        if (messageSize > maxMessageSize) {
            int i = 3 + message.length() + hashTag.length() - maxMessageSize;
            return message.substring(0, (message.length() - i)) + "..." + hashTag;
        } else {
            return message + hashTag;
        }
    }

    public String createMessageNotLongerThen280Char(PoliceEventKafkaModel event, String hashTag) {
        int messageSize;
       // if (null != event.getExtendedInfo()) {
       //     messageSize = event.getSummary().length() + hashTag.length();
       //     return createMessage(event.getExtendedInfo(), hashTag, messageSize);
       // } else {
            messageSize = event.getSummary().length() + hashTag.length();
            return createMessage(event.getSummary(), hashTag, messageSize);
        //}
    }
}
