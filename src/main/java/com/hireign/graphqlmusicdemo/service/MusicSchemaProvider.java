package com.hireign.graphqlmusicdemo.service;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.hireign.graphqlmusicdemo.datafetcher.ArtistDataFetcher;
import com.hireign.graphqlmusicdemo.datafetcher.SongDataFetcher;
import graphql.GraphQL;
import graphql.scalars.ExtendedScalars;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URL;

import static graphql.schema.idl.TypeRuntimeWiring.newTypeWiring;

@Slf4j
@Service
public class MusicSchemaProvider {

    @Autowired
    SongDataFetcher songDataFetcher;

    @Autowired
    ArtistDataFetcher artistDataFetcher;

    private GraphQL graphQL;

    @Bean
    public GraphQL graphQL() {
        return graphQL;
    }

    @PostConstruct
    public void init() {
        try {
            URL schemaUrl = Resources.getResource("schema.graphql");
            String schema = Resources.toString(schemaUrl, Charsets.UTF_8);
            GraphQLSchema graphQLSchema = buildExecutableSchema(schema);
            this.graphQL = GraphQL.newGraphQL(graphQLSchema).build();
        } catch (IllegalArgumentException | IOException e) {
            log.error("error while parsing schema file", e);
        }
    }

    private GraphQLSchema buildExecutableSchema(String schema) {
        log.info("inside buildExecutableSchema()");
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schema);
        RuntimeWiring runtimeWiring = buildWiring();
        SchemaGenerator schemaGenerator = new SchemaGenerator();
        return schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring);
    }

    private RuntimeWiring buildWiring() {
        log.info("inside buildWiring()");
        return RuntimeWiring.newRuntimeWiring()
                .scalar(ExtendedScalars.GraphQLLong)
                .type(newTypeWiring("Query")
                        .dataFetcher("findAllSongs", songDataFetcher.findAllSongs())
                        .dataFetcher("findAllArtists", artistDataFetcher.findAllArtists()))
                .type(newTypeWiring("Song")
                        .dataFetcher("artist", songDataFetcher.findArtistByName()))
                .build();
    }
}
