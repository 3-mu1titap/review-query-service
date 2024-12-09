package com.multitap.reviewQuery.reviewQuery.application;

import com.multitap.reviewQuery.reviewQuery.dto.out.ReviewListResponseDto;
import com.multitap.reviewQuery.reviewQuery.dto.out.ReviewerProfileImageResponseDto;
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
import java.util.Locale;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewListServiceImpl implements ReviewListService {

    private final ReviewListRepository reviewListRepository;

    @Override
    public Page<ReviewListResponseDto> getReviewListByMentoringUuid(String mentoringUuid, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "wroteAt"));

        return reviewListRepository.findByMentoringUuidAndIsDeletedFalse(mentoringUuid, pageable)
                .map(ReviewListResponseDto::from);
    }

    @Override
    public Page<ReviewListResponseDto> getReviewListByMentorUuid(String mentorUuid, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "wroteAt"));

        return reviewListRepository.findByMentorUuidAndIsDeletedFalse(mentorUuid, pageable)
                .map(ReviewListResponseDto::from);
    }

    @Override
    public Page<ReviewListResponseDto> getReviewListByMenteeUuid(String menteeUuid, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "wroteAt"));

        return reviewListRepository.findByMenteeUuidAndIsDeletedFalse(menteeUuid, pageable)
                .map(ReviewListResponseDto::from);
    }

    @Override
    public Long countReviewByMentorUuid(String mentorUuid) {
        return reviewListRepository.countByMentorUuidAndIsDeletedFalse(mentorUuid);
    }

    @Override
    public List<ReviewListResponseDto> getBestReviewByMentoringUuid(String mentoringUuid) {
        Pageable pageable = PageRequest.of(0, 3);// 첫 3개 리뷰만 가져오기

        return reviewListRepository.findTopReviewListsByMentoringUuidAndIsDeletedFalse(mentoringUuid, pageable)
                .stream()
                .map(ReviewListResponseDto::from)
                .collect(Collectors.toList());

    }

    @Override
    public List<ReviewListResponseDto> getRecentReviewByMentorUuid(String mentorUuid) {
        Pageable pageable = PageRequest.of(0, 5);// 첫 5개 리뷰만 가져오기

        return reviewListRepository.findRecentReviewListsByMentorUuidAndIsDeletedFalse(mentorUuid, pageable)
                .stream()
                .map(ReviewListResponseDto::from)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReviewerProfileImageResponseDto> getProfileImageUrlsByMentoringUuid(String mentoringUuid) {
        Pageable pageable = PageRequest.of(0, 4);// 첫 4개 리뷰만 가져오기

        return reviewListRepository.findProfileImageUrlsByMentoringUuidAndIsDeletedFalse(mentoringUuid, pageable)
                .stream()
                .map(ReviewerProfileImageResponseDto::from)
                .collect(Collectors.toList());

    }

}
