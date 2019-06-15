package cn.ddzu.shop.service;

import java.util.List;
import java.util.Map;

public interface DBService {

    /**
     * 更新数据库
     */
    void updateDB();

    /**
     * 获取所有数据
     */
    Map<String, List> getAll();

    /**
     * 设置所有数据
     */
    void setAll(Map<String, List> data);
}
