package com.thomashaughton.dizplaipoll.dto.response;

import com.thomashaughton.dizplaipoll.dto.PollStatistics;
import com.thomashaughton.dizplaipoll.dto.VoteDto;

public record VoteCreationResponseDto(
        VoteDto answer,
        PollStatistics pollStatistics
) {
}
