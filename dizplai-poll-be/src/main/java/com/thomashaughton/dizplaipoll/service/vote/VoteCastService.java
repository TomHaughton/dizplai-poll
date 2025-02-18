package com.thomashaughton.dizplaipoll.service.vote;

import com.thomashaughton.dizplaipoll.assembler.VoteAssembler;
import com.thomashaughton.dizplaipoll.dao.entity.Vote;
import com.thomashaughton.dizplaipoll.dto.PollStatistics;
import com.thomashaughton.dizplaipoll.dto.request.VoteCreationRequestDto;
import com.thomashaughton.dizplaipoll.dto.response.VoteCreationResponseDto;
import com.thomashaughton.dizplaipoll.service.poll.PollAnalysisService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VoteCastService {

    private final VoteCreationService voteCreationService;
    private final PollAnalysisService pollAnalysisService;

    public VoteCreationResponseDto castVote(
            VoteCreationRequestDto request,
            UUID pollId
    ) {
        Vote vote = voteCreationService.createVote(request, pollId);
        PollStatistics pollStatistics = pollAnalysisService.analyzePoll(pollId);
        return new VoteCreationResponseDto(VoteAssembler.toDto(vote), pollStatistics);
    }
}
