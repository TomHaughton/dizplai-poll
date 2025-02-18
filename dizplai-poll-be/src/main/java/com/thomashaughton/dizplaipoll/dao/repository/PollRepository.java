package com.thomashaughton.dizplaipoll.dao.repository;

import com.thomashaughton.dizplaipoll.dao.entity.Poll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository("poll")
public interface PollRepository extends JpaRepository<Poll, UUID>, PagingAndSortingRepository<Poll, UUID> {

    Optional<Poll> findFirstByOrderByCreatedAtDesc();
}
