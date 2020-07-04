package com.itheima.dao;

import com.itheima.pojo.Order;

import java.util.List;
import java.util.Map;

public interface OrderDao {
    /**
     *
     * @param order
     * @return
     */
    List<Order> findByCondition(Order order);

    /**
     *
     * @param order
     */
    void add(Order order);

    /**
     * 根据id查询预约详细信息
     * @param id
     * @return
     */
    Map findById4Detail(Integer id);
}
