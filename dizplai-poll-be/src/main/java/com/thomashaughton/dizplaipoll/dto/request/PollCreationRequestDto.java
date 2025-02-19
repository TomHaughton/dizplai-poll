package com.thomashaughton.dizplaipoll.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public record PollCreationRequestDto(
        @NotEmpty String question,
        @Size(min = 2, max = 7) List<PollAnswerCreationDto> pollAnswers
) {
}
