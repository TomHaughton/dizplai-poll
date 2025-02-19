package com.thomashaughton.dizplaipoll.dto.request;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.validation.annotation.Validated;

@Validated
public record PollAnswerCreationDto(
        @NotEmpty String answer
) {
}
