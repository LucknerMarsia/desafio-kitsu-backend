package com.project.kitsuapi.manga.response;

import com.project.kitsuapi.dto.Image;
import com.project.kitsuapi.dto.Titles;
import com.project.kitsuapi.enums.MangaType;
import com.project.kitsuapi.enums.StatusEnum;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Manga implements Serializable {

  private static final long serialVersionUID = 1L;
  private String createdAt;
  private String updatedAt;
  private String slug;
  private String synopsis;
  private int coverImageTopOffset;
  private Titles titles;
  private String canonicalTitle;
  private List<String> abbreviatedTitles;
  private double averageRating;
  private Map<Integer, Integer> ratingFrequencies;
  private int userCount;
  private int favoritesCount;
  private String startDate;
  private String endDate;
  private int popularityRank;
  private int ratingRank;
  private String ageRating;
  private String ageRatingGuide;
  private MangaType subtype;
  private StatusEnum status;
  private String tba;
  private Image posterImage;
  private Image coverImage;
  private int chapterCount;
  private int volumeCount;
  private String serialization;
  private MangaType mangaType;
}
