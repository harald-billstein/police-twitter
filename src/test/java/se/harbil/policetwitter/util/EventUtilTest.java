package se.harbil.policetwitter.util;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import se.harbil.policetwitter.model.UnwantedContent;
import se.harbil.policetwitter.model.WantedRegions;
import se.harbil.policetwitter.repository.UnwantedContentRepository;
import se.harbil.policetwitter.repository.WantedRegionsRepository;

//@SpringBootTest
//@ActiveProfiles("local")
class EventUtilTest {

    //@Autowired
    UnwantedContentRepository unwantedContentRepository;
    //@Autowired
    WantedRegionsRepository wantedRegionsRepository;

    //@Test
    void insertUnwantedWordsInDb() {
        unwantedContentRepository.save(UnwantedContent.builder().content("Sammanfattning").build());
    }

    //@Test
    void insertWantedRegionDb() {
        wantedRegionsRepository.save(WantedRegions.builder().region("Botkyrka").build());
        wantedRegionsRepository.save(WantedRegions.builder().region("Danderyd").build());
        wantedRegionsRepository.save(WantedRegions.builder().region("Ekerö").build());
        wantedRegionsRepository.save(WantedRegions.builder().region("Haninge").build());
        wantedRegionsRepository.save(WantedRegions.builder().region("Huddinge").build());
        wantedRegionsRepository.save(WantedRegions.builder().region("Järfälla").build());
        wantedRegionsRepository.save(WantedRegions.builder().region("Lidingö").build());
        wantedRegionsRepository.save(WantedRegions.builder().region("Nacka").build());
        wantedRegionsRepository.save(WantedRegions.builder().region("Norrtälje").build());
        wantedRegionsRepository.save(WantedRegions.builder().region("Nykvarn").build());
        wantedRegionsRepository.save(WantedRegions.builder().region("Nynäshamn").build());
        wantedRegionsRepository.save(WantedRegions.builder().region("Salem").build());
        wantedRegionsRepository.save(WantedRegions.builder().region("Sigtuna").build());
        wantedRegionsRepository.save(WantedRegions.builder().region("Sollentuna").build());
        wantedRegionsRepository.save(WantedRegions.builder().region("Solna").build());
        wantedRegionsRepository.save(WantedRegions.builder().region("Stockholm").build());
        wantedRegionsRepository.save(WantedRegions.builder().region("Stockholms-län").build());
        wantedRegionsRepository.save(WantedRegions.builder().region("Sundbyberg").build());
        wantedRegionsRepository.save(WantedRegions.builder().region("Södertälje").build());
        wantedRegionsRepository.save(WantedRegions.builder().region("Tyresö").build());
        wantedRegionsRepository.save(WantedRegions.builder().region("Täby").build());
        wantedRegionsRepository.save(WantedRegions.builder().region("Upplands-BroLän").build());
        wantedRegionsRepository.save(WantedRegions.builder().region("Upplands Väsby").build());
        wantedRegionsRepository.save(WantedRegions.builder().region("Vallentuna").build());
        wantedRegionsRepository.save(WantedRegions.builder().region("Vaxholm").build());
        wantedRegionsRepository.save(WantedRegions.builder().region("Värmdö").build());
        wantedRegionsRepository.save(WantedRegions.builder().region("Österåker").build());
    }


}