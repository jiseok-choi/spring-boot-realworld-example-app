package io.spring.core.secondary.history;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "history_article")
public class HistoryArticle {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", nullable = false, length = 36)
    private String id;

    @Column(name = "article_id", nullable = false)
    private String articleId;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private HistoryTypes type;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Builder
    public HistoryArticle(String id, String articleId, HistoryTypes type, String userId, LocalDateTime createdAt) {
        this.id = id;
        this.articleId = articleId;
        this.type = type;
        this.userId = userId;
        this.createdAt = createdAt;
    }
}
