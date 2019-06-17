package cn.ddzu.shop.entity;

import java.sql.Date;

public class Building {

    private long id;
    private String name;
    // 区域id
    private Long zone_id;
    // 详细地址
    private String address;
    // 地铁列表
    private String metro_name_list;
    // 介绍
    private String introduce;
    // 备注
    private String notes;
    // 图片列表
    private String img_list;
    // 编辑时间
    private Date edit_time;
    // 是否热门
    private Boolean hot;
    private Boolean valid;

    private Long district_id;
    private String district_name;
    private String zone_name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getZone_id() {
        return zone_id;
    }

    public void setZone_id(Long zone_id) {
        this.zone_id = zone_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMetro_name_list() {
        return metro_name_list;
    }

    public void setMetro_name_list(String metro_name_list) {
        this.metro_name_list = metro_name_list;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getImg_list() {
        return img_list;
    }

    public void setImg_list(String img_list) {
        this.img_list = img_list;
    }

    public Boolean isValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    public Long getDistrict_id() {
        return district_id;
    }

    public void setDistrict_id(Long district_id) {
        this.district_id = district_id;
    }

    public String getDistrict_name() {
        return district_name;
    }

    public void setDistrict_name(String district_name) {
        this.district_name = district_name;
    }

    public String getZone_name() {
        return zone_name;
    }

    public void setZone_name(String zone_name) {
        this.zone_name = zone_name;
    }
}
