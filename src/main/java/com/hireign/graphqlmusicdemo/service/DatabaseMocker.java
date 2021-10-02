package com.hireign.graphqlmusicdemo.service;

import com.hireign.graphqlmusicdemo.pojo.Artist;
import com.hireign.graphqlmusicdemo.pojo.Song;
import com.hireign.graphqlmusicdemo.repository.ArtistRepository;
import com.hireign.graphqlmusicdemo.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DatabaseMocker implements CommandLineRunner {

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private ArtistRepository artistRepository;

    @Override
    public void run(String... args) throws Exception {

        // delete any existing data
        songRepository.deleteAll();
        artistRepository.deleteAll();

        // Save mock data for songs
        songRepository.save(new Song("1", "Lose Yourself", "Eminem", "8 Mile"));
        songRepository.save(new Song("2", "Gravity", "John Mayer", "Try!"));
        songRepository.save(new Song("3", "Anastasia", "Slash", "Apocaplytic Love"));
        // Save mock data for artists
        artistRepository.save(new Artist("1", "Eminem", Arrays.asList("Lose Yourself", "Stan", "Mockingbird"), Arrays.asList("8 Miles", "Recovery", "The Marshal Mathers LP")));
        artistRepository.save(new Artist("2", "John Mayer", Arrays.asList("Gravity", "Slow Dancing In a Burning Room", "Half of My Heart"), Arrays.asList("Continuum", "Sob Rock", "Try!")));
        artistRepository.save(new Artist("3", "Slash", Arrays.asList("November Rain", "Anastasia", "Paradise City"), Arrays.asList("Apocaplytic Love", "Use Your Illusion I", "Appetite for Destruction")));

        // retrieve mock data for songs
        System.out.println("\n\nSongs found with findAll():");
        System.out.println("-------------------------------");
        for (Song song : songRepository.findAll()) {
            System.out.println(song);
        }
        // retrieve mock data for artists
        System.out.println("\n\nArtists found with findAll():");
        System.out.println("-------------------------------");
        for (Artist artist : artistRepository.findAll()) {
            System.out.println(artist);
        }
    }
}
