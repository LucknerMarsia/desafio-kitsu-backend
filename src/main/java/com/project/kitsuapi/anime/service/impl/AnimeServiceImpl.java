package com.project.kitsuapi.anime.service.impl;

import com.project.kitsuapi.anime.repository.AnimeData;
import com.project.kitsuapi.anime.repository.AnimeDataRepository;
import com.project.kitsuapi.anime.response.AnimeResponse;
import com.project.kitsuapi.anime.response.AnimeTrendingResponse;
import com.project.kitsuapi.anime.response.EpisodioResponse;
import com.project.kitsuapi.response.KitsuResponse;
import com.project.kitsuapi.util.kitsuApiUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
@Slf4j
@Service
public class AnimeServiceImpl {

  private final RestTemplate restTemplate;

  @Autowired
  private AnimeDataRepository animeDataRepository;

  @Value("${kitsu-api.client.service-url}")

  String kitsuUrl;
  @Value("${kitsu-api.client.service-anime-endpoint}")

  String animeEndPoint;
  @Value("${kitsu-api.client.service-anime-filter}")

  String animeFilterEndpoint;
  private HttpEntity<String> HTTP_REQUEST_ENTITY;

  private AnimeServiceImpl(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  public List<AnimeResponse> recuperarAnimesMaisPopulares() throws Exception {

    List<AnimeResponse> animePopularList = new ArrayList<>();

    try {
      HTTP_REQUEST_ENTITY = new HttpEntity<>(kitsuApiUtil.headers());

      var response =
              restTemplate
                      .exchange(
                              kitsuUrl + animeEndPoint,
                              HttpMethod.GET, HTTP_REQUEST_ENTITY,
                              AnimeTrendingResponse.class)
                      .getBody()
                      .getData()
                      .stream();

      var animeResponseList =
              response.map(KitsuResponse.Data::getAttributes);

      animePopularList = animeResponseList
              .sorted(Comparator.comparing(AnimeResponse::getPopularityRank))
              .collect(Collectors.toList());
    } catch (Exception ex) {
      log.error("Erro ao tentar recuperar animes populares", ex);
      throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
    }
    animePopularList.forEach(anime -> {
      var animeToSave = AnimeData
          .builder()
          .popularityRank(anime.getPopularityRank())
          .episodeCount(anime.getEpisodeCount())
          .ratingRank(anime.getRatingRank())
          .dataCriacao(LocalDateTime.now())
          .build();
      animeDataRepository.save(animeToSave);
    });
    return animePopularList;
  }

  public List<AnimeResponse> recuperarInformacoesAnimes
          (Integer id, String type, String title) throws Exception {

    List<AnimeResponse> informacoesAnimesList = new ArrayList<>();
    List<AnimeResponse> recuperarInformacoesAnime = new ArrayList<>();


    try {
      HTTP_REQUEST_ENTITY = new HttpEntity<>(kitsuApiUtil.headers());

      var path = kitsuApiUtil.montarUrl(kitsuUrl, animeEndPoint, id, type, title);

      var response = Objects.requireNonNull(restTemplate
                      .exchange(
                              path, HttpMethod.GET, HTTP_REQUEST_ENTITY,
                              AnimeTrendingResponse.class)
                      .getBody())
              .getData()
              .stream();

      recuperarInformacoesAnime  =
              response.map(KitsuResponse.Data::getAttributes)
                      .collect(Collectors.toList());

      recuperarInformacoesAnime .forEach(anime -> {
        var animeToSave = AnimeData
                .builder()
                .dataCriacao(LocalDateTime.now())
                .synopsis(anime.getSynopsis())
                .seasonNumber(anime.getSeasonNumber())
                .number(anime.getNumber())
                .number(anime.getNumber())
                .relativeNumber(anime.getRelativeNumber())
                .build();
        animeDataRepository.save(animeToSave);
      });

    } catch (Exception ex) {
      log.error("Erro ao tentar recuperar informações de animes", ex);
      throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
    }
    return recuperarInformacoesAnime;
  }
  public EpisodioResponse recuperarEpisodioAnimesPorId(Integer id) throws Exception {
    try{
      HTTP_REQUEST_ENTITY = new HttpEntity<>(kitsuApiUtil.headers());
      var url = kitsuUrl + animeFilterEndpoint + "?" + id;

      return restTemplate
              .exchange(
                      url, HttpMethod.GET, HTTP_REQUEST_ENTITY,
                      EpisodioResponse.class)
              .getBody();
    } catch (Exception ex) {
      log.error("Erro ao tentar recuperar Episodio por Id", ex);
      throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
    }
  }
}
