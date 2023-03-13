package se.harbil.policetwitter.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.data.annotation.Id;

@Getter
@Builder
@EqualsAndHashCode(exclude="id")
public class WantedRegions {

    @Id
    private String id;
    private String region;
}
