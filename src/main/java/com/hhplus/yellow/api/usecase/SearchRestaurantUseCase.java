package com.hhplus.yellow.api.usecase;

import com.hhplus.yellow.api.controller.dto.response.SearchResponse;
import com.hhplus.yellow.domain.search.component.SearchManager;
import com.hhplus.yellow.domain.search.dto.SearchLogDto;
import com.hhplus.yellow.domain.search.model.SearchLog;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class SearchRestaurantUseCase {

  private final SearchManager searchManager;


  public SearchResponse searchRestaurant(String searchWord, String sortType) {

    SearchLogDto searchLogDto = searchManager.searchRestaurant(searchWord, sortType);

    SearchResponse response = SearchResponse.toResponse(searchLogDto);

    return response;
  }

}
