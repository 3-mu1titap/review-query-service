package com.multitap.reviewQuery.reviewQuery.infrastructure;

import com.multitap.reviewQuery.reviewQuery.entity.ReviewList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewListRepository extends MongoRepository<ReviewList, String> {

    @Query(value = "{ 'reviewInfo.menteeUuid': ?0 }")
    List<ReviewList> findAllByMenteeUuid(String menteeUuid);

    @Query(value = "{ 'reviewInfo.mentoringUuid': ?0 }")
    Page<ReviewList> findByMentoringUuid(String mentoringUuid, Pageable pageable);

    @Query(value = "{ 'mentorUuid': ?0 }")
    Page<ReviewList> findByMentorUuid(String mentorUuid, Pageable pageable);

    @Query(value = "{ 'reviewInfo.menteeUuid': ?0 }")
    Page<ReviewList> findByMenteeUuid(String menteeUuid, Pageable pageable);
}
