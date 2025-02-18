package com.thomashaughton.dizplaipoll.dto.request;

import java.util.UUID;

public record VoteCreationRequestDto(
        UUID pollAnswerId
) {
}
