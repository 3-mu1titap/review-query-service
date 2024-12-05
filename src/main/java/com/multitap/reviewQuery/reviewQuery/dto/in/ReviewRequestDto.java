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
    private String mentorUuid;
    private String mentoringUuid;
    private String mentoringSessionUuid;
    private int score;
    private boolean isDeleted;
    private LocalDateTime wroteAt;

    @Builder
    public ReviewRequestDto(String reviewTitle, String reviewComment, String menteeUuid, String mentorUuid, String mentoringUuid, String mentoringSessionUuid, int score, boolean isDeleted, LocalDateTime wroteAt) {
        this.reviewTitle = reviewTitle;
        this.reviewComment = reviewComment;
        this.menteeUuid = menteeUuid;
        this.mentorUuid = mentorUuid;
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
                .mentorUuid(reviewDto.getMentorUuid())
                .mentoringUuid(reviewDto.getMentoringUuid())
                .mentoringSessionUuid(reviewDto.getMentoringSessionUuid())
                .score(reviewDto.getScore())
                .isDeleted(reviewDto.isDeleted())
                .wroteAt(reviewDto.getWroteAt())
                .build();
    }

    public ReviewList toEntity(ReviewRequestDto reviewRequestDto, MemberRequestDto memberRequestDto, String reviewCode) {
        return ReviewList.builder()
                .id(reviewCode)
                .reviewInfo(reviewRequestDto)
                .memberInfo(memberRequestDto)
                .build();
    }
}
