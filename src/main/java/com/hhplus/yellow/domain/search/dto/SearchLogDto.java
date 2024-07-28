package com.hhplus.yellow.domain.search.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.hhplus.yellow.domain.search.model.ResponseStatus;
import com.hhplus.yellow.domain.search.model.SearchLog;
import com.hhplus.yellow.domain.user.model.User;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@AllArgsConstructor
@Builder
@Slf4j
public class SearchLogDto {

  // 입력값
  private String searchWord;
  private String sortType;
  private String apiHost;
  private User user;

  // 출력값
  List<RestaurantDto> restaurantDtos;
  private String responseBody;

  public SearchLogDto() {
  }

  public static SearchLog toEntity(SearchLogDto searchLogDto) {

    SearchLog searchLog = SearchLog.builder()
                                   .keyword(searchLogDto.getSearchWord())
                                   .user(searchLogDto.getUser())
                                   .searchTimeStamp(LocalDateTime.now())
                                   .searchLocation(null)
                                   .apiHost(searchLogDto.getApiHost())
                                   .responseStatus(null)
                                   .responseData(searchLogDto.getResponseBody())
                                   .build();

    return searchLog;
  }
}
