package com.thomashaughton.dizplaipoll.dto.response;

import java.util.List;

public record PageableDto<T>(
        List<T> elements,
        int page,
        int size,
        long totalElements,
        long totalPages
) {

}
