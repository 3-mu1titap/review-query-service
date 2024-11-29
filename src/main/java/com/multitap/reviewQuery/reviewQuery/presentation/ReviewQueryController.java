package com.multitap.reviewQuery.reviewQuery.presentation;

import com.multitap.reviewQuery.common.response.BaseResponse;
import com.multitap.reviewQuery.reviewQuery.application.ReviewListService;
import com.multitap.reviewQuery.reviewQuery.dto.out.ReviewListResponseDto;
import com.multitap.reviewQuery.reviewQuery.vo.out.ReviewListResponseVo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/reviewList")
public class ReviewQueryController {

    private final ReviewListService reviewListService;

    @Operation(summary = "멘토링별 리뷰 리스트 조회", description = "특정 멘토링에 작성된 리뷰 리스트를 조회합니다.")
    @GetMapping("/review-list/{mentoringUuid}")
    public BaseResponse<Page<ReviewListResponseVo>> getReviewListByMentoring(
            @PathVariable String mentoringUuid,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return new BaseResponse<>(reviewListService.getReviewListByMentoringUuid(mentoringUuid, page, size)
                .map(ReviewListResponseDto::toVo));

    }

    @Operation(summary = "멘토별 리뷰 리스트 조회", description = "특정 멘토에 대하여 작성된 리뷰 리스트를 조회합니다.")
    @GetMapping("/review-list/mentor")
    public BaseResponse<Page<ReviewListResponseVo>> getReviewListByMentorUuid(
            @RequestHeader ("userUuid") String mentorUuid,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return new BaseResponse<>(reviewListService.getReviewListByMentorUuid(mentorUuid, page, size)
                .map(ReviewListResponseDto::toVo));

    }

    @Operation(summary = "멘티별 리뷰 리스트 조회", description = "특정 멘티에 대하여 작성된 리뷰 리스트를 조회합니다.")
    @GetMapping("/review-list/mentee")
    public BaseResponse<Page<ReviewListResponseVo>> getReviewListByMenteeUuid(
            @RequestHeader ("userUuid") String menteeUuid,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return new BaseResponse<>(reviewListService.getReviewListByMenteeUuid(menteeUuid, page, size)
                .map(ReviewListResponseDto::toVo));

    }

    @Operation(summary = "멘토별 리뷰 개수 조회", description = "특정 멘토에 대하여 작성된 리뷰의 개수를 조회합니다.")
    @GetMapping("/count/review")
    public BaseResponse<Long> countReviewByMentorUuid(@RequestHeader ("userUuid") String mentorUuid) {

        return new BaseResponse<>(reviewListService.countReviewByMentorUuid(mentorUuid));

    }
}
