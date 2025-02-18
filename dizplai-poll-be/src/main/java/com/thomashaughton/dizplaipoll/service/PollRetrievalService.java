package com.thomashaughton.dizplaipoll.service;

import com.thomashaughton.dizplaipoll.assembler.PollAssembler;
import com.thomashaughton.dizplaipoll.dao.repository.PollRepository;
import com.thomashaughton.dizplaipoll.dto.PollDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PollRetrievalService {

    private final PollRepository pollRepository;

    @Transactional(readOnly = true)
    public PollDto getLatestPoll() {
        return PollAssembler.toDto(pollRepository.findFirstByOrderByCreatedAtDesc().orElseThrow());
    }
}
