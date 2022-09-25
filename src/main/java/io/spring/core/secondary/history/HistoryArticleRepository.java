package io.spring.core.secondary.history;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HistoryArticleRepository extends JpaRepository<HistoryArticle, Integer> {

    List<HistoryArticle> findByUserIdOrderByCreatedAtDesc(String userId, Pageable pageable);

    @Query("select count(e) from HistoryArticle e where e.userId = :userId")
    int countHistory(String userId);
}
