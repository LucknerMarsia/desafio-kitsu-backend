package com.project.kitsuapi.manga.rest;

import com.project.kitsuapi.manga.response.InformacoesMangaResponse;
import com.project.kitsuapi.manga.response.Manga;
import com.project.kitsuapi.manga.response.MangaResponse;
import com.project.kitsuapi.manga.service.impl.MangaServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping("/manga")
@Api(tags = "Manga", description = "Endpoint para tratamento dos Mangas")
public class MangaController {

  private final MangaServiceImpl mangaServiceImpl;

  @ApiParam(name = "Authorization", type = "header")
  @ApiOperation("Retorna os mangas mais populares.")
  @GetMapping(value = "/maisPopulares", produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<List<Manga>> recuperarMangasPopulares() {
    return ResponseEntity.ok(mangaServiceImpl.recuperarMangasMaisPopulares());
  }

  @ApiParam(name = "Authorization", type = "header")
  @ApiOperation("Retorna informacoes do manga.")
  @GetMapping(value = "/informacoes", produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<InformacoesMangaResponse> recuperarInformacoesManga(
      @RequestParam(name = "id", required = true) Integer id) {
    return ResponseEntity.ok(mangaServiceImpl.recuperarInformacoesMangas(id));
  }

  @ApiParam(name = "Authorization", type = "header")
  @ApiOperation("Retorna mangas por filtro.")
  @GetMapping(value = "/capitulo", produces = APPLICATION_JSON_VALUE)
  public ResponseEntity<MangaResponse> recuperarMangaPorFiltro(
      @RequestParam(name = "id", required = true) Integer id) {
    return ResponseEntity.ok(mangaServiceImpl.recuperarMangaPorFiltro(id));
  }
}
