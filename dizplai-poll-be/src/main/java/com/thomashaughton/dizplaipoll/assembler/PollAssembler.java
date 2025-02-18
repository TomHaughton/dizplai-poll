package com.thomashaughton.dizplaipoll.assembler;

import com.thomashaughton.dizplaipoll.dao.entity.Poll;
import com.thomashaughton.dizplaipoll.dto.PollDto;

public class PollAssembler {

    public static PollDto toDto(Poll poll) {
        return new PollDto(
                poll.getId(),
                poll.getQuestion(),
                poll.getAnswers().stream().map(PollAnswerAssembler::toDto).toList()
        );
    }

}
