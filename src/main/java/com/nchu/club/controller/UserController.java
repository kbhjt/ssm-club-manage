package com.nchu.club.controller;

import com.nchu.club.domain.Club;
import com.nchu.club.domain.Role;
import com.nchu.club.domain.User;
import com.nchu.club.service.ClubService;
import com.nchu.club.service.RoleService;
import com.nchu.club.service.UserService;
import com.nchu.club.utils.Md5Util;
import com.nchu.club.utils.SendmailUtil;
import com.nchu.club.vo.ClubUserTableVo;
import com.nchu.club.vo.ClubUserVo;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import sun.security.provider.MD5;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.*;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private ClubService clubService;
    @Autowired
    private RoleService roleService;

    //登录
    @RequestMapping("/login")
    @ResponseBody
    public String login(String username, String password, String utype, HttpServletRequest request) {
        User user = userService.login(username, password, Integer.valueOf(utype));
        if(user == null) {
            if(utype.equals("4") || utype.equals("2")) {
                user = userService.login(username,password,3);
            }
        }
        if(user.getUimage() == null || user.getUimage().equals("")) {
            user.setUimage("https://img0.baidu.com/it/u=1942253063,3807598283&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500");
        }
        if (user != null) {
            List<Club> club = clubService.getClubByUid(user.getUid());
            if(club != null && club.size() > 0) {
                request.getSession().setAttribute("club",club.get(0));
              }
            if (utype.equals("4") || utype.equals("3")) {
                request.getSession().setAttribute("student",user);
            }else if (utype.equals("2")){
                request.getSession().setAttribute("user",user);
            }else if(utype.equals("1")) {
                request.getSession().setAttribute("super",user);
            }
            return "登录成功";
        }
        return "账号或密码错误";
    }

    //注册
    @RequestMapping("/register")
    @ResponseBody
    public String register(String uname, String uno, String uemail, String usex, String upassword) {
        User user = new User();
        user.setUname(uname);
        user.setUno(uno);
        user.setUemail(uemail);
        user.setUsex(usex);
        user.setUpassword(upassword);
        int result = userService.register(user);
        if (result > 0) {
            return "注册成功";
        }
        return "注册失败";
    }

    //输入邮箱将密钥存入数据库中,并发送邮件
    @RequestMapping("/forgetpwd")
    @ResponseBody
    public Map forgetPass(HttpServletRequest request,
                          @RequestParam("uemail") String uemail,
                          Model model) {
        User user = userService.getUserByEmail(uemail);
        Map<String, String> map = new HashMap<>();
        String msg = "";
        if (uemail.equals("")) {
            msg = "请输入邮箱！";
            map.put("msg", msg);
            return map;
        }
        if (user == null) {
            msg = "邮箱不存在？";
            map.put("msg", msg);
            return map;
        }
        //生成一个密钥
        String secretKey = UUID.randomUUID().toString();
        //设置30分钟有效时间
        Timestamp outDate = new Timestamp(System.currentTimeMillis() + 30 * 60 * 1000);
        long date = outDate.getTime() / 1000 * 1000; //忽略毫秒数
        user.setValidatacode(secretKey);
        user.setOutdate(outDate);
        userService.upadteUserByCode(user);
        String key = user.getUname() + "$" + date + "$" + secretKey;
        System.out.println("key1:" + key);
        //数字签名
        String digitalSignature = Md5Util.Md5Encode(key);
        String emailTitle = "密码找回";
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName()
                + ":" + request.getServerPort() + path + "/";
        String resetPassHref = basePath + "user/resetpassword?sid=" + digitalSignature
                + "&uemail=" + user.getUemail();
        String emailContent = "请勿回复本邮件.点击下面的链接,重设密码<br/><a href=" + resetPassHref +
                ">点击我重新设置密码</a>" +
                "<br/>tips:本邮件超过30分钟,链接将会失效，需要重新申请'找回密码'" +
                "<br/>tips:本链接可能被邮箱拦截，如链接无效，请复制下列链接到您的浏览器中。<br/>" +
                "链接开始：<span style='color:#F00; font-weight:bold'>" + resetPassHref + "</span>链接结束";
        try {
            SendmailUtil.class.newInstance().doSendHtmlEmail(emailTitle, emailContent, user.getUemail());
            msg = "操作成功,已经发送找回密码链接到您邮箱。请在30分钟内重置密码";
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            msg = "邮箱不存在？";
        }
        map.put("msg", msg);
        return map;
    }

    //重置密码
    @RequestMapping("/resetpassword")
    public String resetPwd(@RequestParam("sid") String sid,
                           @RequestParam("uemail") String uemail,
                           Model model) {
        String msg = "";
        User user = userService.getUserByEmail(uemail);
        Date outDate = user.getOutdate();
        if(outDate.getTime() <= System.currentTimeMillis()) {//表示已过期
            System.out.println(outDate.getTime());
            System.out.println(System.currentTimeMillis());
            msg = "链接已经过期,请重新申请找回密码.";
            model.addAttribute("msg",msg) ;
            return "findPwdError";
        }
        String key = user.getUname() + "$" + outDate.getTime() / 1000 * 1000 + "$" + user.getValidatacode();
        System.out.println("key2:" + key);
        String digitalSignature = Md5Util.Md5Encode(key);
        if(!digitalSignature.equals(sid)) {
            msg = "链接不正确,是否已经过期了?重新申请吧";
            System.out.println(sid);
            System.out.println(digitalSignature);
            model.addAttribute("msg",msg);
            return "findPwdError";
        }
        model.addAttribute("uemail",uemail);
        return "reset_page";
    }

    //更新密码
    @RequestMapping("/updatepwd")
    @ResponseBody
    public String updatePwd(String uemail,String upassword,HttpServletRequest request) {
        User user = userService.getUserByEmail(uemail);
        user.setUpassword(upassword);
        //修改密码后，需要重新登录，所以这里需要将session对象清空
        request.getSession().invalidate();
        if(userService.updatePwd(user) > 0) {
            return "密码修改成功，3秒后返回登录页面";
        }else {
            return "密码修改失败，服务器异常";
        }
    }

    //设置个人信息
    @RequestMapping("/update_user")
    @ResponseBody
    public String updateUser(String uclass,String unick,String uemail,String uimage,String usign,String uno,HttpServletRequest request) {
        User user = new User();
        user.setUclass(uclass);
        user.setUnick(unick);
        user.setUemail(uemail);
        user.setUimage(uimage);
        user.setUsign(usign);
        user.setUno(uno);
        int result = userService.updatePersonalMessage(user);
        if(result > 0) {
            request.getSession().setAttribute("user",user);
            return "保存成功";
        }else {
            return "服务器异常，保存失败";
        }
    }

    //设置学生个人信息
    @RequestMapping("/update_student")
    @ResponseBody
    public String updateStudent(String uclass,String unick,String uemail,String uimage,String usign,String uno,HttpServletRequest request) {
        User user = new User();
        user.setUclass(uclass);
        user.setUnick(unick);
        user.setUemail(uemail);
        user.setUimage(uimage);
        user.setUsign(usign);
        user.setUno(uno);
        int result = userService.updatePersonalMessage(user);
        if(result > 0) {
            request.getSession().setAttribute("student",user);
            return "保存成功";
        }else {
            return "服务器异常，保存失败";
        }
    }
    //退出登录
    @RequestMapping("/logout")
    public String logout(String utype,HttpServletRequest request) {
        //退出登录将session对象的数据清空
        if (utype.equals("1")) { //超级管理员
            request.getSession().removeAttribute("super");
        }else if(utype.equals("2")) { //社团负责人
            request.getSession().removeAttribute("user");
        }else if(utype.equals("4")) { //学生
            request.getSession().removeAttribute("student");
            return "redirect:/student/login.jsp";
        }

        return "redirect:/login.jsp";
    }

    //获取社团所有成员信息
    @RequestMapping("/getClubUsers")
    @ResponseBody
    public ClubUserTableVo getClubUsers(int cid,int page,int limit) {
        return userService.getTableUsers(cid, page, limit);
    }

    //更新社团用户角色
    @RequestMapping("/updateUserRole")
    @ResponseBody
    public int updateUserRole(int uid,int cid,int rid) {
        return userService.updateUserRole(uid, cid, rid);
    }
}
