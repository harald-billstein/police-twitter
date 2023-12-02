package se.harbil.policetwitter.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import se.harbil.policetwitter.model.PoliceEventKafkaModel;
import se.harbil.policetwitter.repository.UnwantedContentRepository;
import se.harbil.policetwitter.repository.WantedRegionsRepository;

@Service
@RequiredArgsConstructor
public class ContentFilter {

    private final UnwantedContentRepository unwantedContentRepository;
    private final WantedRegionsRepository wantedRegionsRepository;

    public boolean hasUnwantedContent(final PoliceEventKafkaModel event) {
        return !unwantedContentRepository.findAll().stream()
            .filter(unwantedContent -> event.getSummary().toLowerCase()
                .contains(unwantedContent.getContent().toLowerCase()))
            .toList()
            .isEmpty() || unWantedRegion(event);
    }

    private boolean unWantedRegion(final PoliceEventKafkaModel event) {
        return wantedRegionsRepository.findAll().stream()
            .filter(wantedRegions -> event.getLocationName().toLowerCase()
                .contains(wantedRegions.getRegion().toLowerCase()))
            .toList()
            .isEmpty();
    }
}
