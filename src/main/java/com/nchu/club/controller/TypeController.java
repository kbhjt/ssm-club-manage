package com.nchu.club.controller;

import com.nchu.club.domain.Type;
import com.nchu.club.service.ActivityTypeService;
import com.nchu.club.tablevo.TypeTableVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/type")
public class TypeController {
    @Autowired
    private ActivityTypeService activityTypeService;

    @RequestMapping("/getTypeData")
    @ResponseBody
    public TypeTableVo getAllType(){
        List<Type> types = activityTypeService.getAllType();
        TypeTableVo vo = new TypeTableVo();
        vo.setData(types);
        return vo;
    }
    @RequestMapping(value = "/addType",method = RequestMethod.GET)
    @ResponseBody
    public String addType(String type,int number,double time){
        Type type1 = new Type();
        type1.setType(type);
        type1.setNumber(number);
        type1.setTime(time);
        activityTypeService.addType(type1);
        return "添加成功";
    }
    @RequestMapping("/findTypeByid")
    @ResponseBody
    public Type getTypeByid(int atid){
        Type type = activityTypeService.getTypeByid(atid);
        return type;
    }

    @RequestMapping("/updateType")
    @ResponseBody
    public String updateType(int atid,String type,int number,double time){
        Type type2 = new Type();
        type2.setAtid(atid);
        type2.setType(type);
        type2.setNumber(number);
        type2.setTime(time);
        activityTypeService.updateType(type2);
        return "编辑成功";
    }

    @RequestMapping("/deleteType")
    @ResponseBody
    public String deleteType(int atid){
        activityTypeService.deleteType(atid);
        return "操作成功";
    }
}
