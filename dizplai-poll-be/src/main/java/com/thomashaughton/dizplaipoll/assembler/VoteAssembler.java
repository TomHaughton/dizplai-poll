package com.thomashaughton.dizplaipoll.assembler;

import com.thomashaughton.dizplaipoll.dao.entity.Vote;
import com.thomashaughton.dizplaipoll.dto.VoteDto;

public class VoteAssembler {

    public static VoteDto toDto(Vote vote) {
        return new VoteDto(
                vote.getId(),
                vote.getPoll().getId(),
                vote.getAnswer().getId(),
                vote.getCreatedAt()
        );
    }
}
