package se.harbil.policetwitter.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import se.harbil.policetwitter.model.PoliceEventKafkaModel;

class TwitterServiceTest {
    String testText = "1234567890Försök till tillgrepp av fortskaffningsmedel. Nio förare får ordningsbot för hastighetsöverträdelse. En av dessa får sitt körkort omhändertaget efter att ha kört 117 km/h på en 80-sträcka. Trafikkontroller är en del i polisens arbete för att förhindra och förebygga trafikolyckor.";

//    @Test
//    void createMessageNotLongerThen280Char() {
//        TwitterService twitterService = new TwitterService(null, null, null);
//        System.out.println(testText.length());
//
//        String messageNotLongerThen280Char = twitterService.createMessageNotLongerThen280Char(
//            PoliceEventKafkaModel.builder()
//                .extendedInfo(testText)
//                .build(), "#örebro #mord");
//
//        System.out.println(messageNotLongerThen280Char.length());
//        System.out.println(messageNotLongerThen280Char);
//
//        Assertions.assertTrue(messageNotLongerThen280Char.length() <= 280);
//    }
}