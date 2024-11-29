package com.multitap.reviewQuery.reviewQuery.application;

import com.multitap.reviewQuery.reviewQuery.dto.out.ReviewListResponseDto;
import org.springframework.data.domain.Page;

import java.io.IOException;
import java.util.List;

public interface ReviewListService {
    Page<ReviewListResponseDto> getReviewListByMentoringUuid(String mentoringUuid, int page, int size);
    Page<ReviewListResponseDto> getReviewListByMentorUuid(String mentorUuid, int page, int size);
    Page<ReviewListResponseDto> getReviewListByMenteeUuid(String menteeUuid, int page, int size);
    Long countReviewByMentorUuid(String mentorUuid);
    List<ReviewListResponseDto> getBestReviewByMentoringUuid(String mentoringUuid);
    List<ReviewListResponseDto> getRecentReviewByMentorUuid(String mentorUuid);
}
