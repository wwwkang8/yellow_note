package com.hhplus.yellow.domain.keyword.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table
@Builder
public class Keyword {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long keywordId;

  @Column(name="keyword")
  private String keyword;

  @Column(name="count")
  private int count;

  @Column(name="createdAt")
  private LocalDateTime createdAt;

  @Column(name="updatedAt")
  private LocalDateTime updatedAt;


  public Keyword() {
  }

  public Keyword(long keywordId, String keyword, int count) {
    this.keywordId = keywordId;
    this.keyword = keyword;
    this.count = count;
  }

  public Keyword(long keywordId, String keyword, int count, LocalDateTime createdAt,
                 LocalDateTime updatedAt) {
    this.keywordId = keywordId;
    this.keyword = keyword;
    this.count = count;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }
}
