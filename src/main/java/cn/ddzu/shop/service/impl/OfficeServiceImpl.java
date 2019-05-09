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
    public List<Office> getOfficeList(SearchBean searchBean, int page, int step) {
        return officeMapperDao.selectOfficeList(searchBean.getId()
                , searchBean.getKeyword()
                , searchBean.getBusiness_center_id()
                , searchBean.getZone_id()
                , searchBean.getMetro_name()
                , searchBean.getType_id()
                , searchBean.getArea_range_id()
                , searchBean.getPrice_range_id()
                , searchBean.getDecoration_id()
                , page < 0 ? 0 : page * step
                , step);
    }

    @Override
    public int getOfficeSize(SearchBean searchBean) {
        Integer size = officeMapperDao.countOfficeList(searchBean.getId()
                , searchBean.getKeyword()
                , searchBean.getBusiness_center_id()
                , searchBean.getZone_id()
                , searchBean.getMetro_name()
                , searchBean.getType_id()
                , searchBean.getArea_range_id()
                , searchBean.getPrice_range_id()
                , searchBean.getDecoration_id());
        return size == null ? 0 : size;
    }
}
