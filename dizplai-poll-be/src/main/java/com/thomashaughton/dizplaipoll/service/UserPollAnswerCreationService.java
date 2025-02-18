package com.thomashaughton.dizplaipoll.service;

import com.thomashaughton.dizplaipoll.assembler.UserPollAnswerAssembler;
import com.thomashaughton.dizplaipoll.dao.entity.Poll;
import com.thomashaughton.dizplaipoll.dao.entity.PollAnswer;
import com.thomashaughton.dizplaipoll.dao.entity.PollAnswerCount;
import com.thomashaughton.dizplaipoll.dao.entity.UserPollAnswer;
import com.thomashaughton.dizplaipoll.dao.repository.PollAnswerRepository;
import com.thomashaughton.dizplaipoll.dao.repository.PollRepository;
import com.thomashaughton.dizplaipoll.dao.repository.UserPollAnswerRepository;
import com.thomashaughton.dizplaipoll.dto.AnswerStatistic;
import com.thomashaughton.dizplaipoll.dto.PollStatistics;
import com.thomashaughton.dizplaipoll.dto.request.UserPollAnswerCreationRequestDto;
import com.thomashaughton.dizplaipoll.dto.response.UserPollAnswerCreationResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserPollAnswerCreationService {

    private final PollRepository pollRepository;
    private final PollAnswerRepository pollAnswerRepository;
    private final UserPollAnswerRepository userPollAnswerRepository;

    @Transactional
    public UserPollAnswerCreationResponseDto createUserPollAnswer(
            UserPollAnswerCreationRequestDto request,
            UUID pollId
    ) {
        Poll poll = pollRepository.findById(pollId)
                .orElseThrow(() -> new ResourceNotFoundException("Poll %s not found".formatted(pollId)));
        PollAnswer pollAnswer = pollAnswerRepository.findById(request.pollAnswerId())
                .orElseThrow(() -> new ResourceNotFoundException("Poll answer %s not found".formatted(request.pollAnswerId())));

        UserPollAnswer userPollAnswer = UserPollAnswer.builder()
                .poll(poll)
                .answer(pollAnswer)
                .build();
        UserPollAnswer save = userPollAnswerRepository.save(userPollAnswer);
        PollStatistics pollStatistics = analyzePoll(poll);

        return new UserPollAnswerCreationResponseDto(UserPollAnswerAssembler.toDto(save), pollStatistics);
    }

    private PollStatistics analyzePoll(Poll poll) {
        List<PollAnswerCount> pollAnswerCounts = userPollAnswerRepository.countResponsesByPoll(poll.getId());
        BigDecimal total = pollAnswerCounts.stream()
                .map(PollAnswerCount::getAnswerCount)
//                .toList()
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        List<AnswerStatistic> answerStats = pollAnswerCounts.stream()
                .map(entry -> getAnswerStatistic(entry, total))
                .toList();
        return new PollStatistics(total.longValue(), answerStats);
    }

    private static AnswerStatistic getAnswerStatistic(PollAnswerCount entry, BigDecimal total) {
        BigDecimal percentage = entry.getAnswerCount()
                .divide(total, 2, RoundingMode.HALF_DOWN)
                .multiply(BigDecimal.valueOf(100))
                .setScale(0, RoundingMode.HALF_DOWN);
        return new AnswerStatistic(entry.getPollAnswerId(), percentage, entry.getAnswerCount().longValue());
    }

}
