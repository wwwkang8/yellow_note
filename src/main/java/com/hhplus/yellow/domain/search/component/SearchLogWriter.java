package com.hhplus.yellow.domain.search.component;


import com.hhplus.yellow.domain.search.dto.SearchLogDto;
import com.hhplus.yellow.domain.search.model.SearchLog;
import com.hhplus.yellow.domain.search.repository.SearchLogCoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class SearchLogWriter {

  private final SearchLogCoreRepository searchLogCoreRepository;

  public SearchLog save(SearchLogDto searchLogDto) {

    SearchLog searchLog = SearchLogDto.toEntity(searchLogDto);

    log.info("SearchLogWriter save 함수 : " + searchLog.getKeyword());
    log.info("SearchLogWriter save 함수 : " + searchLog.toString());

    return searchLogCoreRepository.save(searchLog);
  }
}
