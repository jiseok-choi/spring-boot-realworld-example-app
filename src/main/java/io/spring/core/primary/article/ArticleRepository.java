package io.spring.core.primary.article;

import java.util.Optional;

public interface ArticleRepository {

  void save(Article article);

  Optional<Article> findById(String id);

  Optional<Article> findBySlug(String slug);

  void remove(Article article);
}
