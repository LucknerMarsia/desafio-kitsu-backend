package com.project.kitsuapi.anime.response;

import com.project.kitsuapi.dto.Titles;
import java.util.Date;
import lombok.Data;

@Data
public class Episodios {

  private Date createdAt;
  private Date updatedAt;
  private Titles titles;
  private String canonicalTitle;
  private int seasonNumber;
  private int number;
  private int relativeNumber;
  private String synopsis;
  private String airdate;
  private String length;
  private Thumbnail thumbnail;
}