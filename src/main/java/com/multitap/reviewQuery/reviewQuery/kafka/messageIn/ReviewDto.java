package com.multitap.reviewQuery.reviewQuery.kafka.messageIn;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@Getter
@NoArgsConstructor
public class ReviewDto {

    private String reviewCode;
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
    public ReviewDto(String reviewCode, String reviewTitle, String reviewComment, String menteeUuid, String mentorUuid, String mentoringUuid, String mentoringSessionUuid, int score, boolean isDeleted, LocalDateTime wroteAt) {
        this.reviewCode = reviewCode;
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
}
