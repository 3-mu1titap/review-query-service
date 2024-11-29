package com.multitap.reviewQuery.reviewQuery.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "http://localhost:9001", name = "mentoring-query-service")
public interface MentoringServiceFeignClient {

    // mentoring-query-service 의 멘토 uuid 조회 api 호출
    @GetMapping("/api/v1/mentoring-query-service/mentor-info/{sessionUuid}")
    String getMentorUuidBySessionUuid(@PathVariable(name = "sessionUuid") String sessionUuid);
}
