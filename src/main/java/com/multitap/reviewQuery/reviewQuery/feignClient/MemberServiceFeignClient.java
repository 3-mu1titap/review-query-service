package com.multitap.reviewQuery.reviewQuery.feignClient;

import com.multitap.reviewQuery.reviewQuery.feignClient.vo.ProfileImageNickNameVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

//@FeignClient(url = "http://localhost:8083", name = "member-query-service")
@FeignClient(url = "http://api.adaptors.site/member-query-service", name = "member-query-service")
public interface MemberServiceFeignClient {

    // mentoring-query-service 의 멘토 uuid 조회 api 호출
    @GetMapping("/api/v1/memberInfo/profileImage-review")
    ProfileImageNickNameVo getMemberProfileImageForReview(@RequestHeader(name = "userUuid") String userUuid);
}
