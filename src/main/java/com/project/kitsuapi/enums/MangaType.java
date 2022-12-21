package com.project.kitsuapi.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;



public enum MangaType {
  doujin,
  manga,
  manhua,
  manhwa,
  novel,
  oel,
  oneshot;

  private final String key;

  MangaType() {
    this.key = this.name();
  }

  MangaType(String jsonKey) {
    this.key = jsonKey;
  }

  @JsonCreator
  public static MangaType fromString(String key) {
    if (key == null) {
      return null;
    }
    return MangaType.valueOf(key.toLowerCase());
  }

  @JsonValue
  public String getKey() {
    return this.key;
  }
}