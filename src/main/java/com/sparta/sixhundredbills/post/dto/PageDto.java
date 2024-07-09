package com.sparta.sixhundredbills.post.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Objects;

@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class PageDto {
    private final Integer currentPage;
    private final Integer pageSize;
    private String sortBy;

    public Pageable toPageable() {
        if (Objects.isNull(sortBy)) {
            return PageRequest.of(currentPage - 1, pageSize);
        } else {
            return PageRequest.of(currentPage - 1, pageSize, Sort.by(sortBy).descending());
        }
    }

    public Pageable toPageable(String sortBy) {
        return PageRequest.of(currentPage -1, pageSize, Sort.by(sortBy).descending());
    }
}
