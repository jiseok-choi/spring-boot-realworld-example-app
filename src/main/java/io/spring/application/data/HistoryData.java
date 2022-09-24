package io.spring.application.data;

import io.spring.core.secondary.history.HistoryTypes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HistoryData {
    private String historyId;
    private String articleId;
    private HistoryTypes type;
    private String userId;
    private LocalDateTime createdAt;
}
