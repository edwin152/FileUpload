package cn.ddzu.shop.dao;

import cn.ddzu.shop.entity.Office;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfficeMapperDao {

    void insertOffice(@Param("office") Office office);

    List<Office> selectOfficeList(@Param("id") Long id
            , @Param("keyword") String keyword
            , @Param("business_center_id") Long business_center_id
            , @Param("zone_id") Long zone_id
            , @Param("metro_name") String metro_name
            , @Param("type_id") Long type_id
            , @Param("area_range_id") Long area_range_id
            , @Param("price_range_id") Long price_range_id
            , @Param("decoration_id") Long decoration_id);
}
