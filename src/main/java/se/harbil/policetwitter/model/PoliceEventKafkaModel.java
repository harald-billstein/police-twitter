package se.harbil.policetwitter.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Builder
@ToString
@Data
public class PoliceEventKafkaModel {

    private String datetime;
    private Long id;
    private String locationGps;
    private String locationName;
    private String name;
    private String summary;
    private String type;
    private String url;
    private String extendedInfo;

}
