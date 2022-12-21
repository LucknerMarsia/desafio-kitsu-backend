package com.project.kitsuapi.manga.repository;

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
@Entity(name = "manga")

public class MangaData implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "popularity")
    private int popularityRank;

    @Column(name = "rating")
    private int ratingRank;

    @Column(name = "favorites")
    private int favoritesCount;

    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;

}
