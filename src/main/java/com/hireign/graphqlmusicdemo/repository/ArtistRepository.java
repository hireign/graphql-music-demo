package com.hireign.graphqlmusicdemo.repository;

import com.hireign.graphqlmusicdemo.pojo.Artist;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository extends MongoRepository<Artist, String> {
    Artist findByName(String name);
}
