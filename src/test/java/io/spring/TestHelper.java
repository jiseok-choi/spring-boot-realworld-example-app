package io.spring;

import io.spring.application.data.ArticleData;
import io.spring.application.data.ProfileData;
import io.spring.core.primary.article.Article;
import io.spring.core.primary.user.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

public class TestHelper {
  public static ArticleData articleDataFixture(String seed, User user) {
    LocalDateTime now = LocalDateTime.now();
    return new ArticleData(
        seed + "id",
        "title-" + seed,
        "title " + seed,
        "desc " + seed,
        "body " + seed,
        false,
        0,
        now,
        now,
        new ArrayList<>(),
        new ProfileData(user.getId(), user.getUsername(), user.getBio(), user.getImage(), false));
  }

  public static ArticleData getArticleDataFromArticleAndUser(Article article, User user) {
    return new ArticleData(
        article.getId(),
        article.getSlug(),
        article.getTitle(),
        article.getDescription(),
        article.getBody(),
        false,
        0,
        article.getCreatedAt(),
        article.getUpdatedAt(),
        Arrays.asList("joda"),
        new ProfileData(user.getId(), user.getUsername(), user.getBio(), user.getImage(), false));
  }
}
