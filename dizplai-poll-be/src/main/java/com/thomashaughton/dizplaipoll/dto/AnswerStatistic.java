package com.thomashaughton.dizplaipoll.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record AnswerStatistic(
        BigDecimal percentage,
        Long total
) {
}
