package com.project.kitsuapi.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Type {
  ONA, OVA, TV,
  MOVIE,
  MUSIC("music"),
  SPECIAL("special");

  private final String key;

  Type() {
    this.key = this.name();
  }

  Type(String jsonKey) {
    this.key = jsonKey;
  }

  @JsonCreator
  public static Type fromString(String key) {
    if (key == null) {
      return null;
    }
    return Type.valueOf(key.toUpperCase());
  }

  @JsonValue
  public String getKey() {
    return this.key;
  }
}