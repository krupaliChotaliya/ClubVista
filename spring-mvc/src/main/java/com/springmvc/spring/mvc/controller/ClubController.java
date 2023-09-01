package com.springmvc.spring.mvc.controller;

import com.springmvc.spring.mvc.models.User;
import com.springmvc.spring.mvc.models.club;
import com.springmvc.spring.mvc.repository.service.UserService;
import com.springmvc.spring.mvc.security.SecurityUtil;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import com.springmvc.spring.mvc.dto.ClubDto;
import com.springmvc.spring.mvc.repository.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ClubController {
    private ClubService clubService;
    private UserService userService;

    @Autowired
    public ClubController(ClubService clubService,UserService userService) {

        this.userService=userService;
        this.clubService = clubService;
    }

    @GetMapping("/clubs")
    public String listClubs(Model model) throws Exception{
        User user=new User();
        List<ClubDto> clubs= clubService.findAllClubs();
        String username= SecurityUtil.getSessionUser();
        if(username !=null){
            user=userService.findByUsername(username);
        }
        model.addAttribute("user",user);
        model.addAttribute("clubs",clubs);
        return "clubs-list";
    }

    @GetMapping("clubs/new")
    public String creteClubForm(Model model){
        club club=new club();
        model.addAttribute("club",club);
        return "club-create";
    }

    @PostMapping ("clubs/new")
    public String saveClub(@Valid @ModelAttribute("club") club club,BindingResult result,Model model){
        if(result.hasErrors()){
            model.addAttribute("club",club);
            return "club-create";
        }
        clubService.saveClub(club);
        return "redirect:/clubs";
    }

    @GetMapping("clubs/{clubId}/edit")
    public String editClubForm(@PathVariable("clubId") int clubId,Model model){
        ClubDto clubDto=clubService.findClubById(clubId);
        model.addAttribute("club",clubDto);
        return "clubs-edit";
    }

    @PostMapping("clubs/{clubId}/edit")
    public String updateClub(@PathVariable("clubId") int clubId,
                             @Valid @ModelAttribute("club")ClubDto clubDto,
                             BindingResult result){
        if(result.hasErrors()){
            return "clubs-edit";
        }
        clubDto.setId(clubId);
        clubService.updateClub(clubDto);
        return "redirect:/clubs";
    }

    @GetMapping("clubs/{clubId}")
    public String clubDetail(@PathVariable("clubId")long clubId,Model model){
        User user=new User();
        ClubDto clubDto=clubService.findClubById((int) clubId);
        String username= SecurityUtil.getSessionUser();
        if(username !=null){
            user=userService.findByUsername(username);
        }
        model.addAttribute("user",user);
        model.addAttribute("club",clubDto);
        return "club-details";
    }

    @GetMapping("clubs/{clubId}/delete")
    public String deleteClub(@PathVariable("clubId") long clubId,Model model){
        clubService.delete(clubId);
        return "redirect:/clubs";
    }

    @GetMapping("/clubs/search")
    public String searchClub(@RequestParam(value = "query")String query,Model model){
        List<ClubDto> clubs=clubService.searchClubs(query);
        model.addAttribute("clubs",clubs);
        return "clubs-list";
    }

}
