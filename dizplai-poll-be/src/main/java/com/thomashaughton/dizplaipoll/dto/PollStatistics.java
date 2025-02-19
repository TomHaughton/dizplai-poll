package com.thomashaughton.dizplaipoll.dto;

import java.util.Map;
import java.util.UUID;

public record PollStatistics(
        Long total,
        Map<UUID, AnswerStatistic> answerStatistics
) {
}
