package se.harbil.policetwitter.util;

import org.springframework.stereotype.Service;
import se.harbil.policetwitter.model.PoliceEventKafkaModel;

@Service
public class MessageComposer {

    private String createMessage(PoliceEventKafkaModel event, String hashTag, int messageSize) {
        int maxMessageSize = 280;
        if (messageSize > maxMessageSize) {
            int i = 3 + event.getSummary().length() + hashTag.length() - maxMessageSize;
            return event.getSummary().substring(0, (event.getSummary().length() - i)) + "..." + hashTag;
        } else {
            return event.getSummary() + hashTag;
        }
    }

    public String createMessageNotLongerThen280Char(PoliceEventKafkaModel event, String hashTag) {
        int messageSize;
        //if (null != event.getExtendedInfo()) {
        //    messageSize = event.getExtendedInfo().length() + hashTag.length();
        //    return createMessage(event.getExtendedInfo(), hashTag, messageSize);
        //} else {
            messageSize = event.getDatetime().length() + event.getSummary().length() + hashTag.length();
            return createMessage(event, hashTag, messageSize);
        //}
    }
}
