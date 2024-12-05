package com.multitap.reviewQuery.reviewQuery.entity;

import com.multitap.reviewQuery.reviewQuery.dto.in.MemberRequestDto;
import com.multitap.reviewQuery.reviewQuery.dto.in.ReviewRequestDto;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@ToString
@Document
@Data
@Builder
public class ReviewList {

    @Id
    private String id;
    private ReviewRequestDto reviewInfo;
    private MemberRequestDto memberInfo;
}
