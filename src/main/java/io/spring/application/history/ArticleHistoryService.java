package io.spring.application.history;

import io.spring.application.data.HistoryData;
import io.spring.core.primary.article.Article;
import io.spring.core.primary.user.User;
import io.spring.core.secondary.history.HistoryArticle;
import io.spring.core.secondary.history.HistoryArticleRepository;
import io.spring.core.secondary.history.HistoryTypes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ArticleHistoryService {

    private final HistoryArticleRepository historyArticleRepository;

    @Transactional
    public void createHistory(Article article, HistoryTypes type, User user) {
        historyArticleRepository.save(HistoryArticle.builder()
                .articleId(article.getId())
                .type(type)
                .userId(user.getId())
                .createdAt(LocalDateTime.now())
                .build());
    }

    public List<HistoryData> getHistoryByUserId(User user) {
        List<HistoryArticle> history = historyArticleRepository.findByUserId(user.getId());
        List<HistoryData> result = new ArrayList<>();
        history.forEach(h -> result.add(transHistoryData(h)));
        return result;
    }

    private HistoryData transHistoryData(HistoryArticle hist) {
        return HistoryData.builder()
                .historyId(hist.getId())
                .articleId(hist.getArticleId())
                .type(hist.getType())
                .createdAt(hist.getCreatedAt())
                .build();
    }
}
