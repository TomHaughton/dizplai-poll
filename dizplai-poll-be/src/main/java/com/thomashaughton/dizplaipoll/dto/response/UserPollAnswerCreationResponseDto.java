package com.thomashaughton.dizplaipoll.dto.response;

import com.thomashaughton.dizplaipoll.dto.PollStatistics;
import com.thomashaughton.dizplaipoll.dto.UserPollAnswerDto;

public record UserPollAnswerCreationResponseDto(
        UserPollAnswerDto answer,
        PollStatistics pollStatistics
) {
}
