package com.itheima.service;

import com.itheima.entity.Result;

import java.util.Map;

/**
 * 体检预约接口
 */
public interface OrderService {
    /**
     * 预约
     * @param map
     * @return
     * @throws Exception
     */
    public Result order(Map map) throws Exception;

    /**
     * 根据用户id查询预约信息
     * @param id
     * @return
     */
    Map findById(Integer id);
}
