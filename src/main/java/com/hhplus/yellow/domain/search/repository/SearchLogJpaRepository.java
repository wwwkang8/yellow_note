package com.hhplus.yellow.domain.search.repository;

import java.util.Optional;

import com.hhplus.yellow.domain.search.model.SearchLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SearchLogJpaRepository extends JpaRepository<SearchLog, Long> {

  @Query("SELECT s from SearchLog s WHERE s.keyword = :searchWord")
  public SearchLog findSearchLogBySearchWord(String searchWord);

}
