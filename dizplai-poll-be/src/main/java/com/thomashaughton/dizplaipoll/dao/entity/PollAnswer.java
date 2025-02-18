package com.thomashaughton.dizplaipoll.dao.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@NamedEntityGraph(
        name = "poll-answer-entity-graph",
        attributeNodes = {}
)
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "poll_answer")
public class PollAnswer implements Serializable {

    @Id
    private UUID id;

    private String answer;

    @JoinColumn(name = "poll_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Poll poll;

    @PrePersist
    public void prePersist() {
        this.id = UUID.randomUUID();
    }
}
