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
import java.util.HashMap;
import java.util.Map;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class SearchManagerImpl implements SearchManager{

  @Value("${naver.clientId}")
  String clientId;

  @Value("${naver.clientSecret}")
  String clientSecret;

  private long display = 10;
  private String sort = "random";

  @Override
  public String searchRestaurant(String searchWord) {


    // 1. 파라메터 입력
    String text = null;
    try {
      text = URLEncoder.encode(searchWord, "UTF-8");
    } catch (UnsupportedEncodingException e) {
      throw new RuntimeException("검색어 인코딩 실패",e);
    }

    String apiURL = "https://openapi.naver.com/v1/search/local?query=" + text + "&display=" + display + "&sort=" + sort;    // JSON 결과

    // 2. 레스토랑 검색 API 호출
    Map<String, String> requestHeaders = new HashMap<>();
    requestHeaders.put("X-Naver-Client-Id", clientId);
    requestHeaders.put("X-Naver-Client-Secret", clientSecret);
    String responseBody = get(apiURL,requestHeaders);


    System.out.println(responseBody);

    // 3. 리턴 받고

    return null;
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
}

