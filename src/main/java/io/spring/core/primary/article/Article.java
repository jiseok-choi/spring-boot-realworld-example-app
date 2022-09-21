package io.spring.core.primary.article;

import io.spring.Util;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class Article {
  private String userId;
  private String id;
  private String slug;
  private String title;
  private String description;
  private String body;
  private List<Tag> tags;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  public Article(
      String title, String description, String body, List<String> tagList, String userId) {
    this(title, description, body, tagList, userId, LocalDateTime.now());
  }

  public Article(
      String title,
      String description,
      String body,
      List<String> tagList,
      String userId,
      LocalDateTime createdAt) {
    this.id = UUID.randomUUID().toString();
    this.slug = toSlug(title);
    this.title = title;
    this.description = description;
    this.body = body;
    this.tags = new HashSet<>(tagList).stream().map(Tag::new).collect(toList());
    this.userId = userId;
    this.createdAt = createdAt;
    this.updatedAt = createdAt;
  }

  public void update(String title, String description, String body) {
    if (!Util.isEmpty(title)) {
      this.title = title;
      this.slug = toSlug(title);
      this.updatedAt = LocalDateTime.now();
    }
    if (!Util.isEmpty(description)) {
      this.description = description;
      this.updatedAt = LocalDateTime.now();
    }
    if (!Util.isEmpty(body)) {
      this.body = body;
      this.updatedAt = LocalDateTime.now();
    }
  }

  public static String toSlug(String title) {
    return title.toLowerCase().replaceAll("[\\&|[\\uFE30-\\uFFA0]|\\’|\\”|\\s\\?\\,\\.]+", "-");
  }
}
