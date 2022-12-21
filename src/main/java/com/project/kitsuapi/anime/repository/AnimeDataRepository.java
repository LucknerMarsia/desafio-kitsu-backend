package com.project.kitsuapi.anime.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimeDataRepository extends JpaRepository<AnimeData, Integer> {

}
