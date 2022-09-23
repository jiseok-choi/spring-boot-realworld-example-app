package io.spring.application.history;

import io.spring.core.primary.article.Article;
import io.spring.core.primary.user.User;
import io.spring.core.secondary.history.HistoryArticle;
import io.spring.core.secondary.history.HistoryArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ArticleHistoryService {

    private final HistoryArticleRepository historyArticleRepository;

    @Transactional
    public void createHistory(Article article, String type, User user) {
        historyArticleRepository.save(HistoryArticle.builder()
                .articleId(article.getId())
                .type(type)
                .userId(user.getId())
                .createdAt(LocalDateTime.now())
                .build());
    }
}
