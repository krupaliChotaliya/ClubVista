package com.springmvc.spring.mvc.repository.service;

import com.springmvc.spring.mvc.dto.ClubDto;
import com.springmvc.spring.mvc.models.club;

import java.util.List;

public interface ClubService {
    List<ClubDto> findAllClubs();
    club saveClub(club club);
    ClubDto findClubById(int clubid);
    void updateClub(ClubDto clubDto);
    void delete(long clubId);
    List<ClubDto> searchClubs(String query);
}
