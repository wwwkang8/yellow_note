package com.hhplus.yellow.domain.search.component;


import java.util.List;

import com.hhplus.yellow.domain.search.dto.SearchLogDto;
import com.hhplus.yellow.domain.search.model.SearchLog;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class SearchManagerImpl implements SearchManager{

  private final SearchNaverApi searchNaverApi;
  private final SearchLogWriter searchLogWriter;
  private final SearchLogReader searchLogReader;


  @Override
  public SearchLogDto searchRestaurant(String searchWord, String sortType) {


    // 1. Naver API로 맛집 호출
    SearchLogDto searchLogDto = searchNaverApi.searchRestaurant(searchWord, sortType);

    // 1-1. Naver API 장애 발생시 카카오 API로 호출(리턴 타입이 다른 것에 대해서도 고려하기)


    // 2. SearchLog에 저장
    log.info(String.valueOf(searchLogDto));
    SearchLog savedSearchLog = searchLogWriter.save(searchLogDto);


    // 3. 데이터 가공해서 Client에게 Return


    return searchLogDto;
  }
}

