package com.thomashaughton.dizplaipoll;

import com.thomashaughton.dizplaipoll.dto.PollAnswerDto;
import com.thomashaughton.dizplaipoll.dto.request.PollAnswerCreationDto;
import com.thomashaughton.dizplaipoll.dto.request.PollCreationRequestDto;
import com.thomashaughton.dizplaipoll.dto.response.PollCreationResponseDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = {
                "spring.datasource.url=jdbc:tc:mysql:8.0.36:///dizplaipoll",
                "spring.datasource.username=root",
                "spring.datasource.password=test",
                "spring.jpa.hibernate.ddl-auto=update",
                "spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect"
        })
public class PollControllerIT {

        @Autowired
        private WebTestClient webTestClient;

        @Test
        void testCreatePoll() {
                webTestClient.post().uri("/api/v1/polls")
                        .bodyValue(new PollCreationRequestDto(
                                "What's your favourite colour?",
                                List.of(
                                        new PollAnswerCreationDto("Red"),
                                        new PollAnswerCreationDto("Blue")
                                        )
                                )
                        )
                        .exchange()
                        .expectStatus().isCreated()
                        .expectBody(PollCreationResponseDto.class)
                        .consumeWith(result -> {
                                Assertions.assertEquals("What's your favourite colour?", result.getResponseBody().poll().question());
                                assertThat(result.getResponseBody().poll().answers())
                                        .hasSize(2)
                                        .extracting(PollAnswerDto::answer)
                                        .containsExactlyInAnyOrder("Red", "Blue");
                        });
        }
}
