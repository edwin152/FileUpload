package cn.ddzu.shop.entity;

public class Office {

    private long id;
    private String name;
    private Long business_center_id;
    private Long zone_id;
    private String address;
    private String metro_name_list;
    private Long type_id;
    private Float area_value;
    private Long area_range_id;
    private Float price;
    private Long price_range_id;
    private Long decoration_id;

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

    public Long getBusiness_center_id() {
        return business_center_id;
    }

    public void setBusiness_center_id(Long business_center_id) {
        this.business_center_id = business_center_id;
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

    public Long getType_id() {
        return type_id;
    }

    public void setType_id(Long type_id) {
        this.type_id = type_id;
    }

    public Float getArea_value() {
        return area_value;
    }

    public void setArea_value(Float area_value) {
        this.area_value = area_value;
    }

    public Long getArea_range_id() {
        return area_range_id;
    }

    public void setArea_range_id(Long area_range_id) {
        this.area_range_id = area_range_id;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Long getPrice_range_id() {
        return price_range_id;
    }

    public void setPrice_range_id(Long price_range_id) {
        this.price_range_id = price_range_id;
    }

    public Long getDecoration_id() {
        return decoration_id;
    }

    public void setDecoration_id(Long decoration_id) {
        this.decoration_id = decoration_id;
    }
}
