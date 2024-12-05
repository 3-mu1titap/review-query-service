package com.multitap.reviewQuery.reviewQuery.kafka.consumer;

import com.multitap.reviewQuery.reviewQuery.dto.in.ReviewRequestDto;
import com.multitap.reviewQuery.reviewQuery.kafka.messageIn.NickNameDto;
import com.multitap.reviewQuery.reviewQuery.kafka.messageIn.ProfileImageDto;

public interface KafkaConsumerService {
    void addOrUpdateReview(ReviewRequestDto reviewRequestDto, String reviewCode);
    void updateNickName(NickNameDto nickNameDto);
    void updateProfileImageUrl(ProfileImageDto profileImageDto);
}
