package io.spring.core.secondary.history;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum HistoryTypes {

    CREATE("create"),
    UPDATE("update"),
    DELETE("delete")
    ;

    private final String code;
}
