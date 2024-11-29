package com.multitap.reviewQuery.reviewQuery.dto.in;

import com.multitap.reviewQuery.reviewQuery.entity.ReviewList;
import com.multitap.reviewQuery.reviewQuery.kafka.messageIn.ReviewDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ReviewRequestDto {

    private String reviewTitle;
    private String reviewComment;
    private String menteeUuid;
    private String mentoringUuid;
    private String mentoringSessionUuid;
    private int score;
    private boolean isDeleted;
    private LocalDateTime wroteAt;

    @Builder
    public ReviewRequestDto(String reviewTitle, String reviewComment, String menteeUuid, String mentoringUuid, String mentoringSessionUuid, int score, boolean isDeleted, LocalDateTime wroteAt) {
        this.reviewTitle = reviewTitle;
        this.reviewComment = reviewComment;
        this.menteeUuid = menteeUuid;
        this.mentoringUuid = mentoringUuid;
        this.mentoringSessionUuid = mentoringSessionUuid;
        this.score = score;
        this.isDeleted = isDeleted;
        this.wroteAt = wroteAt;
    }

    public static ReviewRequestDto from(ReviewDto reviewDto) {
        return ReviewRequestDto.builder()
                .reviewTitle(reviewDto.getReviewTitle())
                .reviewComment(reviewDto.getReviewComment())
                .menteeUuid(reviewDto.getMenteeUuid())
                .mentoringUuid(reviewDto.getMentoringUuid())
                .mentoringSessionUuid(reviewDto.getMentoringSessionUuid())
                .score(reviewDto.getScore())
                .isDeleted(reviewDto.isDeleted())
                .wroteAt(reviewDto.getWroteAt())
                .build();
    }

    public ReviewList toEntity(ReviewRequestDto reviewRequestDto, MemberRequestDto memberRequestDto, String menteeUuid, String reviewCode) {
        return ReviewList.builder()
                .id(reviewCode)
                .mentorUuid(menteeUuid)
                .reviewInfo(reviewRequestDto)
                .memberInfo(memberRequestDto)
                .build();
    }
}
