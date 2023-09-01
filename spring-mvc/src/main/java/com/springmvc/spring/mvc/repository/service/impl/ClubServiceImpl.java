package com.springmvc.spring.mvc.repository.service.impl;

import com.springmvc.spring.mvc.dto.ClubDto;
import com.springmvc.spring.mvc.models.User;
import com.springmvc.spring.mvc.models.club;
import com.springmvc.spring.mvc.repository.ClubRepository;
import com.springmvc.spring.mvc.repository.UserRepository;
import com.springmvc.spring.mvc.repository.service.ClubService;
import com.springmvc.spring.mvc.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.springmvc.spring.mvc.mapper.ClubMapper.mapToClub;
import static com.springmvc.spring.mvc.mapper.ClubMapper.mapToClubDto;

@Service
public class ClubServiceImpl implements ClubService {
    private ClubRepository clubRepository;
    private UserRepository userRepository;

    @Autowired
    public ClubServiceImpl(ClubRepository clubRepository,UserRepository userRepository) {
         this.clubRepository=clubRepository;
         this.userRepository=userRepository;
    }

    @Override
    public List<ClubDto> findAllClubs() {
       List<club> clubs= clubRepository.findAll();
        return clubs.stream().map((club) ->mapToClubDto(club)).collect(Collectors.toList());
    }

    @Override
    public club saveClub(club club) {
        String username= SecurityUtil.getSessionUser();
        User user=userRepository.findByUsername(username);
        club.setCreatedBy(user);
        return clubRepository.save(club);
    }

    @Override
    public ClubDto findClubById(int clubid) {
        club club=clubRepository.findById((long) clubid).get();
        return mapToClubDto(club);
    }

    @Override
    public void updateClub(ClubDto clubDto) {
        String username=SecurityUtil.getSessionUser();
        User user=userRepository.findByUsername(username);
        club club=mapToClub(clubDto);
        club.setCreatedBy(user);
        clubRepository.save(club);
    }

    @Override
    public void delete(long clubId) {
        clubRepository.deleteById(clubId);
    }

    @Override
    public List<ClubDto> searchClubs(String query) {
       List<club> clubs= clubRepository.searchClubs(query);
       return clubs.stream().map(club -> mapToClubDto(club)).collect(Collectors.toList());
    }
}
