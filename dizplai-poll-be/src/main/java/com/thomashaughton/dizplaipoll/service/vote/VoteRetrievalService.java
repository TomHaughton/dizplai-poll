package com.thomashaughton.dizplaipoll.service.vote;

import com.thomashaughton.dizplaipoll.assembler.VoteAssembler;
import com.thomashaughton.dizplaipoll.dao.entity.Vote;
import com.thomashaughton.dizplaipoll.dao.repository.VoteRepository;
import com.thomashaughton.dizplaipoll.dto.VoteDto;
import com.thomashaughton.dizplaipoll.dto.response.PageableDto;
import com.thomashaughton.dizplaipoll.enums.SortValue;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VoteRetrievalService {

    private final VoteRepository voteRepository;

    @Transactional
    public PageableDto<VoteDto> getVotes(UUID pollId, int page, int size, SortValue sort) {
        PageRequest pageRequest = PageRequest.of(
                page,
                size,
                Sort.by(new Sort.Order(Sort.Direction.valueOf(sort.name()), "createdAt")
                )
        );
        Page<Vote> answerPage = voteRepository.findUserPollAnswerByPoll_Id(pollId, pageRequest);

        return new PageableDto<VoteDto>(
                answerPage.stream().map(VoteAssembler::toDto).toList(),
                answerPage.getPageable().getPageNumber(),
                answerPage.getNumberOfElements(),
                answerPage.getTotalElements(),
                answerPage.getTotalPages()
        );
    }
}
