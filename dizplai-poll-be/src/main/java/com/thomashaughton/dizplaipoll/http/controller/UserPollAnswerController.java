package com.thomashaughton.dizplaipoll.http.controller;

import com.thomashaughton.dizplaipoll.dto.UserPollAnswerDto;
import com.thomashaughton.dizplaipoll.dto.request.UserPollAnswerCreationRequestDto;
import com.thomashaughton.dizplaipoll.dto.response.UserPollAnswerCreationResponseDto;
import com.thomashaughton.dizplaipoll.service.UserPollAnswerCreationService;
import com.thomashaughton.dizplaipoll.service.UserPollAnswerRetrievalService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/polls")
public class UserPollAnswerController {

    private final UserPollAnswerCreationService userPollAnswerCreationService;
    private final UserPollAnswerRetrievalService userPollAnswerRetrievalService;

    @PostMapping("/{pollId}/answers")
    @ResponseStatus(HttpStatus.CREATED)
    public UserPollAnswerCreationResponseDto createUserPollAnswer(
            @RequestBody UserPollAnswerCreationRequestDto request,
            @PathVariable("pollId") UUID pollId
    ) {
        return userPollAnswerCreationService.createUserPollAnswer(request, pollId);
    }

    @GetMapping("/{pollId}/answers")
    @ResponseStatus(HttpStatus.OK)
    public PageImpl<UserPollAnswerDto> getUserPollAnswer(
            @PathVariable("pollId") UUID pollId,
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("sort") String sort
    ) {
        return userPollAnswerRetrievalService.getUserPollAnswers(pollId, page, size, sort);
    }
}
