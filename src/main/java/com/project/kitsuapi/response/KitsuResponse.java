package com.project.kitsuapi.response;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class KitsuResponse<T> {

  Data<T> data;

  public T getAttributes() {
    return data.getAttributes();
  }

  @Getter
  @ToString
  public final static class Data<T> {

    T attributes;
  }
}