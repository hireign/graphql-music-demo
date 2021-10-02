package com.hireign.graphqlmusicdemo.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
public class Artist {

    @Id
    public String id;
    public String name;
    public List<String> songs;
    public List<String> albums;
}
