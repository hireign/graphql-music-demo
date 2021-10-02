package com.hireign.graphqlmusicdemo.datafetcher;

import com.hireign.graphqlmusicdemo.pojo.Song;
import com.hireign.graphqlmusicdemo.repository.SongRepository;
import graphql.schema.DataFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SongDataFetcher {

    @Autowired
    private SongRepository songRepository;

    public DataFetcher<List<Song>> findAllSongs() {
        return dataFetchingEnvironment -> {
            return songRepository.findAll();
        };
    }
}
