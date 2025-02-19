package com.thomashaughton.dizplaipoll.dto.request;

import jakarta.validation.constraints.NotEmpty;

public record PollAnswerCreationDto(
        @NotEmpty String answer
) {
}
