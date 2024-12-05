package com.multitap.reviewQuery.reviewQuery.dto.out;

import com.multitap.reviewQuery.reviewQuery.dto.in.MemberRequestDto;
import com.multitap.reviewQuery.reviewQuery.dto.in.ReviewRequestDto;
import com.multitap.reviewQuery.reviewQuery.entity.ReviewList;
import com.multitap.reviewQuery.reviewQuery.vo.out.ReviewListResponseVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReviewListResponseDto {

    private String id;
    private ReviewRequestDto reviewRequestDto;
    private MemberRequestDto memberRequestDto;

    @Builder
    public ReviewListResponseDto(String id, ReviewRequestDto reviewRequestDto, MemberRequestDto memberRequestDto) {
        this.id = id;
        this.reviewRequestDto = reviewRequestDto;
        this.memberRequestDto = memberRequestDto;
    }

    public static ReviewListResponseDto from(ReviewList reviewList) {
        return ReviewListResponseDto.builder()
                .id(reviewList.getId())
                .reviewRequestDto(reviewList.getReviewInfo())
                .memberRequestDto(reviewList.getMemberInfo())
                .build();
    }

    public ReviewListResponseVo toVo() {
        return ReviewListResponseVo.builder()
                .id(id)
                .reviewRequestDto(reviewRequestDto)
                .memberRequestDto(memberRequestDto)
                .build();
    }
}
