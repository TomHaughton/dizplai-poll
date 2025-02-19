package com.thomashaughton.dizplaipoll.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

@Validated
public record VoteCreationRequestDto(
        @NotNull UUID pollAnswerId
) {
}
