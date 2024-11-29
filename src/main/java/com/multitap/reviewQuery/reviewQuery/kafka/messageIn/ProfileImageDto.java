package com.multitap.reviewQuery.reviewQuery.kafka.messageIn;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor
public class ProfileImageDto {

    private String memberUuid;
    private String profileImageUrl;

    @Builder
    public ProfileImageDto(String memberUuid, String profileImageUrl) {
        this.memberUuid = memberUuid;
        this.profileImageUrl = profileImageUrl;
    }
}
