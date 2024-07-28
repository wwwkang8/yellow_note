package com.hhplus.yellow.api.controller.dto.response;

import com.hhplus.yellow.domain.search.dto.SearchLogDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class SearchResponse {

  private SearchLogDto searchLogDto;

  public SearchResponse() {
  }

  public static SearchResponse toResponse(SearchLogDto searchLogDto) {

    return SearchResponse.builder()
                          .searchLogDto(searchLogDto)
                          .build();

  }
}
