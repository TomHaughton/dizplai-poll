package com.thomashaughton.dizplaipoll.dao.repository;

import com.thomashaughton.dizplaipoll.dao.entity.PollAnswerCount;
import com.thomashaughton.dizplaipoll.dao.entity.UserPollAnswer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository("user_poll_answer")
public interface UserPollAnswerRepository extends JpaRepository<UserPollAnswer, UUID>, PagingAndSortingRepository<UserPollAnswer, UUID> {

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
            SELECT DISTINCT bin_to_uuid(pa.id) as pollAnswerId, count(upa.id) as answerCount
                                        FROM poll
                                                 INNER JOIN dizplaipoll.poll_answer pa on poll.external_id = pa.poll_id
                                                 left outer JOIN dizplaipoll.user_poll_answer upa on pa.id = upa.poll_answer_id
                                        WHERE poll.external_id = ?1
                                        GROUP BY pa.id
            """,
            nativeQuery = true
    )
    List<PollAnswerCount> countResponsesByPoll(UUID pollId);

    @EntityGraph()
    Page<UserPollAnswer> findUserPollAnswerByPoll_Id(UUID pollId, PageRequest of);
}
