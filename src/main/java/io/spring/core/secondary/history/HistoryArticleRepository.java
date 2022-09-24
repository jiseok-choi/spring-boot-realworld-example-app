package io.spring.core.secondary.history;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HistoryArticleRepository extends JpaRepository<HistoryArticle, Integer> {

    @Query("select e from HistoryArticle e where e.userId = :userId")
    List<HistoryArticle> findByUserId(String userId);


}
