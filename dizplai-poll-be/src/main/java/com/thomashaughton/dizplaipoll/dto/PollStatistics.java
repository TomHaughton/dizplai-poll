package com.thomashaughton.dizplaipoll.dto;

import java.util.List;

public record PollStatistics(
        Long total,
        List<AnswerStatistic> answerStatistics
) {
}
