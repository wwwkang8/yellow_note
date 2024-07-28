package com.hhplus.yellow.domain.search.infra;

import com.hhplus.yellow.domain.search.model.SearchLog;
import com.hhplus.yellow.domain.search.repository.SearchLogCoreRepository;
import com.hhplus.yellow.domain.search.repository.SearchLogJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
@RequiredArgsConstructor
public class SearchLogCoreRepositoryImpl implements SearchLogCoreRepository {

  private final SearchLogJpaRepository searchLogJpaRepository;


  @Override
  public SearchLog save(SearchLog searchLog) {
    return searchLogJpaRepository.save(searchLog);
  }

  @Override
  public SearchLog findSearchLogBySearchWord(String searchWord) {
    return searchLogJpaRepository.findSearchLogBySearchWord(searchWord);
  }
}
