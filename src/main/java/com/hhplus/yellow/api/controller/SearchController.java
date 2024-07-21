package com.hhplus.yellow.api.controller;

import com.hhplus.yellow.api.controller.dto.response.SearchResponse;
import com.hhplus.yellow.api.usecase.SearchRestaurantUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/yellow")
@RequiredArgsConstructor
@Slf4j
public class SearchController {

  private final SearchRestaurantUseCase searchRestaurantUseCase;

  @GetMapping("/search")
  public ResponseEntity<SearchResponse> searchRestaurant(@RequestParam String searchWord) {

    searchRestaurantUseCase.searchRestaurant(searchWord);

    return ResponseEntity.ok().body(null);
  }

}
