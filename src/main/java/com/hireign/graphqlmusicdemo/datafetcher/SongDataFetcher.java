package com.hireign.graphqlmusicdemo.datafetcher;

import com.hireign.graphqlmusicdemo.pojo.Song;
import com.hireign.graphqlmusicdemo.repository.ArtistRepository;
import com.hireign.graphqlmusicdemo.repository.SongRepository;
import graphql.schema.DataFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
public class SongDataFetcher {

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private ArtistRepository artistRepository;

    public DataFetcher<List<Song>> findAllSongs() {
        return dataFetchingEnvironment -> songRepository.findAll();
    }

    public DataFetcher findArtistByName() {
        return dataFetchingEnvironment -> {
            Song song = dataFetchingEnvironment.getSource();
            String artistName = song.getArtistName();
            return artistRepository.findByName(artistName);
        };
    }
}
