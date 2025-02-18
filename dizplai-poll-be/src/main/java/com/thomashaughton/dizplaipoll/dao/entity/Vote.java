package com.thomashaughton.dizplaipoll.dao.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.UUID;

@NamedEntityGraph(
        name = "user-poll-answer-entity-graph",
        attributeNodes = {
                @NamedAttributeNode("poll"),
                @NamedAttributeNode("answer")
        }
)
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "vote")
public class Vote implements Serializable {

    @Id
    private UUID id;

    @JoinColumn(name = "poll_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Poll poll;

    @JoinColumn(name = "poll_answer_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private PollAnswer answer;

    private ZonedDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.id = UUID.randomUUID();
        this.createdAt = ZonedDateTime.now();
    }

}
