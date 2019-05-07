package cn.ddzu.shop.service.impl;

import cn.ddzu.shop.dao.OfficeMapperDao;
import cn.ddzu.shop.entity.Office;
import cn.ddzu.shop.service.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfficeServiceImpl implements OfficeService {

    @Autowired
    private OfficeMapperDao officeMapperDao;

    @Override
    public void addOffice(Office office) {
        officeMapperDao.insertOffice(office);
    }

    @Override
    public List<Office> getOfficeList(Long id
            , String keyword
            , Long business_center_id
            , Long zone_id
            , String metro_name
            , Long type_id
            , Long area_range_id
            , Long price_range_id
            , Long decoration_id) {
        return officeMapperDao.selectOfficeList(id
                , keyword
                , business_center_id
                , zone_id
                , metro_name
                , type_id
                , area_range_id
                , price_range_id
                , decoration_id);
    }
}
