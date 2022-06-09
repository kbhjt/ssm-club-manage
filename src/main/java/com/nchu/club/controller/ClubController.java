package com.nchu.club.controller;

import com.nchu.club.domain.*;
import com.nchu.club.service.ApplyClubService;
import com.nchu.club.service.ClubService;
import com.nchu.club.service.RoleService;
import com.nchu.club.service.UserService;
import com.nchu.club.tablevo.CMessageTableVo;
import com.nchu.club.tablevo.ClubTableVo;
import com.nchu.club.vo.CMessageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.*;

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
        ApplyClub applyClub = applyClubService.getOneApply(uid,cid);

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
        if (applyClub != null && applyClub.getIsOut().equals("申请助理")) {
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

    @RequestMapping("/addCTag")
    @ResponseBody
    public int addCTag(String cid, String ctag){
        CTag cTag = new CTag();
        cTag.setCid(Integer.parseInt(cid));
        cTag.setCtag(ctag);

        return clubService.addCTag(cTag);
    }

    @RequestMapping("/getCTagByCid")
    @ResponseBody
    public List<String> getCTagByCid(String cid) {
        String tag = "";
        List<String> list = clubService.getCTagByCid(Integer.parseInt(cid));//获取每一条标签的list
        List<String> tagList = new ArrayList<String>();
        for (int i = 0; i < list.size(); i++) {//把每一条list组合成一条string（便于按，切割）
            tag += list.get(i);
        }
        System.out.println(tag);
        String[] stag = tag.split(",");
        for(int i = 0; i < stag.length; i++){//切割完成后将最后一个空格舍弃，存入list
            tagList.add(stag[i]);
        }
        System.out.println(tagList);
        Map<String, Integer> map = new HashMap<String, Integer>();//使用map统计list元素出现次数
        for (String temp : tagList) {
            Integer count = map.get(temp);
            map.put(temp, (count == null) ? 1 : count + 1);
        }

        tagList.clear();//清空tagList
        Map<String, Integer> sortMap = sortMap(map);//得到降序序列
        //使用set遍历map
        Set<String> set = sortMap.keySet();
        Iterator<String> it = set.iterator();
        int n = 0;
        while (it.hasNext()) {//取出前三名的标签值
            if(n>2){
                break;
            }
            String key = it.next();
            Integer value = map.get(key);
            System.out.println(key);
            tagList.add(key);
            n++;
        }
        System.out.println(tagList);
        return tagList;
    }

    private Map<String, Integer> sortMap(Map<String, Integer> map) {
        //利用Map的entrySet方法，转化为list进行排序
        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(map.entrySet());
        //利用Collections的sort方法对list排序
        Collections.sort(entryList, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                //正序排列，倒序反过来
                return o2.getValue() - o1.getValue();
            }
        });
        //遍历排序好的list，一定要放进LinkedHashMap，因为只有LinkedHashMap是根据插入顺序进行存储
        LinkedHashMap<String, Integer> linkedHashMap = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String,Integer> e : entryList
        ) {
            linkedHashMap.put(e.getKey(),e.getValue());
        }
        return linkedHashMap;
    }

    @RequestMapping("/get_message")
    @ResponseBody
    public CMessageTableVo getMessage(int page,int limit,int cid) {
        return clubService.getMessageVo(page, limit, cid);
    }

    @RequestMapping("/delete_message")//删除留言
    @ResponseBody
    public int deleteCMessage(String mid) {
        System.out.println(mid);
        return clubService.deleteCMeassge(Integer.parseInt(mid));
    }


}
