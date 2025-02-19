package com.thomashaughton.dizplaipoll.service.poll;

import com.thomashaughton.dizplaipoll.assembler.PollAssembler;
import com.thomashaughton.dizplaipoll.config.CacheConfig;
import com.thomashaughton.dizplaipoll.dao.entity.Poll;
import com.thomashaughton.dizplaipoll.dao.repository.PollRepository;
import com.thomashaughton.dizplaipoll.dto.PollDto;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PollRetrievalService {

    private final PollRepository pollRepository;

    @Transactional(readOnly = true)
    @Cacheable(CacheConfig.POLL_CACHE)
    public PollDto getLatestPoll() {
        Poll poll = pollRepository.findFirstByOrderByCreatedAtDesc()
                .orElseThrow(() -> new ResourceNotFoundException("No active polls exist"));
        return PollAssembler.toDto(poll);
    }
}
