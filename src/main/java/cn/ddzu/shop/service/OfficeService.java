package cn.ddzu.shop.service;

import cn.ddzu.shop.entity.*;

import java.util.List;

public interface OfficeService {

    void reset();

    /**
     * 插入办公室
     */
    void addOffice(Office office);

    /**
     * 删除办公室
     */
    void deleteOffice(Long id);

    /**
     * 修改办公室
     */
    void updateOffice(Office office);

    /**
     * 获得办公室
     */
    Office getOffice(Long id);

    /**
     * 按条件筛选办公室
     *
     * @param page 当前页数 0开始
     * @param step 每页数量
     */
    List<Office> getOfficeList(SearchBean searchBean, int page, int step);

    /**
     * 按条件筛选办公室数量
     */
    int getOfficeSize(SearchBean searchBean);

    /**
     * 插入楼
     */
    void addBuilding(Building building);

    /**
     * 删除办公室
     */
    void deleteBuilding(Long id);

    /**
     * 修改办公室
     */
    void updateBuilding(Building building);

    /**
     * 获得楼
     */
    Building getBuilding(Long id);

    /**
     * 按条件筛选楼
     *
     * @param page 当前页数 0开始
     * @param step 每页数量
     */
    List<Building> getBuildingList(SearchBean searchBean, int page, int step);

    /**
     * 按条件筛选楼
     *
     * @param page 当前页数 0开始
     * @param step 每页数量
     */
    List<Building> getBuildingListWithOffice(SearchBean searchBean, int page, int step);

    /**
     * 按条件筛选楼数量
     */
    int getBuildingSize(SearchBean searchBean);

    /**
     * 按条件筛选楼数量
     */
    int getBuildingSizeWithOffice(SearchBean searchBean);

    class SearchBean implements Cloneable {
        // 关键词
        private String keyword;
        // 商圈id
        private Long building_id;
        // 区id
        private Long district_id;
        // 区域id
        private Long zone_id;
        // 地铁名字
        private String metro_name;
        // 办公室类型id
        private Long type_id;
        // 面积区间id
        private Long area_range_id;
        // 价格区间id
        private Long price_range_id;
        // 装修类型id
        private Long decoration_id;

        public String getKeyword() {
            return keyword;
        }

        public void setKeyword(String keyword) {
            this.keyword = keyword;
        }

        public Long getBuilding_id() {
            return building_id;
        }

        public void setBuilding_id(Long building_id) {
            this.building_id = building_id;
        }

        public Long getDistrict_id() {
            return district_id;
        }

        public void setDistrict_id(Long district_id) {
            if (district_id == 1) {
                district_id = null;
            }
            this.district_id = district_id;
        }

        public Long getZone_id() {
            return zone_id;
        }

        public void setZone_id(Long zone_id) {
            if (zone_id == 1) {
                zone_id = null;
            }
            this.zone_id = zone_id;
        }

        public String getMetro_name() {
            return metro_name;
        }

        public void setMetro_name(Metro metro) {
            if (metro == null || metro.getId() == 1L) {
                this.metro_name = null;
            } else {
                this.metro_name = metro.getName();
            }
        }

        public Long getType_id() {
            return type_id;
        }

        public void setType_id(Long type_id) {
            if (type_id == 1) {
                type_id = null;
            }
            this.type_id = type_id;
        }

        public Long getArea_range_id() {
            return area_range_id;
        }

        public void setArea_range_id(Long area_range_id) {
            if (area_range_id == 1) {
                area_range_id = null;
            }
            this.area_range_id = area_range_id;
        }

        public Long getPrice_range_id() {
            return price_range_id;
        }

        public void setPrice_range_id(Long price_range_id) {
            if (price_range_id == 1) {
                price_range_id = null;
            }
            this.price_range_id = price_range_id;
        }

        public Long getDecoration_id() {
            return decoration_id;
        }

        public void setDecoration_id(Long decoration_id) {
            if (decoration_id == 1) {
                decoration_id = null;
            }
            this.decoration_id = decoration_id;
        }

        @Override
        public Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }
}
