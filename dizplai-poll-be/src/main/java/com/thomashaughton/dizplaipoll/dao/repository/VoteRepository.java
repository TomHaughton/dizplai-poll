package com.thomashaughton.dizplaipoll.dao.repository;

import com.thomashaughton.dizplaipoll.config.CacheConfig;
import com.thomashaughton.dizplaipoll.dao.entity.VoteCount;
import com.thomashaughton.dizplaipoll.dao.entity.Vote;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository("user_poll_answer")
public interface VoteRepository extends JpaRepository<Vote, UUID>, PagingAndSortingRepository<Vote, UUID> {

//    @Query(value = """
//            SELECT upa.answer.id as pollAnswerId, coalesce(count(*), 0) as answerCount
//            FROM UserPollAnswer upa
//            WHERE upa.poll.id = :pollId
//            GROUP BY upa.answer.id
//            """
//    )
//    @Query(value = """
//            SELECT DISTINCT pa.id as pollAnswerId, count(upa.id) as answerCount FROM Poll poll
//                   JOIN PollAnswer pa on pa.poll = poll
//                   RIGHT JOIN UserPollAnswer upa on upa.poll = poll
//            WHERE poll.id = :pollId
//            GROUP BY pa.id
//            """
//    )
    @Query(value = """
            SELECT DISTINCT bin_to_uuid(pa.id) as pollAnswerId, count(vote.id) as voteCount
                  FROM poll
                           INNER JOIN dizplaipoll.poll_answer pa on poll.external_id = pa.poll_id
                           left outer JOIN dizplaipoll.vote vote on pa.id = vote.poll_answer_id
                  WHERE poll.external_id = ?1
                  GROUP BY pa.id
            """,
            nativeQuery = true
    )
    List<VoteCount> countResponsesByPoll(UUID pollId);

    @EntityGraph()
    Page<Vote> findUserPollAnswerByPoll_Id(UUID pollId, PageRequest of);
}
