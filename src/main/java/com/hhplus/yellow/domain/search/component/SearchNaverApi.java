package com.hhplus.yellow.domain.search.component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hhplus.yellow.domain.search.dto.RestaurantDto;
import com.hhplus.yellow.domain.search.dto.SearchLogDto;
import jdk.jfr.Registered;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class SearchNaverApi {

  @Value("${naver.clientId}")
  String clientId;

  @Value("${naver.clientSecret}")
  String clientSecret;

  private long display = 10;

  public SearchLogDto searchRestaurant(String searchWord, String sortType) {


    // 1. 파라메터 입력
    String text = null;
    try {
      text = URLEncoder.encode(searchWord, "UTF-8");
    } catch (UnsupportedEncodingException e) {
      throw new RuntimeException("검색어 인코딩 실패",e);
    }

    String apiURL = "https://openapi.naver.com/v1/search/local?query=" + text + "&display=" + display + "&sort=" + sortType;    // JSON 결과

    // 2. 레스토랑 검색 API 호출
    Map<String, String> requestHeaders = new HashMap<>();
    requestHeaders.put("X-Naver-Client-Id", clientId);
    requestHeaders.put("X-Naver-Client-Secret", clientSecret);
    String responseBody = get(apiURL,requestHeaders);

    log.info(responseBody);

    // 3. 리턴 받고
    SearchLogDto searchLogDto = jsonToDto(responseBody);
    searchLogDto.setApiHost("Naver");
    searchLogDto.setSearchWord(searchWord);
    searchLogDto.setSortType(sortType);
    searchLogDto.setResponseBody(responseBody);

    return searchLogDto;
  }

  private static String get(String apiUrl, Map<String, String> requestHeaders){
    HttpURLConnection con = connect(apiUrl);
    try {
      con.setRequestMethod("GET");
      for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
        con.setRequestProperty(header.getKey(), header.getValue());
      }


      int responseCode = con.getResponseCode();
      if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
        return readBody(con.getInputStream());
      } else { // 오류 발생
        return readBody(con.getErrorStream());
      }
    } catch (IOException e) {
      throw new RuntimeException("API 요청과 응답 실패", e);
    } finally {
      con.disconnect();
    }
  }

  private static HttpURLConnection connect(String apiUrl){
    try {
      URL url = new URL(apiUrl);
      return (HttpURLConnection)url.openConnection();
    } catch (MalformedURLException e) {
      throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
    } catch (IOException e) {
      throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
    }
  }


  private static String readBody(InputStream body){
    InputStreamReader streamReader = new InputStreamReader(body);


    try (BufferedReader lineReader = new BufferedReader(streamReader)) {
      StringBuilder responseBody = new StringBuilder();


      String line;
      while ((line = lineReader.readLine()) != null) {
        responseBody.append(line);
      }


      return responseBody.toString();
    } catch (IOException e) {
      throw new RuntimeException("API 응답을 읽는 데 실패했습니다.", e);
    }
  }

  public SearchLogDto jsonToDto(String responseBody) {

    SearchLogDto searchLogDto = new SearchLogDto();
    List<RestaurantDto> restaurantList = new ArrayList<>();

    ObjectMapper mapper = new ObjectMapper();
    try {
      JsonNode rootNode = mapper.readTree(responseBody);
      JsonNode itemsNode = rootNode.path("items");


      if (itemsNode.isArray()) {
        for (JsonNode itemNode : itemsNode) {
          RestaurantDto restaurantDto = RestaurantDto.builder()
              .title(itemNode.path("title").asText())
              .link(itemNode.path("link").asText())
              .category(itemNode.path("category").asText())
              .description(itemNode.path("description").asText())
              .telephone(itemNode.path("telephone").asText())
              .address(itemNode.path("address").asText())
              .roadAddress(itemNode.path("roadAddress").asText())
              .mapx(itemNode.path("mapx").asText())
              .mapy(itemNode.path("mapy").asText())
              .build();

          restaurantList.add(restaurantDto);
        }
      }

      searchLogDto.setRestaurantDtos(restaurantList);

    } catch (Exception e) {
      e.printStackTrace();
    }

    return searchLogDto;
  }

}
