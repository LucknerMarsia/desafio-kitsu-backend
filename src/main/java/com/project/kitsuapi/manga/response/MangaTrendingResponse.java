package com.project.kitsuapi.manga.response;

import com.project.kitsuapi.response.KitsuResponse;
import java.util.List;
import lombok.Data;

@Data
public class MangaTrendingResponse {

  List<KitsuResponse.Data<Manga>> data;
}
