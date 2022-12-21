package com.project.kitsuapi.manga.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MangaDataRepository extends JpaRepository<MangaData, Integer> {
}
