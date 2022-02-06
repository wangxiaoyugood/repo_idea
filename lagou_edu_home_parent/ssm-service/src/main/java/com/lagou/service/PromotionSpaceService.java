package com.lagou.service;

import com.lagou.domain.PromotionSpace;

import java.util.List;

public interface PromotionSpaceService {


    public List<PromotionSpace> findAllPromotionSpace();

    //新建广告位
    public void savePromotionSpace(PromotionSpace promotionSpace);

    //根据ID查询广告位信息
    public PromotionSpace findPromotionSpaceById(int id);

    //修改广告位
    public void updatePromotionSpace(PromotionSpace promotionSpace);


}
