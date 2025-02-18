package com.thomashaughton.dizplaipoll.dao.repository;

import com.thomashaughton.dizplaipoll.dao.entity.Poll;
import com.thomashaughton.dizplaipoll.dao.entity.PollAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository("poll_answer")
public interface PollAnswerRepository extends JpaRepository<PollAnswer, UUID>, PagingAndSortingRepository<PollAnswer, UUID> {
}
