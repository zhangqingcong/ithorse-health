package com.itheima.service;

import com.itheima.pojo.OrderSetting;

import java.util.List;
import java.util.Map;


public interface OrderSettingService {
    /**
     * 导入数据
     * @param data
     */
    public void add(List<OrderSetting> data);

    /**
     * 查询数据
     * @param date
     * @return
     */
    List<Map> getOrderSettingServiceByMonth(String date);

    /**
     * 页面设置预约数
     * @param orderSetting
     */
    void editNumberByDate(OrderSetting orderSetting);
}
