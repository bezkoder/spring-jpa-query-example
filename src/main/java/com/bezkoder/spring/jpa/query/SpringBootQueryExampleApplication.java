package com.bezkoder.spring.jpa.query;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.bezkoder.spring.jpa.query.model.Tutorial;
import com.bezkoder.spring.jpa.query.repository.TutorialRepository;

@SpringBootApplication
public class SpringBootQueryExampleApplication implements CommandLineRunner {

  @Autowired
  TutorialRepository tutorialRepository;

  public static void main(String[] args) {
    SpringApplication.run(SpringBootQueryExampleApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    tutorialRepository.deleteAll();

    Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse("2022-03-11");
    Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse("2022-04-26");
    Date date3 = new SimpleDateFormat("yyyy-MM-dd").parse("2022-05-19");

    tutorialRepository.save(new Tutorial("Spring Data", "Tut#1 Description", 3, false, date1));
    tutorialRepository.save(new Tutorial("Java Spring", "Tut#2 Description", 1, false, date1));
    tutorialRepository.save(new Tutorial("Hibernate", "Tut#3 Description", 3, false, date2));
    tutorialRepository.save(new Tutorial("Spring Boot", "Tut#4 Description", 2, false, date2));
    tutorialRepository.save(new Tutorial("Spring Data JPA", "Tut#5 Description", 3, false, date3));
    tutorialRepository.save(new Tutorial("Spring Batch", "Tut#6 Description", 4, false, date3));
    tutorialRepository.save(new Tutorial("Spring Security", "Tut#7 Description", 5, false, date3));

    List<Tutorial> tutorials = new ArrayList<>();

    tutorials = tutorialRepository.findAll();
    show(tutorials);

    tutorialRepository.publishTutorial(tutorials.get(0).getId());
    tutorialRepository.publishTutorial(tutorials.get(2).getId());
    tutorialRepository.publishTutorial(tutorials.get(4).getId());

    tutorials = tutorialRepository.findByPublished(true);
    show(tutorials);

    tutorials = tutorialRepository.findByTitleLike("ata");
    show(tutorials);

    tutorials = tutorialRepository.findByTitleLikeCaseInsensitive("dat");
    show(tutorials);

    tutorials = tutorialRepository.findByLevelGreaterThanEqual(3);
    show(tutorials);

    Date myDate = new SimpleDateFormat("yyyy-MM-dd").parse("2022-05-11");

    tutorials = tutorialRepository.findByDateGreaterThanEqual(myDate);
    show(tutorials);

    tutorials = tutorialRepository.findByLevelBetween(3, 5);
    show(tutorials);
    
    tutorials = tutorialRepository.findByLevelBetween(3, 5, true);
    show(tutorials);

    Date myDate1 = new SimpleDateFormat("yyyy-MM-dd").parse("2022-04-11");
    Date myDate2 = new SimpleDateFormat("yyyy-MM-dd").parse("2022-05-11");

    tutorials = tutorialRepository.findByDateBetween(myDate1, myDate2);
    show(tutorials);
    
    tutorials = tutorialRepository.findAllOrderByLevelDesc();
    show(tutorials);

    tutorials = tutorialRepository.findByTitleOrderByLevelAsc("at");
    show(tutorials);

    tutorials = tutorialRepository.findAllPublishedOrderByCreatedDesc();
    show(tutorials);

    tutorials = tutorialRepository.findByTitleAndSort("at", Sort.by("level").descending());
    show(tutorials);

    tutorials = tutorialRepository.findByTitleAndSort("at", Sort.by("createdAt").descending());
    show(tutorials);

    tutorials = tutorialRepository.findByPublishedAndSort(false, Sort.by("level").descending());
    show(tutorials);

    int page = 0;
    int size = 3;

    Pageable pageable = PageRequest.of(page, size);
    tutorials = tutorialRepository.findAllWithPagination(pageable).getContent();
    show(tutorials);

    pageable = PageRequest.of(page, size, Sort.by("level").descending());
    tutorials = tutorialRepository.findAllWithPagination(pageable).getContent();
    show(tutorials);

    pageable = PageRequest.of(page, size);   
    tutorials = tutorialRepository.findByTitleWithPagination("ring", pageable).getContent();
    show(tutorials);
    
    pageable = PageRequest.of(page, size, Sort.by("level").descending());
    tutorials = tutorialRepository.findByPublishedWithPagination(false, pageable).getContent();
    show(tutorials);
  }

  private void show(List<Tutorial> tutorials) {
    tutorials.forEach(System.out::println);
  }
}
