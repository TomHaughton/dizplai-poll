package com.thomashaughton.dizplaipoll.assembler;

import com.thomashaughton.dizplaipoll.dao.entity.UserPollAnswer;
import com.thomashaughton.dizplaipoll.dto.UserPollAnswerDto;

public class UserPollAnswerAssembler {

    public static UserPollAnswerDto toDto(UserPollAnswer userPollAnswer) {
        return new UserPollAnswerDto(
                userPollAnswer.getPoll().getId(),
                userPollAnswer.getAnswer().getId(),
                userPollAnswer.getId(),
                userPollAnswer.getCreatedAt()
        );
    }
}
