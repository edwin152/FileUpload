package cn.ddzu.shop.entity;

public class Building {

    private long id;
    private String name;
    private Long zone_id;
    private String address;
    private String metro_name_list;
    private String img_list;

    private String district_name;
    private String zone_name;

    public Building() {
    }

    public Building(String name, Long zone_id, String address, String metro_name_list, String img_list) {
        this.name = name;
        this.zone_id = zone_id;
        this.address = address;
        this.metro_name_list = metro_name_list;
        this.img_list = img_list;
    }

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

    public String getImg_list() {
        return img_list;
    }

    public void setImg_list(String img_list) {
        this.img_list = img_list;
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
