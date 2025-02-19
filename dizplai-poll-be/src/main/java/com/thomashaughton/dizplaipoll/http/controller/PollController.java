package com.thomashaughton.dizplaipoll.http.controller;

import com.thomashaughton.dizplaipoll.dto.PollDto;
import com.thomashaughton.dizplaipoll.dto.request.PollCreationRequestDto;
import com.thomashaughton.dizplaipoll.dto.response.PollCreationResponseDto;
import com.thomashaughton.dizplaipoll.service.poll.PollCreationService;
import com.thomashaughton.dizplaipoll.service.poll.PollRetrievalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/polls")
public class PollController {

    private final PollCreationService pollCreationService;
    private final PollRetrievalService pollRetrievalService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PollCreationResponseDto createPoll(@RequestBody @Valid PollCreationRequestDto request) {
        return new PollCreationResponseDto(pollCreationService.createPoll(request));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PollDto getActivePoll() {
        return pollRetrievalService.getLatestPoll();
    }
}
