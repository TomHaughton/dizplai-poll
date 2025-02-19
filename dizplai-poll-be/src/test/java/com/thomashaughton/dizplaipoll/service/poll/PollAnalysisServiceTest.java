package com.thomashaughton.dizplaipoll.service.poll;

import com.thomashaughton.dizplaipoll.dao.entity.Poll;
import com.thomashaughton.dizplaipoll.dao.entity.PollAnswer;
import com.thomashaughton.dizplaipoll.dao.entity.VoteCount;
import com.thomashaughton.dizplaipoll.dao.repository.PollRepository;
import com.thomashaughton.dizplaipoll.dao.repository.VoteRepository;
import com.thomashaughton.dizplaipoll.dto.PollStatistics;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PollAnalysisServiceTest {

    private static final UUID ANSWER_1_ID = UUID.randomUUID();
    private static final UUID ANSWER_2_ID = UUID.randomUUID();
    private static final UUID ANSWER_3_ID = UUID.randomUUID();

    private final PollRepository pollRepository = mock(PollRepository.class);
    private final VoteRepository voteRepository = mock(VoteRepository.class);

    private final PollAnalysisService victim = new PollAnalysisService(
            pollRepository,
            voteRepository
    );

    @ParameterizedTest
    @MethodSource("voteAnalysisProvider")
    void shouldAnalyzeResultsCorrectly(
            final int total,
            final List<VoteCount> voteCounts,
            final BigDecimal expectedPercentage
    ) {
        UUID pollId = UUID.randomUUID();

        when(pollRepository.findById(pollId)).thenReturn(Optional.of(getPoll(pollId)));
        when(voteRepository.countResponsesByPoll(pollId)).thenReturn(voteCounts);

        PollStatistics pollStatistics = victim.analyzePoll(pollId);

        Assertions.assertAll(() -> {
            Assertions.assertEquals(total, pollStatistics.total());
            Assertions.assertEquals(voteCounts.size(), pollStatistics.answerStatistics().size());
        });
    }

    @Test
    void shouldCalculatePercentageCorrectly() {
        UUID pollId = UUID.randomUUID();

        VoteCount vc1 = mock(VoteCount.class);
        VoteCount vc2 = mock(VoteCount.class);
        VoteCount vc3 = mock(VoteCount.class);

        when(vc1.getVoteCount()).thenReturn(BigDecimal.ONE);
        when(vc1.getPollAnswerId()).thenReturn(ANSWER_1_ID);
        when(vc2.getVoteCount()).thenReturn(BigDecimal.TEN);
        when(vc2.getPollAnswerId()).thenReturn(ANSWER_2_ID);
        when(vc3.getVoteCount()).thenReturn(BigDecimal.ZERO);
        when(vc3.getPollAnswerId()).thenReturn(ANSWER_3_ID);

        when(pollRepository.findById(pollId)).thenReturn(Optional.of(getPoll(pollId)));
        when(voteRepository.countResponsesByPoll(pollId)).thenReturn(List.of(vc1, vc2, vc3));

        PollStatistics pollStatistics = victim.analyzePoll(pollId);

        Assertions.assertAll(() -> {
            Assertions.assertEquals(11L, pollStatistics.total());
            Assertions.assertEquals(BigDecimal.valueOf(9), pollStatistics.answerStatistics().get(ANSWER_1_ID).percentage());
            Assertions.assertEquals(BigDecimal.valueOf(91), pollStatistics.answerStatistics().get(ANSWER_2_ID).percentage());
            Assertions.assertEquals(BigDecimal.valueOf(0), pollStatistics.answerStatistics().get(ANSWER_3_ID).percentage());
        });
    }

    public static Stream<Arguments> voteAnalysisProvider() {
        VoteCount vc1 = mock(VoteCount.class);
        VoteCount vc2 = mock(VoteCount.class);

        when(vc1.getVoteCount()).thenReturn(BigDecimal.ONE);
        when(vc1.getPollAnswerId()).thenReturn(ANSWER_1_ID);
        when(vc2.getVoteCount()).thenReturn(BigDecimal.TEN);
        when(vc2.getPollAnswerId()).thenReturn(ANSWER_2_ID);
        return Stream.of(
                Arguments.of(11, List.of(vc1, vc2)),
                Arguments.of(1, List.of(vc1)),
                Arguments.of(0, List.of())
        );
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
