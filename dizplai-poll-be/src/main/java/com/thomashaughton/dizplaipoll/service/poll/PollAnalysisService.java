package com.thomashaughton.dizplaipoll.service.poll;

import com.thomashaughton.dizplaipoll.config.CacheConfig;
import com.thomashaughton.dizplaipoll.dao.entity.Poll;
import com.thomashaughton.dizplaipoll.dao.entity.VoteCount;
import com.thomashaughton.dizplaipoll.dao.repository.PollRepository;
import com.thomashaughton.dizplaipoll.dao.repository.VoteRepository;
import com.thomashaughton.dizplaipoll.dto.AnswerStatistic;
import com.thomashaughton.dizplaipoll.dto.PollStatistics;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PollAnalysisService {

    private final PollRepository pollRepository;
    private final VoteRepository voteRepository;

    @Cacheable(CacheConfig.POLL_ANALYSIS_CACHE)
    public PollStatistics analyzePoll(UUID pollId) {
        Poll poll = pollRepository.findById(pollId)
                .orElseThrow(() -> new ResourceNotFoundException("Poll %s not found".formatted(pollId)));

        List<VoteCount> voteCounts = voteRepository.countResponsesByPoll(poll.getId());
        BigDecimal total = voteCounts.stream()
                .map(VoteCount::getVoteCount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Map<UUID, AnswerStatistic> answerStats = voteCounts.stream()
                .collect(Collectors.toMap(VoteCount::getPollAnswerId, e -> getAnswerStatistic(e, total)));
        return new PollStatistics(total.longValue(), answerStats);
    }

    private static AnswerStatistic getAnswerStatistic(VoteCount entry, BigDecimal total) {
        BigDecimal percentage = entry.getVoteCount()
                .divide(total, 2, RoundingMode.HALF_DOWN)
                .multiply(BigDecimal.valueOf(100))
                .setScale(0, RoundingMode.HALF_DOWN);
        return new AnswerStatistic(percentage, entry.getVoteCount().longValue());
    }
}
