package io.spring.api;

import io.spring.application.Page;
import io.spring.application.history.ArticleHistoryService;
import io.spring.core.primary.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/history")
@RequiredArgsConstructor
public class HistoryApi {

    private final ArticleHistoryService articleHistoryService;

    @GetMapping
    public ResponseEntity<?> getHistory(
            @RequestParam(value = "offset", defaultValue = "0") int offset,
            @RequestParam(value = "limit", defaultValue = "20") int limit,
            @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(articleHistoryService.getHistoryByUserId(user, new Page(offset, limit)));
    }
}
