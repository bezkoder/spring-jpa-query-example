package com.bezkoder.spring.jpa.query.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tutorials")
public class Tutorial {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  private String title;

  private String description;
  
  private int level;

  private boolean published;
  
  @Temporal(TemporalType.TIMESTAMP)
  private Date createdAt;

  public Tutorial() {

  }

  public Tutorial(String title, String description, int level, boolean published, Date createdAt) {
    this.title = title;
    this.description = description;
    this.level = level;
    this.published = published;
    this.createdAt = createdAt;
  }

  public long getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getLevel() {
    return level;
  }

  public void setLevel(int level) {
    this.level = level;
  }

  public boolean isPublished() {
    return published;
  }

  public void setPublished(boolean isPublished) {
    this.published = isPublished;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  @Override
  public String toString() {
    return "Tutorial [id=" + id + ", title=" + title + ", description=" + description + ", level=" + level + ", published=" + published + ", createdAt=" + createdAt + "]";
  }
}