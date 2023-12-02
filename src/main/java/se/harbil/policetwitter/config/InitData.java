package se.harbil.policetwitter.config;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import se.harbil.policetwitter.model.UnwantedContent;
import se.harbil.policetwitter.model.WantedRegions;
import se.harbil.policetwitter.repository.UnwantedContentRepository;
import se.harbil.policetwitter.repository.WantedRegionsRepository;

@Component
@RequiredArgsConstructor
public class InitData {

    private final UnwantedContentRepository unwantedContentRepository;
    private final WantedRegionsRepository wantedRegionsRepository;

    private List<WantedRegions> loadWantedRegions() {
        List<WantedRegions> wantedRegions = new ArrayList<>();

        wantedRegions.add(WantedRegions.builder().region("Botkyrka").build());
        wantedRegions.add(WantedRegions.builder().region("Danderyd").build());
        wantedRegions.add(WantedRegions.builder().region("Ekerö").build());
        wantedRegions.add(WantedRegions.builder().region("Haninge").build());
        wantedRegions.add(WantedRegions.builder().region("Huddinge").build());
        wantedRegions.add(WantedRegions.builder().region("Järfälla").build());
        wantedRegions.add(WantedRegions.builder().region("Lidingö").build());
        wantedRegions.add(WantedRegions.builder().region("Nacka").build());
        wantedRegions.add(WantedRegions.builder().region("Norrtälje").build());
        wantedRegions.add(WantedRegions.builder().region("Nykvarn").build());
        wantedRegions.add(WantedRegions.builder().region("Nynäshamn").build());
        wantedRegions.add(WantedRegions.builder().region("Salem").build());
        wantedRegions.add(WantedRegions.builder().region("Sigtuna").build());
        wantedRegions.add(WantedRegions.builder().region("Sollentuna").build());
        wantedRegions.add(WantedRegions.builder().region("Solna").build());
        wantedRegions.add(WantedRegions.builder().region("Stockholm").build());
        wantedRegions.add(WantedRegions.builder().region("Stockholms-län").build());
        wantedRegions.add(WantedRegions.builder().region("Sundbyberg").build());
        wantedRegions.add(WantedRegions.builder().region("Södertälje").build());
        wantedRegions.add(WantedRegions.builder().region("Tyresö").build());
        wantedRegions.add(WantedRegions.builder().region("Täby").build());
        wantedRegions.add(WantedRegions.builder().region("Upplands-BroLän").build());
        wantedRegions.add(WantedRegions.builder().region("Upplands Väsby").build());
        wantedRegions.add(WantedRegions.builder().region("Vallentuna").build());
        wantedRegions.add(WantedRegions.builder().region("Vaxholm").build());
        wantedRegions.add(WantedRegions.builder().region("Värmdö").build());
        wantedRegions.add(WantedRegions.builder().region("Österåker").build());

        return wantedRegions;
    }

    private List<UnwantedContent> loadUnwantedContent() {
        List<UnwantedContent> unwantedContent = new ArrayList<>();
        unwantedContent.add(UnwantedContent.builder().content("Sammanfattning").build());
        return unwantedContent;
    }

    public void loadDefaultDataToMongo() {
        List<WantedRegions> savedWantedRegions = wantedRegionsRepository.findAll();
        List<WantedRegions> wantedRegions = loadWantedRegions();

        List<WantedRegions> wantedRegionsNotFoundInDB = wantedRegions.stream()
            .filter(wantedRegion -> !savedWantedRegions.contains(wantedRegion)).toList();

        if (!wantedRegionsNotFoundInDB.isEmpty()) {
            wantedRegionsRepository.saveAll(wantedRegionsNotFoundInDB);
        }

        List<UnwantedContent> savedUnwantedContents = unwantedContentRepository.findAll();
        List<UnwantedContent> unwantedContents = loadUnwantedContent();

        List<UnwantedContent> savedUnwantedContentsNotFoundInDB = unwantedContents.stream()
            .filter(unwantedContent -> !savedUnwantedContents.contains(unwantedContent)).toList();

        if (!savedUnwantedContentsNotFoundInDB.isEmpty()) {
            unwantedContentRepository.saveAll(savedUnwantedContentsNotFoundInDB);
        }
    }
}
