package io.spring.application.history;

import io.spring.application.OffsetBasedPageRequest;
import io.spring.application.Page;
import io.spring.application.data.HistoryData;
import io.spring.application.data.HistoryDataList;
import io.spring.core.primary.article.Article;
import io.spring.core.primary.user.User;
import io.spring.core.secondary.history.HistoryArticle;
import io.spring.core.secondary.history.HistoryArticleRepository;
import io.spring.core.secondary.history.HistoryTypes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
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

    public HistoryDataList getHistoryByUserId(User user, Page page) {
        Pageable pageable = new OffsetBasedPageRequest(page.getLimit(), page.getOffset());
        List<HistoryArticle> history = historyArticleRepository.findByUserIdOrderByCreatedAtDesc(user.getId(), pageable);
        List<HistoryData> datalist = new ArrayList<>();
        history.forEach(h -> datalist.add(transHistoryData(h)));
        int historyCount = historyArticleRepository.countHistory(user.getId());
        return new HistoryDataList(datalist, historyCount);
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
