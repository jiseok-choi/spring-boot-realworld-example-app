package io.spring.core.secondary.history;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryArticleRepository extends JpaRepository<HistoryArticle, Integer> {
}
