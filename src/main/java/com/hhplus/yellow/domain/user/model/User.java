package com.hhplus.yellow.domain.user.model;

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
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long userId;


  @Column(name="token")
  private String token;

  @Column(name = "status")
  private ProcessStatus status;

  @Column(name = "created_at")
  private LocalDateTime createdAt;

  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  public User() {
  }

  public User(long userId, String token, ProcessStatus status) {
    this.userId = userId;
    this.token = token;
    this.status = status;
  }

  public User(long userId, String token, ProcessStatus status, LocalDateTime createdAt,
              LocalDateTime updatedAt) {
    this.userId = userId;
    this.token = token;
    this.status = status;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }
}
