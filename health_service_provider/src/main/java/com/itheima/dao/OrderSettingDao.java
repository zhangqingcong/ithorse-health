package com.itheima.dao;

import com.itheima.pojo.OrderSetting;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface OrderSettingDao {
    /**
     * 根据日期查询当日是否已有预约数
     * @param orderDate
     * @return
     */
    long findCountByOrderDate(Date orderDate);

    /**
     * 根据日期更新预约人数
     * @param orderSetting
     */
    void editNumberByOrderDate(OrderSetting orderSetting);

    /**
     *插入预约人数
     * @param orderSetting
     */
    void add(OrderSetting orderSetting);

    /**
     * 根据日期查询预约人数
     * @param map
     * @return
     */
    List<OrderSetting> getOrderSettingServiceByMonth(Map map);

    /**
     * 查询当前日期能否预约
     * @param date
     * @return
     */
    OrderSetting findByOrderDate(Date date);

    /**
     * 预约人数+1
     * @param orderSetting
     */
    void editReservationsByOrderDate(OrderSetting orderSetting);


}
