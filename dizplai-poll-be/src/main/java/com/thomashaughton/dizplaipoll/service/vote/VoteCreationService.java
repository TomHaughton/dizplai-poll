package com.thomashaughton.dizplaipoll.service.vote;

import com.thomashaughton.dizplaipoll.dao.entity.Poll;
import com.thomashaughton.dizplaipoll.dao.entity.PollAnswer;
import com.thomashaughton.dizplaipoll.dao.entity.Vote;
import com.thomashaughton.dizplaipoll.dao.repository.PollRepository;
import com.thomashaughton.dizplaipoll.dao.repository.VoteRepository;
import com.thomashaughton.dizplaipoll.dto.request.VoteCreationRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VoteCreationService {

    private final PollRepository pollRepository;
    private final VoteRepository voteRepository;

    @Transactional
    public Vote createVote(
            VoteCreationRequestDto request,
            UUID pollId
    ) {
        Poll poll = pollRepository.findById(pollId)
                .orElseThrow(() -> new ResourceNotFoundException("Poll %s not found".formatted(pollId)));
        PollAnswer pollAnswer = poll.getAnswers().stream()
                .filter(answer -> answer.getId().equals(request.pollAnswerId()))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Poll answer %s not found".formatted(request.pollAnswerId())));

        Vote vote = Vote.builder()
                .poll(poll)
                .answer(pollAnswer)
                .build();
        return voteRepository.save(vote);
    }

}
