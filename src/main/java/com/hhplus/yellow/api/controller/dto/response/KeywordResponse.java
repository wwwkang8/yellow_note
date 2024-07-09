package com.hhplus.yellow.api.controller.dto.response;

import java.util.List;

public class KeywordResponse {

  List<String> keywordList;

  public KeywordResponse() {
  }

  public KeywordResponse(List<String> keywordList) {
    this.keywordList = keywordList;
  }
}
