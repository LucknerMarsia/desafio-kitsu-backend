package com.project.kitsuapi.anime.response;

import com.project.kitsuapi.response.KitsuResponse;
import java.util.List;
import lombok.Data;



@Data
public class AnimeTrendingResponse {

  List<KitsuResponse.Data<AnimeResponse>> data;
}
