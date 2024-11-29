package com.multitap.reviewQuery.reviewQuery.feignClient.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class ProfileImageNickNameVo {

    private String nickName;
    private String profileImageUrl;
}
