package io.spring.application.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class HistoryDataList {
    @JsonProperty("history")
    private final List<HistoryData> historyDatas;

    @JsonProperty("historyCount")
    private final int count;

    public HistoryDataList(List<HistoryData> historyDatas, int count) {
        this.historyDatas = historyDatas;
        this.count = count;
    }
}
