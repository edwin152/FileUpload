package cn.ddzu.shop.service;

import cn.ddzu.shop.entity.*;

import java.util.List;

public interface OfficeService {

    /**
     * 插入办公室
     *
     * @param office 办公室信息
     */
    void addOffice(Office office);

    /**
     * 按条件筛选办公室
     *
     * @param id                 办公室id
     * @param keyword            关键词
     * @param business_center_id 商圈id
     * @param zone_id            区域id
     * @param metro_name         地铁名字
     * @param type_id            办公室类型id
     * @param area_range_id      面积区间id
     * @param price_range_id     价格区间id
     * @param decoration_id      装修类型id
     */
    List<Office> getOfficeList(Long id
            , String keyword
            , Long business_center_id
            , Long zone_id
            , String metro_name
            , Long type_id
            , Long area_range_id
            , Long price_range_id
            , Long decoration_id);
}
