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

    @Query(value = "{ 'reviewInfo.mentoringUuid': ?0, 'reviewInfo.isDeleted': false }")
    Page<ReviewList> findByMentoringUuidAndIsDeletedFalse(String mentoringUuid, Pageable pageable);

    @Query(value = "{ 'mentorUuid': ?0, 'reviewInfo.isDeleted': false }")
    Page<ReviewList> findByMentorUuidAndIsDeletedFalse(String mentorUuid, Pageable pageable);

    @Query(value = "{ 'reviewInfo.menteeUuid': ?0, 'reviewInfo.isDeleted': false }")
    Page<ReviewList> findByMenteeUuidAndIsDeletedFalse(String menteeUuid, Pageable pageable);

    @Query(value = "{ 'mentorUuid': ?0, 'reviewInfo.isDeleted': false }", count = true)
    Long countByMentorUuidAndIsDeletedFalse(String mentorUuid);

    @Query(value = "{ 'reviewInfo.mentoringUuid': ?0, 'reviewInfo.isDeleted': false }",
            sort = "{ 'reviewInfo.score': -1, 'reviewInfo.wroteAt': -1 }")
    List<ReviewList> findTopReviewListsByMentoringUuidAndIsDeletedFalse(String mentoringUuid, Pageable pageable);

    @Query(value = "{ 'mentorUuid': ?0, 'reviewInfo.isDeleted': false }",
            sort = "{ 'reviewInfo.wroteAt': -1 }")
    List<ReviewList> findRecentReviewListsByMentorUuidAndIsDeletedFalse(String mentorUuid, Pageable pageable);

    @Query(value = "{ 'reviewInfo.mentoringUuid': ?0, 'reviewInfo.isDeleted': false }",
            sort = "{ 'reviewInfo.wroteAt': -1 }")
    List<ReviewList> findProfileImageUrlsByMentoringUuidAndIsDeletedFalse(String mentoringUuid, Pageable pageable);

}
