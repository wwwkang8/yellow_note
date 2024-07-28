package com.hhplus.yellow.domain.search.component;

import com.hhplus.yellow.domain.search.dto.SearchLogDto;
import com.hhplus.yellow.domain.search.model.SearchLog;

public interface SearchManager {

  public SearchLogDto searchRestaurant(String searchWord, String sortType);

}
