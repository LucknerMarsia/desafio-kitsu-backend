package com.project.kitsuapi.anime.rest;

import com.project.kitsuapi.anime.response.AnimeResponse;
import com.project.kitsuapi.anime.response.EpisodioResponse;
import com.project.kitsuapi.anime.service.impl.AnimeServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping("/anime")
@Api(tags = "Anime", description = "Endpoint para pesquisas de animes")
public class AnimeController {

  private final AnimeServiceImpl animeServiceImpl;


  @ApiParam(name = "Authorization", type = "header")
  @ApiOperation("Animes mais populares.")
  @GetMapping(value = "/maisPopulares", produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<List<AnimeResponse>> recuperarAnimesPopulares() throws Exception {
    return ResponseEntity.ok(animeServiceImpl.recuperarAnimesMaisPopulares());
  }

  @ApiParam(name = "Authorization", type = "header")
  @ApiOperation("Informacoes do anime.")
  @GetMapping(value = "/informacoes", produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<List<AnimeResponse>> recuperarInformacoesAnimes(
      @RequestParam(name = "id", required = false) Integer id,
      @RequestParam(name = "Type", required = false) String type,
      @RequestParam(name = "Titles", required = false) String title) throws Exception {
    return ResponseEntity.ok(animeServiceImpl.recuperarInformacoesAnimes(id, type, title));
  }

  @ApiParam(name = "Authorization", type = "header")
  @ApiOperation("Filtar animes.")
  @GetMapping(value = "/episodio", produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<EpisodioResponse> recuperarAnimesPorId(
      @RequestParam(name = "id", required = true) Integer id) throws Exception {
    return ResponseEntity.ok(animeServiceImpl.recuperarEpisodioAnimesPorId(id));
  }
}
