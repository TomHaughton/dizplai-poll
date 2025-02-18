package com.thomashaughton.dizplaipoll.dao.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;

import java.io.Serializable;
import java.sql.Types;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@NamedEntityGraph(
        name = "poll-entity-graph",
        attributeNodes = {
                @NamedAttributeNode("answers")
        }
)
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "poll")
public class Poll implements Serializable {

    @Id
    @JdbcTypeCode(Types.BINARY)
    @Column(name = "external_id", columnDefinition = "")
    private UUID id;

    private String question;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "poll_id")
    private List<PollAnswer> answers;

    private ZonedDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.id = UUID.randomUUID();
        this.createdAt = ZonedDateTime.now();
    }

    @Builder
    public Poll(String question, List<PollAnswer> answers) {
        this.question = question;
        this.answers = answers;
    }

}
