package com.multitap.reviewQuery.reviewQuery.kafka.messageIn;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor
public class NickNameDto {

    private String memberUuid;
    private String nickName;

    @Builder
    public NickNameDto(String memberUuid, String nickName) {
        this.memberUuid = memberUuid;
        this.nickName = nickName;
    }
}
