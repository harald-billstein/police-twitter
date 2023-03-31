package se.harbil.policetwitter.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.harbil.policetwitter.model.PoliceEventKafkaModel;
import se.harbil.policetwitter.service.HashTagCreator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MessageComposerTest {

    MessageComposer messageComposer;
    HashTagCreator hashTagCreator;

    @BeforeEach
    void setUp() {
        hashTagCreator = new HashTagCreator();
        messageComposer = new MessageComposer(hashTagCreator);
    }

    @Test
    void testMessageMaxSizeShouldNotCutMessageIsExact280Char() {
        PoliceEventKafkaModel event = eventContaining280Char();

        String messageNotLongerThen280Char = messageComposer.createMessageNotLongerThen280Char(event);
        System.out.println(messageNotLongerThen280Char);

        assertEquals(280, messageNotLongerThen280Char.length());
        assertFalse(messageNotLongerThen280Char.contains("..."));
        assertEquals("Hund letade i snön en längre stund och hittade en mössa. Tyvärr hittades ej människorna, men mössan kom till användning för en skolklass med en elev som glömt sin mössa hemma, fröken var mycket tacksam och tyckte om färgen mössan! #Stockholm #fjällräddning https://fixLenght23.com", messageNotLongerThen280Char);
    }

    @Test
    void testMessageToLongShouldCutMessage() {
        PoliceEventKafkaModel event = eventContaining281Char();

        String messageNotLongerThen280Char = messageComposer.createMessageNotLongerThen280Char(event);

        assertEquals(280, messageNotLongerThen280Char.length());
        assertTrue(messageNotLongerThen280Char.contains("..."));
        assertEquals("Hund letade i snön en längre stund och hittade en mössa. Tyvärr hittades ej människorna, men mössan kom till användning för en skolklass med en elev som glömt sin mössa hemma, fröken var mycket tacksam och tyckte om färgen möss... #Stockholm #fjällräddning https://fixLenght23.com", messageNotLongerThen280Char);
    }

    @Test
    void testMessageLessThen280Chars() {
        PoliceEventKafkaModel event = eventContaining279Char();

        String messageNotLongerThen280Char = messageComposer.createMessageNotLongerThen280Char(event);

        assertTrue(messageNotLongerThen280Char.length() < 280);
        assertFalse(messageNotLongerThen280Char.contains("..."));
        assertEquals("Hund letade i snön en längre stund och hittade en mössa. Tyvärr hittades ej människorna, men mössan kom till användning för en skolklass med en elev som glömt sin mössa hemma, fröken var mycket tacksam och tyckte om färgen mössan #Stockholm #fjällräddning https://fixLenght23.com", messageNotLongerThen280Char);
    }

    private static PoliceEventKafkaModel eventContaining280Char() {
        return PoliceEventKafkaModel.builder()
                .locationName("Stockholm")
                .summary("Hund letade i snön en längre stund och hittade en mössa. Tyvärr hittades ej människorna, men mössan kom till användning för en skolklass med en elev som glömt sin mössa hemma, fröken var mycket tacksam och tyckte om färgen mössan!")
                .url("https://fixLenght23.com")
                .type("fjällräddning")
                .build();
    }

    private static PoliceEventKafkaModel eventContaining281Char() {
        return PoliceEventKafkaModel.builder()
                .locationName("Stockholm")
                .summary("Hund letade i snön en längre stund och hittade en mössa. Tyvärr hittades ej människorna, men mössan kom till användning för en skolklass med en elev som glömt sin mössa hemma, fröken var mycket tacksam och tyckte om färgen mössan!!")
                .url("https://fixLenght23.com")
                .type("fjällräddning")
                .build();
    }

    private static PoliceEventKafkaModel eventContaining279Char() {
        return PoliceEventKafkaModel.builder()
                .locationName("Stockholm")
                .summary("Hund letade i snön en längre stund och hittade en mössa. Tyvärr hittades ej människorna, men mössan kom till användning för en skolklass med en elev som glömt sin mössa hemma, fröken var mycket tacksam och tyckte om färgen mössan")
                .url("https://fixLenght23.com")
                .type("fjällräddning")
                .build();
    }

}