package com.project.kitsuapi.anime.response;

import com.project.kitsuapi.dto.Image;
import com.project.kitsuapi.dto.Titles;
import com.project.kitsuapi.enums.StatusEnum;
import com.project.kitsuapi.enums.Type;
import java.io.Serializable;
import java.time.LocalDateTime;
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
public class AnimeResponse implements Serializable {

  private static final long serialVersionUID = 1L;
  private String id;
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
  private Type subtype;
  private StatusEnum status;
  private String tba;
  private Image posterImage;
  private Image coverImage;
  private int episodeCount;
  private int episodeLength;
  private String youtubeVideoId;
  private Type showType;
  private int seasonNumber;
  private int number;
  private int relativeNumber;
  private boolean nsfw;
}
