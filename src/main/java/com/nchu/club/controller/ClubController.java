package com.nchu.club.controller;

import com.nchu.club.domain.*;
import com.nchu.club.service.ApplyClubService;
import com.nchu.club.service.ClubService;
import com.nchu.club.service.RoleService;
import com.nchu.club.service.UserService;
import com.nchu.club.tablevo.ClubTableVo;
import com.nchu.club.vo.CMessageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/club")
public class ClubController {

    @Autowired
    private ClubService clubService;
    @Autowired
    private UserService userService;
    @Autowired
    private ApplyClubService applyClubService;
    @Autowired
    private RoleService roleService;

    @RequestMapping("/get_club")
    @ResponseBody
    public ClubTableVo getAllClubVo(Integer page, Integer limit) {
        return clubService.getAllClub(page,limit);
    }

    @RequestMapping("/edit_club")
    public ModelAndView setClubVoToEdit(Integer cid) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("clubUser",userService.getClubUser(cid));
        mv.setViewName("/manager/edit");
        return mv;
    }

    @RequestMapping("/getAllClubs")
    @ResponseBody
    public List<Club> getAllClubs() {
        return clubService.selectAllClubs();
    }

    @RequestMapping("/detail")
    public ModelAndView getClubByCid(int cid, int uid) {
        ModelAndView mv = new ModelAndView();
        Club club = clubService.getClubByCid(cid);
        User user = userService.getUserByUid(club.getCleader());
        List<Club> clubList = clubService.getClubByUid(uid); //用户已加入的社团
        List<Club> examineClub = applyClubService.getExamineClub(uid); //用户还在审核中的社团
        Apply apply = applyClubService.getOneApply(uid,cid);

        if (clubList != null && clubList.size() != 0) {
            for (Club club1 : clubList) {
                if(club1.getCid() == cid) { //用户已经加入了该社团
                    System.out.println(cid);
                    mv.addObject("exist",1);
                    break;
                }
            }
        }else {
            mv.addObject("exist",0);
        }
        if(examineClub != null && examineClub.size() != 0) {
            for (Club examine : examineClub) {
                if(examine.getCid() == cid) {
                    System.out.println("ssk"+examine.getCid());
                    mv.addObject("examine",1);
                    break;
                }
            }
        }else {
            mv.addObject("examine",0);
        }
        if (apply != null && apply.getIsOut().equals("申请助理")) {
            mv.addObject("applyExist",1);
        }
        if (clubService.getIsClubHelper(uid,cid) != null) {
            System.out.println("club"+clubService.getIsClubHelper(uid,cid));
            mv.addObject("applyExist",2);
        }
        mv.addObject("club",club);
        mv.addObject("userName",user.getUname());
        mv.setViewName("/student/club_detail");
        return mv;
    }

    @RequestMapping("/getClubByUid")
    @ResponseBody
    public List<Club> getClubByUid(int uid) {
        return clubService.getClubByUid(uid);
    }

    @RequestMapping("/addCMessage")
    @ResponseBody
    public int addCMessage(String cid,String uid,String cmessage) {
        CMessage cMessage  = new CMessage();
        cMessage.setCid(Integer.parseInt(cid));
        cMessage.setUid(Integer.parseInt(uid));
        cMessage.setCmessage(cmessage);

        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式(年-月-日-时-分-秒)
        String mcreateTime = dateFormat.format(now);
        cMessage.setMcreateTime(mcreateTime);

        return clubService.addCMessage(cMessage);
    }

    @RequestMapping("/getCMessageByCid")
    @ResponseBody
    public List<CMessageVo> getCMessageByCid(String cid) {
        return clubService.getCMessageByCid(Integer.parseInt(cid));
    }

    @RequestMapping("/edit_user")
    @ResponseBody
    public ModelAndView toEditUser(Integer uid) {
        //查找用户信息
        User user = userService.getUserByUid(uid);
        ModelAndView mv = new ModelAndView();
        mv.addObject("editUser",user);
        mv.setViewName("/club_manager/edit_user");
        return mv;
    }
}
