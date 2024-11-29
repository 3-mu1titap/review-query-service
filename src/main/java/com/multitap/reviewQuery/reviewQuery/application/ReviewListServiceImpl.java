package com.multitap.reviewQuery.reviewQuery.application;

import com.multitap.reviewQuery.reviewQuery.dto.out.ReviewListResponseDto;
import com.multitap.reviewQuery.reviewQuery.entity.ReviewList;
import com.multitap.reviewQuery.reviewQuery.infrastructure.ReviewListRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewListServiceImpl implements ReviewListService {

    private final ReviewListRepository reviewListRepository;

    @Override
    public Page<ReviewListResponseDto> getReviewListByMentoringUuid(String mentoringUuid, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "wroteAt"));

        return reviewListRepository.findByMentoringUuid(mentoringUuid, pageable)
                .map(ReviewListResponseDto::from);
    }

    @Override
    public Page<ReviewListResponseDto> getReviewListByMentorUuid(String mentorUuid, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "wroteAt"));

        return reviewListRepository.findByMentorUuid(mentorUuid, pageable)
                .map(ReviewListResponseDto::from);
    }

    @Override
    public Page<ReviewListResponseDto> getReviewListByMenteeUuid(String menteeUuid, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "wroteAt"));

        return reviewListRepository.findByMenteeUuid(menteeUuid, pageable)
                .map(ReviewListResponseDto::from);
    }
}
