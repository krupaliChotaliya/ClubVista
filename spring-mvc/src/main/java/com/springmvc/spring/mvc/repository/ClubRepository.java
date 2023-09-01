package com.springmvc.spring.mvc.repository;

import com.springmvc.spring.mvc.models.club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ClubRepository  extends JpaRepository<club,Long> {

//    Optional<club> findByTitle(String url);

    @Query("select c from club c where c.title like concat('%',:query,'%') ")
    List<club> searchClubs(String query);
}
