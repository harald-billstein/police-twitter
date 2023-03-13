package se.harbil.policetwitter.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import se.harbil.policetwitter.model.UnwantedContent;

public interface UnwantedContentRepository extends MongoRepository<UnwantedContent, String> {

}
