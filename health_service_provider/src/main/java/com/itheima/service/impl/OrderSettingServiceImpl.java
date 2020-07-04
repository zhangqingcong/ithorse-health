package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.itheima.dao.OrderSettingDao;
import com.itheima.pojo.OrderSetting;
import com.itheima.service.OrderSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 预约设置服务
 */
@Service(interfaceClass = OrderSettingService.class)
@Transactional
public class OrderSettingServiceImpl implements OrderSettingService {
    @Autowired
    private OrderSettingDao orderSettingDao;

    /**
     * 插入预约人数
     * @param list
     */
    @Override
    public void add(List<OrderSetting> list) {
        if (list != null && list.size() > 0) {
            for (OrderSetting orderSetting : list) {
          long count = orderSettingDao.findCountByOrderDate(orderSetting.getOrderDate());
          if (count>0){
              orderSettingDao.editNumberByOrderDate(orderSetting);
          }else {
              orderSettingDao.add(orderSetting);
          }
            }
        }
    }

    /**
     * 查询数据
     * @param date
     * @return
     */
    @Override
    public List<Map> getOrderSettingServiceByMonth(String date) {
        String dateBegin = date +"-1";
        String dateEnd = date+"-31";
        Map map = new HashMap();
        map.put("dateBegin",dateBegin);
        map.put("dateEnd",dateEnd);
       List<OrderSetting> list = orderSettingDao.getOrderSettingServiceByMonth(map);
       List<Map> data = new ArrayList<>();
       for (OrderSetting orderSetting:list){
           Map orderSettingMap = new HashMap();
           orderSettingMap.put("date",orderSetting.getOrderDate().getDate());////获得日期 （几号）
           orderSettingMap.put("number",orderSetting.getNumber());//可预约人数
           orderSettingMap.put("reservations",orderSetting.getReservations());//已预约人数
           data.add(orderSettingMap);
       }
        return data;
    }

    /**
     * 页面修改预约数
     * @param orderSetting
     */
    @Override
    public void editNumberByDate(OrderSetting orderSetting) {
       long count = orderSettingDao.findCountByOrderDate(orderSetting.getOrderDate());
       if (count>0){
           //当前日期已经进行了预约设置，需要进行修改操作
           orderSettingDao.editNumberByOrderDate(orderSetting);
       }else {
           //当前日期没有进行预约设置，进行添加操作
           orderSettingDao.add(orderSetting);
       }
    }
}
