package com.hhplus.yellow.domain.search.dto;

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
public class RestaurantDto {

  private String title;
  private String link;
  private String category;
  private String description;
  private String telephone;
  private String address;
  private String roadAddress;
  private String mapx;
  private String mapy;

  public RestaurantDto() {
  }
}
