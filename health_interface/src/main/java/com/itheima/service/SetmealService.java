package com.itheima.service;

import com.itheima.entity.PageResult;
import com.itheima.pojo.Setmeal;

import java.util.List;

public interface SetmealService {
    /**
     * 新增检查套餐
     * @param setmeal
     * @param checkgroupIds
     */
    public void add(Setmeal setmeal,Integer[] checkgroupIds);

    /**
     * 检查套餐分页查询
     * @param currentPage
     * @param pageSize
     * @param queryString
     * @return
     */
    PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString);

    /**
     * 查询所有的套餐信息
     * @return
     */
    List<Setmeal> findAll();

    /**
     * 根据ID查询套餐相信信息
     * @param id
     * @return
     */
    Setmeal findById(int id);
}
