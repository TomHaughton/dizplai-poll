package com.thomashaughton.dizplaipoll.dao.entity;

import java.math.BigDecimal;
import java.util.UUID;

public interface VoteCount {

    public UUID getPollAnswerId();
    public BigDecimal getVoteCount();
}
