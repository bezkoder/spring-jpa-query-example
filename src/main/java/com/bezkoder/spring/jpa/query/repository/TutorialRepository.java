package com.bezkoder.spring.jpa.query.repository;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import com.bezkoder.spring.jpa.query.model.Tutorial;

public interface TutorialRepository extends JpaRepository<Tutorial, Long> {

  @Query("SELECT t FROM Tutorial t")
  List<Tutorial> findAll();
  
  @Query("SELECT t FROM Tutorial t WHERE t.published=?1")
  List<Tutorial> findByPublished(boolean isPublished);

  @Query("SELECT t FROM Tutorial t WHERE t.title LIKE %?1%")
  List<Tutorial> findByTitleLike(String title);
  
  @Query("SELECT t FROM Tutorial t WHERE LOWER(t.title) LIKE LOWER(CONCAT('%', ?1,'%'))")
  List<Tutorial> findByTitleLikeCaseInsensitive(String title);
  
  @Transactional
  @Modifying
  @Query("UPDATE Tutorial t SET t.published=true WHERE t.id=?1")
  int publishTutorial(Long id);
  
  @Query("SELECT t FROM Tutorial t WHERE t.level >= ?1")
  List<Tutorial> findByLevelGreaterThanEqual(int level);
  
  @Query("SELECT t FROM Tutorial t WHERE t.createdAt >= ?1")
  List<Tutorial> findByDateGreaterThanEqual(Date date);
  
  @Query("SELECT t FROM Tutorial t WHERE t.level BETWEEN ?1 AND ?2")
  List<Tutorial> findByLevelBetween(int start, int end);

  @Query("SELECT t FROM Tutorial t WHERE t.published=:isPublished AND t.level BETWEEN :start AND :end")
  List<Tutorial> findByLevelBetween(@Param("start") int start, @Param("end") int end, @Param("isPublished") boolean isPublished);
  
  @Query("SELECT t FROM Tutorial t WHERE t.createdAt BETWEEN ?1 AND ?2")
  List<Tutorial> findByDateBetween(Date start, Date end);
  
  @Query("SELECT t FROM Tutorial t ORDER BY t.level DESC")
  List<Tutorial> findAllOrderByLevelDesc();
  
  @Query("SELECT t FROM Tutorial t WHERE LOWER(t.title) LIKE LOWER(CONCAT('%', ?1,'%')) ORDER BY t.level ASC")
  List<Tutorial> findByTitleOrderByLevelAsc(String title);

  @Query("SELECT t FROM Tutorial t WHERE t.published=true ORDER BY t.createdAt DESC")
  List<Tutorial> findAllPublishedOrderByCreatedDesc();
  
  @Query("SELECT t FROM Tutorial t WHERE LOWER(t.title) LIKE LOWER(CONCAT('%', ?1,'%'))")
  List<Tutorial> findByTitleAndSort(String title, Sort sort);
  
  @Query("SELECT t FROM Tutorial t WHERE t.published=?1")
  List<Tutorial> findByPublishedAndSort(boolean isPublished, Sort sort);
  
  // Pagination and Sorting with Pageable
  @Query("SELECT t FROM Tutorial t")
  Page<Tutorial> findAllWithPagination(Pageable pageable);

  @Query("SELECT t FROM Tutorial t WHERE t.published=?1")
  Page<Tutorial> findByPublishedWithPagination(boolean isPublished, Pageable pageable);
  
  @Query("SELECT t FROM Tutorial t WHERE LOWER(t.title) LIKE LOWER(CONCAT('%', ?1,'%'))")
  Page<Tutorial> findByTitleWithPagination(String title, Pageable pageable);
}