package com.nchu.club.controller;

import com.nchu.club.service.ClubService;
import com.nchu.club.service.UserService;
import com.nchu.club.vo.ClubTableVo;
import com.nchu.club.vo.ClubVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/club")
public class ClubController {

    @Autowired
    private ClubService clubService;
    @Autowired
    private UserService userService;

    @RequestMapping("/get_club")
    @ResponseBody
    public ClubTableVo getAllClubVo(Integer page, Integer limit) {
        ClubTableVo clubTableVo = new ClubTableVo();
        clubTableVo.setData(clubService.getAllClub(page,limit));
        return clubTableVo;
    }

    @RequestMapping("/edit_club")
    public ModelAndView setClubVoToEdit(Integer cid) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("clubUser",userService.getClubUser(cid));
        mv.setViewName("/manager/edit");
        return mv;
    }

}
