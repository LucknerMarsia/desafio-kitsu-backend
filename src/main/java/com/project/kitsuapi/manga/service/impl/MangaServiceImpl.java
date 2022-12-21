package com.project.kitsuapi.manga.service.impl;

import com.project.kitsuapi.anime.repository.AnimeData;
import com.project.kitsuapi.anime.repository.AnimeDataRepository;
import com.project.kitsuapi.manga.repository.MangaData;
import com.project.kitsuapi.manga.repository.MangaDataRepository;
import com.project.kitsuapi.manga.response.InformacoesMangaResponse;
import com.project.kitsuapi.manga.response.Manga;
import com.project.kitsuapi.manga.response.MangaResponse;
import com.project.kitsuapi.manga.response.MangaTrendingResponse;
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
import java.util.stream.Collectors;


@Slf4j
@Service
public class MangaServiceImpl {

  private final RestTemplate restTemplate;

  @Autowired
  private MangaDataRepository mangaDataRepository;
  @Value("${kitsu-api.client.service-url}")
  String kitsuUrl;
  @Value("${kitsu-api.client.service-manga-endpoint}")
  String mangaEndPoint;
  @Value("${kitsu-api.client.service-manga-filter}")
  String mangaFilterEndPoint;
  private HttpEntity<String> HTTP_REQUEST_ENTITY;

  private MangaServiceImpl(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  public List<Manga> recuperarMangasMaisPopulares() {

    List<Manga> mangaPopularList = new ArrayList<>();

    try {
      HTTP_REQUEST_ENTITY = new HttpEntity<>(kitsuApiUtil.headers());
      var response = restTemplate
              .exchange(
                      kitsuUrl + mangaEndPoint,
                      HttpMethod.GET, HTTP_REQUEST_ENTITY,
                      MangaTrendingResponse.class)
              .getBody()
              .getData()
              .stream();

      var mangaResponseList =
              response.map(KitsuResponse.Data::getAttributes);

      mangaPopularList = mangaResponseList
              .sorted(Comparator.comparing(Manga::getPopularityRank)).collect(Collectors.toList());
    } catch (Exception ex) {
      log.error("Erro ao tentar recuperar mangas mais populares", ex);
      throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
    }
    mangaPopularList.forEach(anime -> {
      var mangaToSave = MangaData
              .builder()
              .popularityRank(anime.getPopularityRank())
              .ratingRank(anime.getRatingRank())
              .dataCriacao(LocalDateTime.now())
              .build();
      mangaDataRepository.save(mangaToSave);
    });
    return mangaPopularList;
  }

  public InformacoesMangaResponse recuperarInformacoesMangas(Integer id) {

    try {
      HTTP_REQUEST_ENTITY = new HttpEntity<>(kitsuApiUtil.headers());

      var path = String.format(kitsuUrl + mangaFilterEndPoint + "/" + id);

      return restTemplate
              .exchange(
                      path, HttpMethod.GET, HTTP_REQUEST_ENTITY,
                      InformacoesMangaResponse.class)
              .getBody();
    } catch (Exception ex) {
      log.error("Erro ao tentar recuperar informações de manga", ex);
      throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);

    }

  }

  public MangaResponse recuperarMangaPorFiltro(Integer id) {

    try {
      HTTP_REQUEST_ENTITY = new HttpEntity<>(kitsuApiUtil.headers());

      var path = String.format(kitsuUrl + mangaFilterEndPoint + "?filter[id]=" + id);

      return restTemplate
              .exchange(
                      path, HttpMethod.GET, HTTP_REQUEST_ENTITY,
                      MangaResponse.class)
              .getBody();
    } catch (Exception ex) {
      log.error("Erro ao tentar recuperar por manga filtro", ex);
      throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY);
    }
  }
}
