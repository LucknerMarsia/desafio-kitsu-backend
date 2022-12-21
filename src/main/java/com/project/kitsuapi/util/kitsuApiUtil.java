package com.project.kitsuapi.util;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class kitsuApiUtil {

  public static HttpHeaders headers() {
    final HttpHeaders headers = new HttpHeaders();
    headers.set("Accept", "application/vnd.api+json");
    return headers;
  }

  public static String montarUrl(String kitsuUrl, String animeEndPoint, Integer id, String type, String title) {
    var base = String.format(kitsuUrl + animeEndPoint);

    if (Optional.ofNullable(id).isPresent() ||
            Optional.ofNullable(type).isPresent() ||
            Optional.ofNullable(title).isPresent()) {
      base = String.format(base + "?filter");
    }

    if (Optional.ofNullable(id).isPresent()) {
      base = String.format(base  + "[id]=" + id);
    }

    if (Optional.ofNullable(type).isPresent()) {
      base = String.format(base + "[type]=" + type);
    }

    if (Optional.ofNullable(title).isPresent()) {
      base = String.format(base + "[text]=" + title);
    }

    return base;
  }
}
