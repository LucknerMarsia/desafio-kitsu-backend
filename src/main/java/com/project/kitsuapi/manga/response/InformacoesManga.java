package com.project.kitsuapi.manga.response;

import com.project.kitsuapi.anime.response.Thumbnail;
import com.project.kitsuapi.dto.Titles;
import java.util.Date;
import lombok.Data;




@Data
public class InformacoesManga {

  private Date createdAt;
  private Date updatedAt;
  private Titles titles;
  private String canonicalTitle;
  private int volumeNumber;
  private int number;
  private String synopsis;
  private String published;
  private String length;
  private Thumbnail thumbnail;
}