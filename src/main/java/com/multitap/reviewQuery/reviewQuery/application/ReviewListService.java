package com.multitap.reviewQuery.reviewQuery.application;

import com.multitap.reviewQuery.reviewQuery.dto.out.ReviewListResponseDto;
import org.springframework.data.domain.Page;

import java.io.IOException;
import java.util.List;

public interface ReviewListService {
    Page<ReviewListResponseDto> getReviewListByMentoringUuid(String mentoringUuid, int page, int size);
}
