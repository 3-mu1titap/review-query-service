package com.multitap.reviewQuery.reviewQuery.vo.out;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.multitap.reviewQuery.reviewQuery.dto.in.MemberRequestDto;
import com.multitap.reviewQuery.reviewQuery.dto.in.ReviewRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReviewListResponseVo {

    private String id;
    private String mentorUuid;
    private ReviewRequestDto reviewRequestDto;
    private MemberRequestDto memberRequestDto;
}
