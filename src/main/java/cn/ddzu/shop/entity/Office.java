package cn.ddzu.shop.entity;

public class Office {

    private long id;
    private String name;
    private Long building_id;
    private String address;
    private Long type_id;
    private Float area;
    private Long area_range_id;
    private Float price;
    private Long price_range_id;
    private Long decoration_id;
    private String img_list;

    private String building_name;
    private Long zone_id;
    private String district_name;
    private String zone_name;
    private String metro_name_list;
    private String type_name;
    private String area_range_name;
    private String price_range_name;
    private String decoration_name;

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

    public Long getBuilding_id() {
        return building_id;
    }

    public void setBuilding_id(Long building_id) {
        this.building_id = building_id;
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

    public Float getArea() {
        return area;
    }

    public void setArea(Float area) {
        this.area = area;
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

    public String getImg_list() {
        return img_list;
    }

    public void setImg_list(String img_list) {
        this.img_list = img_list;
    }

    public String getBuilding_name() {
        return building_name;
    }

    public void setBuilding_name(String building_name) {
        this.building_name = building_name;
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

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public String getArea_range_name() {
        return area_range_name;
    }

    public void setArea_range_name(String area_range_name) {
        this.area_range_name = area_range_name;
    }

    public String getPrice_range_name() {
        return price_range_name;
    }

    public void setPrice_range_name(String price_range_name) {
        this.price_range_name = price_range_name;
    }

    public String getDecoration_name() {
        return decoration_name;
    }

    public void setDecoration_name(String decoration_name) {
        this.decoration_name = decoration_name;
    }
}
