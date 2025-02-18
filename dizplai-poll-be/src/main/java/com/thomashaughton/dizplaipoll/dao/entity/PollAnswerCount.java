package com.thomashaughton.dizplaipoll.dao.entity;

import java.math.BigDecimal;
import java.util.UUID;

public interface PollAnswerCount {

    public UUID getPollAnswerId();
    public BigDecimal getAnswerCount();
}
