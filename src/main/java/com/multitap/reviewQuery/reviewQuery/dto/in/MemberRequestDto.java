package com.multitap.reviewQuery.reviewQuery.dto.in;

import com.multitap.reviewQuery.reviewQuery.entity.ReviewList;
import com.multitap.reviewQuery.reviewQuery.feignClient.vo.ProfileImageNickNameVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor
public class MemberRequestDto {

    private String nickName;
    private String profileImageUrl;

    @Builder
    public MemberRequestDto(String nickName, String profileImageUrl) {
        this.nickName = nickName;
        this.profileImageUrl = profileImageUrl;
    }

    public static MemberRequestDto from(ProfileImageNickNameVo profileImageNickNameVo) {
        return MemberRequestDto.builder()
                .nickName(profileImageNickNameVo.getNickName())
                .profileImageUrl(profileImageNickNameVo.getProfileImageUrl())
                .build();
    }

    public static MemberRequestDto of(String nickName, String profileImageUrl) {
        return MemberRequestDto.builder()
                .nickName(nickName)
                .profileImageUrl(profileImageUrl)
                .build();
    }

}
