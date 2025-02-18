package com.thomashaughton.dizplaipoll.service.vote;

import com.thomashaughton.dizplaipoll.dao.entity.Poll;
import com.thomashaughton.dizplaipoll.dao.entity.PollAnswer;
import com.thomashaughton.dizplaipoll.dao.entity.Vote;
import com.thomashaughton.dizplaipoll.dao.repository.PollRepository;
import com.thomashaughton.dizplaipoll.dao.repository.VoteRepository;
import com.thomashaughton.dizplaipoll.dto.request.VoteCreationRequestDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class VoteCreationServiceTest {

    private PollRepository pollRepository = mock(PollRepository.class);
    private VoteRepository voteRepository = mock(VoteRepository.class);

    private static final UUID ANSWER_1_ID = UUID.randomUUID();
    private static final UUID ANSWER_2_ID = UUID.randomUUID();

    private final VoteCreationService victim = new VoteCreationService(
            pollRepository,
            voteRepository
    );

    @Test
    void shouldSaveVote() {
        UUID pollId = UUID.randomUUID();
        when(pollRepository.findById(pollId)).thenReturn(Optional.of(getPoll(pollId)));
        when(voteRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        Vote vote = victim.createVote(new VoteCreationRequestDto(ANSWER_1_ID), pollId);

        verify(voteRepository, times(1)).save(vote);
        Assertions.assertAll(() -> {
            Assertions.assertEquals(ANSWER_1_ID, vote.getAnswer().getId());
            Assertions.assertEquals(pollId, vote.getPoll().getId());
        });
    }

    @Test
    void shouldNotSaveVoteIfPollDoesNotExist() {
        UUID pollId = UUID.randomUUID();

        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            victim.createVote(new VoteCreationRequestDto(ANSWER_1_ID), pollId);
        });
    }

    @Test
    void shouldNotSaveVoteIfPollAnswerDoesNotExist() {
        UUID pollId = UUID.randomUUID();

        when(pollRepository.findById(pollId)).thenReturn(Optional.of(getPoll(pollId)));

        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            victim.createVote(new VoteCreationRequestDto(UUID.randomUUID()), pollId);
        });
    }

    private Poll getPoll(UUID pollId) {
        return Poll.builder()
                .id(pollId)
                .question("Question?")
                .answers(List.of(
                        PollAnswer.builder()
                                .answer("Answer 1!")
                                .id(ANSWER_1_ID)
                                .build(),
                        PollAnswer.builder()
                                .answer("Answer 2!")
                                .id(ANSWER_2_ID)
                                .build()
                ))
                .createdAt(ZonedDateTime.now())
                .build();
    }
}
