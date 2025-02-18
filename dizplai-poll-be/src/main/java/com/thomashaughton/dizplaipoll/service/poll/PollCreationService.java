package com.thomashaughton.dizplaipoll.service.poll;

import com.thomashaughton.dizplaipoll.assembler.PollAssembler;
import com.thomashaughton.dizplaipoll.config.CacheConfig;
import com.thomashaughton.dizplaipoll.dao.entity.Poll;
import com.thomashaughton.dizplaipoll.dao.entity.PollAnswer;
import com.thomashaughton.dizplaipoll.dao.repository.PollRepository;
import com.thomashaughton.dizplaipoll.dto.PollAnswerDto;
import com.thomashaughton.dizplaipoll.dto.request.PollCreationRequestDto;
import com.thomashaughton.dizplaipoll.dto.PollDto;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PollCreationService {

    private final PollRepository pollRepository;

    @Transactional
    @CacheEvict(value = CacheConfig.POLL_CACHE, allEntries = true)
    public PollDto createPoll(PollCreationRequestDto pollCreationRequestDto) {
        Poll poll = Poll.builder()
                .question(pollCreationRequestDto.question())
                .build();
        poll.setAnswers(buildAnswers(pollCreationRequestDto.pollAnswers(), poll));
        Poll save = pollRepository.save(poll);
        return PollAssembler.toDto(save);
    }

    private List<PollAnswer> buildAnswers(List<PollAnswerDto> pollAnswerDtos, Poll poll) {
        return pollAnswerDtos.stream()
                .map(dto ->
                        PollAnswer.builder()
                                .answer(dto.answer())
                                .poll(poll)
                                .build()
                )
                .toList();
    }
}
