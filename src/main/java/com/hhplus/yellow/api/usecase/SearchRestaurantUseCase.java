package com.hhplus.yellow.api.usecase;

import com.hhplus.yellow.domain.search.component.SearchManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class SearchRestaurantUseCase {

  private final SearchManager searchManager;


  public String searchRestaurant(String searchWord) {
    return searchManager.searchRestaurant(searchWord);
  }

}
