package com.hhplus.yellow.api.controller;

import com.hhplus.yellow.api.controller.dto.response.KeywordResponse;
import com.hhplus.yellow.api.usecase.KeywordUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/yellow")
@RequiredArgsConstructor
@Slf4j
public class KeywordController {

  private final KeywordUseCase keywordUseCase;

  @GetMapping("/keyword")
  public ResponseEntity<KeywordResponse> getKeyword() {

    log.info("키워드 조회 API");

    return ResponseEntity.ok().body(null);
  }

}
