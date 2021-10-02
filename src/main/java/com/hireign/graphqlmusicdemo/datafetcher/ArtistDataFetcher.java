package com.hireign.graphqlmusicdemo.datafetcher;

import com.hireign.graphqlmusicdemo.pojo.Artist;
import com.hireign.graphqlmusicdemo.repository.ArtistRepository;
import graphql.schema.DataFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ArtistDataFetcher {

    @Autowired
    private ArtistRepository artistRepository;

    public DataFetcher<List<Artist>> findAllArtists() {
        return dataFetchingEnvironment -> {
            return artistRepository.findAll();
        };
    }
}
