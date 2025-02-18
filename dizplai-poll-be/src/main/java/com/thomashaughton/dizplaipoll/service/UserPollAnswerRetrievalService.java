package com.thomashaughton.dizplaipoll.service;

import com.thomashaughton.dizplaipoll.assembler.UserPollAnswerAssembler;
import com.thomashaughton.dizplaipoll.dao.entity.UserPollAnswer;
import com.thomashaughton.dizplaipoll.dao.repository.UserPollAnswerRepository;
import com.thomashaughton.dizplaipoll.dto.UserPollAnswerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserPollAnswerRetrievalService {

    private final UserPollAnswerRepository userPollAnswerRepository;

    @Transactional
    public PageImpl<UserPollAnswerDto> getUserPollAnswers(UUID pollId, int page, int size, String sort) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(new Sort.Order(Sort.Direction.valueOf(sort), "createdAt")));
        Page<UserPollAnswer> answerPage = userPollAnswerRepository.findUserPollAnswerByPoll_Id(pollId, pageRequest);

        List<UserPollAnswerDto> dtoPage = answerPage.stream().map(UserPollAnswerAssembler::toDto).toList();
        return new PageImpl<>(dtoPage, pageRequest, answerPage.getTotalElements());
    }
}
