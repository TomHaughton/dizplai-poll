package com.thomashaughton.dizplaipoll.assembler;

import com.thomashaughton.dizplaipoll.dao.entity.PollAnswer;
import com.thomashaughton.dizplaipoll.dto.PollAnswerDto;

public class PollAnswerAssembler {

    public static PollAnswerDto toDto(PollAnswer pollAnswer) {
        return new PollAnswerDto(
                pollAnswer.getId(),
                pollAnswer.getAnswer()
        );
    }
}
