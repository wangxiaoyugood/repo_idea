package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.PromotionAd;
import com.lagou.domain.PromotionAdVO;
import com.lagou.domain.ResponseResult;
import com.lagou.service.PromotionAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/PromotionAd")
public class PromotionAdController {

    @Autowired
    private PromotionAdService promotionAdService;

    //分页查询广告
    @RequestMapping("/findAllPromotionAdByPage")
    public ResponseResult findAllAdByPage(PromotionAdVO promotionAdVO){

        PageInfo<PromotionAd> pageInfo = promotionAdService.findAllPromotionAdByPage(promotionAdVO);
        ResponseResult result = new ResponseResult(true, 200, "查询成功", pageInfo);
        return result;
    }

    //图片上传
    @RequestMapping("/PromotionAdUpload")
    public ResponseResult fileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
        //判断接受文件是否为空
        if (file.isEmpty()){
            throw new RuntimeException();
        }

        //获取项目部署路径
        String realPath = request.getServletContext().getRealPath("/");

        String substring = realPath.substring(0, realPath.indexOf("ssm-web"));

        //获取原文件名
        String originalFilename = file.getOriginalFilename();

        //生成新文件名
        String newFileName = System.currentTimeMillis()+originalFilename.substring(originalFilename.lastIndexOf("."));

        //文件上传
        String uploadPath = substring + "upload\\";
        File filePath = new File(uploadPath, newFileName);

        //如果文件目录不存在就创建
        if(!filePath.getParentFile().exists()){
            filePath.getParentFile().mkdirs();
            System.out.println("创建目录:"+filePath);
        }
        //上传
        file.transferTo(filePath);

        //将文件名和文件路径返回，进行响应
        Map<String, String> map = new HashMap<>();
        map.put("fileName", newFileName);
        map.put("filePath","http://localhost:8080/upload/"+newFileName);
        ResponseResult result = new ResponseResult(true, 200, "上传成功", map);
        return result;

    }

    //广告动态上下线
    @RequestMapping("/updatePromotionAdStatus")
    public ResponseResult updatePromotionStatus(int id, int status){
        promotionAdService.updatePromotionAdStatus(id, status);
        ResponseResult result = new ResponseResult(true, 200, "成功", null);
        return result;
    }



}
