package se.harbil.policetwitter.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import se.harbil.policetwitter.model.WantedRegions;

public interface WantedRegionsRepository extends MongoRepository<WantedRegions, String> {

}
