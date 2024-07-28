package com.hhplus.yellow.domain.search.repository;

import java.util.Optional;

import com.hhplus.yellow.domain.search.model.SearchLog;

public interface SearchLogCoreRepository {

  public SearchLog save(SearchLog searchLog);

  public SearchLog findSearchLogBySearchWord(String searchWord);


}
