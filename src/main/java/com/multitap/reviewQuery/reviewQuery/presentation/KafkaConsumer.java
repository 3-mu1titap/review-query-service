package com.multitap.reviewQuery.reviewQuery.presentation;

import com.multitap.reviewQuery.reviewQuery.dto.in.ReviewRequestDto;
import com.multitap.reviewQuery.reviewQuery.kafka.consumer.KafkaConsumerService;
import com.multitap.reviewQuery.reviewQuery.kafka.messageIn.NickNameDto;
import com.multitap.reviewQuery.reviewQuery.kafka.messageIn.ProfileImageDto;
import com.multitap.reviewQuery.reviewQuery.kafka.messageIn.ReviewDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Slf4j
@Component
public class KafkaConsumer {

    private final KafkaConsumerService kafkaConsumerService;

    @KafkaListener(topics = "create-review-topic", groupId = "review-consumer-group", containerFactory = "reviewDtoListener")
    public void processReview(ReviewDto reviewDto) {
        log.info("Received review request: {}", reviewDto);
        kafkaConsumerService.addOrUpdateReview(ReviewRequestDto.from(reviewDto), reviewDto.getReviewCode());
    }

    @KafkaListener(topics = "review-profile-nickname-topic", groupId = "review-consumer-group", containerFactory = "nickNameDtoListener")
    public void processNickName(NickNameDto nickNameDto) {
        log.info("Received nickname request: {}", nickNameDto);
        kafkaConsumerService.updateNickName(nickNameDto);
    }

    @KafkaListener(topics = "review-profile-image-topic", groupId = "review-consumer-group", containerFactory = "profileImageDtoListener")
    public void processProfileImage(ProfileImageDto profileImageDto) {
        log.info("Received profile image request: {}", profileImageDto);
        kafkaConsumerService.updateProfileImageUrl(profileImageDto);
    }
}
