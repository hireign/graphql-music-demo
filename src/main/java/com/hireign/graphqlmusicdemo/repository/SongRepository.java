package com.hireign.graphqlmusicdemo.repository;

import com.hireign.graphqlmusicdemo.pojo.Song;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongRepository extends MongoRepository<Song, String> {
}
