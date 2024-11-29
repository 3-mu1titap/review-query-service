package com.multitap.reviewQuery.reviewQuery.kafka;

import com.multitap.reviewQuery.reviewQuery.dto.in.MemberRequestDto;
import com.multitap.reviewQuery.reviewQuery.dto.in.ReviewRequestDto;
import com.multitap.reviewQuery.reviewQuery.entity.ReviewList;
import com.multitap.reviewQuery.reviewQuery.feignClient.MemberServiceFeignClient;
import com.multitap.reviewQuery.reviewQuery.feignClient.MentoringServiceFeignClient;
import com.multitap.reviewQuery.reviewQuery.feignClient.vo.ProfileImageNickNameVo;
import com.multitap.reviewQuery.reviewQuery.infrastructure.ReviewListRepository;
import com.multitap.reviewQuery.reviewQuery.kafka.messageIn.NickNameDto;
import com.multitap.reviewQuery.reviewQuery.kafka.messageIn.ProfileImageDto;
import com.multitap.reviewQuery.reviewQuery.kafka.messageIn.ReviewDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumerServiceImpl implements KafkaConsumerService {

    private final ReviewListRepository reviewListRepository;
    private final MentoringServiceFeignClient mentoringServiceFeignClient;
    private final MemberServiceFeignClient memberServiceFeignClient;

    @Override
    public void addOrUpdateReview(ReviewRequestDto reviewRequestDto, String reviewCode) {
        // 1. reviewCode로 기존 데이터 조회
        Optional<ReviewList> existingReview = reviewListRepository.findById(reviewCode);

        log.info("ReviewRequestDto {}", reviewRequestDto);

        // 2. 새 엔티티 생성 (기존 데이터가 있으면 ID 유지)
        ReviewList reviewList = existingReview
                .map(review -> ReviewList.builder()
                        .id(review.getId()) // 기존 ID 유지
                        .reviewInfo(reviewRequestDto) // 새로운 ReviewInfo로 대체
                        .memberInfo(review.getMemberInfo()) // 필요시 기존 데이터 유지
                        .mentorUuid(review.getMentorUuid()) // 필요시 기존 데이터 유지
                        .build())
                .orElseGet(() -> {
                    String mentorUuid = mentoringServiceFeignClient.getMentorUuidBySessionUuid(reviewRequestDto.getMentoringSessionUuid());
                    log.info("memtorUuid: {}", mentorUuid);
                    MemberRequestDto memberRequestDto = MemberRequestDto.from(memberServiceFeignClient.getMemberProfileImageForReview(reviewRequestDto.getMenteeUuid()));
                    log.info("memberRequestDto: {}", memberRequestDto);
                    return reviewRequestDto.toEntity(reviewRequestDto, memberRequestDto, mentorUuid, reviewCode);
                }); // 없으면 새로 생성

        log.info("reviewList {}", reviewList);

        // 3. 저장 (기존 ID면 업데이트, 없으면 새로 저장)
        reviewListRepository.save(reviewList);
    }

    @Override
    public void updateNickName(NickNameDto nickNameDto) {
        List<ReviewList> existingReview = reviewListRepository.findAllByMenteeUuid(nickNameDto.getMemberUuid());

        if (!existingReview.isEmpty()) {
            throw new IllegalArgumentException("해당 menteeUuid로 등록한 리뷰가 없습니다.");
        }

        List<ReviewList> reviewLists = existingReview.stream()
                .map(review -> ReviewList.builder()
                        .id(review.getId()) // 기존 ID 유지
                        .reviewInfo(review.getReviewInfo()) // 새로운 ReviewInfo로 대체
                        .memberInfo(MemberRequestDto.of(nickNameDto.getNickName(), review.getMemberInfo().getProfileImageUrl())) // 필요시 기존 데이터 유지
                        .mentorUuid(review.getMentorUuid()) // 필요시 기존 데이터 유지
                        .build())
                .toList();

        reviewListRepository.saveAll(reviewLists);
    }

    @Override
    public void updateProfileImageUrl(ProfileImageDto profileImageDto) {
        List<ReviewList> existingReview = reviewListRepository.findAllByMenteeUuid(profileImageDto.getMemberUuid());

        if (!existingReview.isEmpty()) {
            throw new IllegalArgumentException("해당 menteeUuid로 등록한 리뷰가 없습니다.");
        }

        List<ReviewList> reviewLists = existingReview.stream()
                .map(review -> ReviewList.builder()
                        .id(review.getId()) // 기존 ID 유지
                        .reviewInfo(review.getReviewInfo()) // 새로운 ReviewInfo로 대체
                        .memberInfo(MemberRequestDto.of(review.getMemberInfo().getNickName(), profileImageDto.getProfileImageUrl())) // 필요시 기존 데이터 유지
                        .mentorUuid(review.getMentorUuid()) // 필요시 기존 데이터 유지
                        .build())
                .toList();

        reviewListRepository.saveAll(reviewLists);
    }

}
