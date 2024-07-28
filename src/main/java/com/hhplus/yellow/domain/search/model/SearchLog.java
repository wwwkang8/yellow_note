package com.hhplus.yellow.domain.search.model;

import java.time.LocalDateTime;

import com.hhplus.yellow.api.controller.dto.response.SearchResponse;
import com.hhplus.yellow.domain.search.dto.SearchLogDto;
import com.hhplus.yellow.domain.user.model.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@Entity
@Table
public class SearchLog {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long searchLogId;

  @Column(name="keyword")
  private String keyword;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @Column(name = "search_timestamp")
  private LocalDateTime searchTimeStamp;

  @Column(name = "search_location")
  private String searchLocation;

  @Column(name = "api_host")
  private String apiHost;

  @Column(name = "response_status")
  private ResponseStatus responseStatus;

  @Lob
  @Column(name = "response_data", columnDefinition = "TEXT")
  private String responseData;

  @Column(name = "created_at")
  private LocalDateTime createdAt;

  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  public SearchLog() {
  }

  public SearchLog(long searchLogId, String keyword, User user,
                   LocalDateTime searchTimeStamp, String searchLocation, String apiHost,
                   ResponseStatus responseStatus, String responseData,
                   LocalDateTime createdAt, LocalDateTime updatedAt) {
    this.searchLogId = searchLogId;
    this.keyword = keyword;
    this.user = user;
    this.searchTimeStamp = searchTimeStamp;
    this.searchLocation = searchLocation;
    this.apiHost = apiHost;
    this.responseStatus = responseStatus;
    this.responseData = responseData;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }
}
