package com.lagou.controller;

import com.lagou.domain.PromotionSpace;
import com.lagou.domain.ResponseResult;
import com.lagou.service.PromotionSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/PromotionSpace")
public class PromotionSpaceController {

    @Autowired
    private PromotionSpaceService promotionSpaceService;


    @RequestMapping("/findAllPromotionSpace")
    public ResponseResult findAllPromotionSpace(){
        List<PromotionSpace> allPromotionSpace = promotionSpaceService.findAllPromotionSpace();
        ResponseResult result = new ResponseResult(true, 200, "响应成功", allPromotionSpace);
        return result;
    }

    @RequestMapping("/saveOrUpdatePromotionSpace")
    public ResponseResult savePromotionSpace(@RequestBody PromotionSpace promotionSpace){
        if(promotionSpace.getId() == null){
            promotionSpaceService.savePromotionSpace(promotionSpace);
        }else{
            promotionSpaceService.updatePromotionSpace(promotionSpace);
        }
        ResponseResult result = new ResponseResult(true, 200, "响应成功", null);
        return result;
    }

    @RequestMapping("/findPromotionSpaceById")
    public ResponseResult findPromotionSpaceById(int id){
        PromotionSpace promotionSpace = promotionSpaceService.findPromotionSpaceById(id);
        ResponseResult result = new ResponseResult(true, 200, "响应成功", promotionSpace);
        return result;
    }





}
