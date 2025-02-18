package com.thomashaughton.dizplaipoll.http.controller;

import com.thomashaughton.dizplaipoll.dto.VoteDto;
import com.thomashaughton.dizplaipoll.dto.request.VoteCreationRequestDto;
import com.thomashaughton.dizplaipoll.dto.response.PageableDto;
import com.thomashaughton.dizplaipoll.dto.response.VoteCreationResponseDto;
import com.thomashaughton.dizplaipoll.service.vote.VoteCastService;
import com.thomashaughton.dizplaipoll.service.vote.VoteCreationService;
import com.thomashaughton.dizplaipoll.service.vote.VoteRetrievalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/polls")
public class VoteController {

    private final VoteCreationService voteCreationService;
    private final VoteRetrievalService voteRetrievalService;
    private final VoteCastService voteCastService;

    @PostMapping("/{pollId}/votes")
    @ResponseStatus(HttpStatus.CREATED)
    public VoteCreationResponseDto createVote(
            @RequestBody VoteCreationRequestDto request,
            @PathVariable("pollId") UUID pollId
    ) {
        return voteCastService.castVote(request, pollId);
    }

    @GetMapping("/{pollId}/votes")
    @ResponseStatus(HttpStatus.OK)
    public PageableDto<VoteDto> getVotes(
            @PathVariable("pollId") UUID pollId,
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("sort") String sort
    ) {
        return voteRetrievalService.getVotes(pollId, page, size, sort);
    }
}
