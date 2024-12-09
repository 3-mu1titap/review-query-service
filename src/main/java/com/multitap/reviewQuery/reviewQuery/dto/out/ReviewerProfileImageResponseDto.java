package com.multitap.reviewQuery.reviewQuery.dto.out;

import com.multitap.reviewQuery.reviewQuery.entity.ReviewList;
import com.multitap.reviewQuery.reviewQuery.vo.out.ReviewerProfileImageResponseVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReviewerProfileImageResponseDto {
    private String nickName;
    private String profileImageUrl;

    @Builder
    public ReviewerProfileImageResponseDto(String nickName, String profileImageUrl) {
        this.nickName = nickName;
        this.profileImageUrl = profileImageUrl;
    }

    public static ReviewerProfileImageResponseDto from(ReviewList reviewList) {
        return ReviewerProfileImageResponseDto.builder()
                .nickName(reviewList.getMemberInfo().getNickName())
                .profileImageUrl(reviewList.getMemberInfo().getProfileImageUrl())
                .build();
    }

    public ReviewerProfileImageResponseVo toVo() {
        return ReviewerProfileImageResponseVo.builder()
                .nickname(nickName)
                .profileImageUrl(profileImageUrl)
                .build();
    }
}
