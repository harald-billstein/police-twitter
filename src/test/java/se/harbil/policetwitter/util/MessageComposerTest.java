package se.harbil.policetwitter.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.harbil.policetwitter.model.PoliceEventKafkaModel;
import se.harbil.policetwitter.service.HashTagCreator;

class MessageComposerTest {

    MessageComposer messageComposer;
    HashTagCreator hashTagCreator;
    @BeforeEach
    void setUp() {
        hashTagCreator = new HashTagCreator();
        messageComposer = new MessageComposer(hashTagCreator);
    }
    @Test
    void testMessageMaxSizeShouldNotCutMessage() {
        PoliceEventKafkaModel event = PoliceEventKafkaModel.builder()
                .locationName("Stockholm")
                .summary("Hund letade i snön en längre stund och hittade en mössa. Tyvärr hittades ej människorna, men mössan kom till användning för en skolklass med en elev som glömt sin mössa hemma, fröken var mycket tacksam och tyckte om färgen mössan")
                .url("https://fixLenght23.com")
                .type("fjällräddning")
                .build();

        String messageNotLongerThen280Char = messageComposer.createMessageNotLongerThen280Char(event);

        Assertions.assertTrue(messageNotLongerThen280Char.length() == 280);
        Assertions.assertFalse(messageNotLongerThen280Char.contains("..."));
    }

    @Test
    void testMessageToLongShouldCutMessage() {
        PoliceEventKafkaModel event = PoliceEventKafkaModel.builder()
                .locationName("Stockholm")
                .summary("Hund letade i snön en längre stund och hittade en mössa. Tyvärr hittades ej människorna, men mössan kom till användning då en elev i en skolklass med en elev som glömt sin mössa hemma, fröken var mycket tacksam och tyckte om färgen mössan!!")
                .url("https://fixLenght23.com")
                .type("fjällräddning")
                .build();

        String messageNotLongerThen280Char = messageComposer.createMessageNotLongerThen280Char(event);

        Assertions.assertTrue(messageNotLongerThen280Char.length() == 280);
        Assertions.assertTrue(messageNotLongerThen280Char.contains("..."));
    }

    @Test
    void testMessageLessThen280Chars() {
        PoliceEventKafkaModel event = PoliceEventKafkaModel.builder()
                .locationName("Stockholm")
                .summary("Hund letade i snön en längre stund och hittade en mössa. Tyvärr hittades ej människorna, men mössan kom till användning för en skolklass med en elev som glömt sin mössa hemma, fröken var tacksam och tyckte om färgen mössan")
                .url("https://fixLenght23.com")
                .type("fjällräddning")
                .build();

        String messageNotLongerThen280Char = messageComposer.createMessageNotLongerThen280Char(event);

        System.out.println(messageNotLongerThen280Char.length());
        Assertions.assertTrue(messageNotLongerThen280Char.length() < 280);

        Assertions.assertFalse(messageNotLongerThen280Char.contains("..."));
    }

}