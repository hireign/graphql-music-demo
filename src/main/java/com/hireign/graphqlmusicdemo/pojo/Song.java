package com.hireign.graphqlmusicdemo.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Song {

    @Id
    public String id;
    public String title;
    public String artistName;
    public String albumName;
}
