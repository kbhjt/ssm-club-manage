package com.nchu.club.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

@Controller
@RequestMapping("file")
public class FileController {

    //图片上传
    @RequestMapping("/upload")
    @ResponseBody
    public JSON uploadFile(MultipartFile file, HttpServletRequest request) {
        JSONObject json = new JSONObject();
        //路径
        String filePath = request.getSession().getServletContext().getRealPath("/") + "images/avatar/";//上传到这个文件夹
        File file1 = new File(filePath);
        //如果没有的话创建一个
        if (!file1.exists()) {
            file1.mkdirs();
        }

        //路径+文件名
        //文件名：file.getOriginalFilename()
        String finalFilePath = filePath + file.getOriginalFilename().trim();
        File desFile = new File(finalFilePath);
        if (desFile.exists()) {
            desFile.delete();
        }
        try {
            file.transferTo(desFile);
            json.put("code", 0);
            //将文件名放在msg中，前台提交表单时需要用到
            json.put("msg", file.getOriginalFilename().trim());
            json.put("url",finalFilePath);
            JSONObject json2 = new JSONObject();
            json2.put("src", finalFilePath);
            json2.put("title", "");
            json.put("Data", json2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            json.put("code", 1);
        }
        System.out.println(json);
        return json;
    }
}
