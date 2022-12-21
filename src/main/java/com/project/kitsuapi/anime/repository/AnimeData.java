package com.project.kitsuapi.anime.repository;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "anime")
public class AnimeData implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "popularity")
    private int popularityRank;

    @Column(name = "rating")
    private int ratingRank;

    @Column(name = "episode")
    private int episodeCount;

    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;

    @Column(name = "synopsis")
    private String synopsis;

    @Column(name = "season_number")
    private int seasonNumber;

    @Column(name = "numero")
    private int number;

    @Column(name = "relative_number")
    private int relativeNumber;
}