package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.entity.PageResult;
import com.itheima.pojo.CheckItem;
import com.itheima.pojo.Setmeal;

import java.util.List;
import java.util.Map;

public interface SetmealDao {
    /**
     * 新增检查套餐
     * @param setmeal
     */
    public void add(Setmeal setmeal);

    /**
     * 设置检查组与检查套餐的关系
     * @param map
     */
     public void setSetmealAndCheckGroup(Map<String,Integer> map);


    /**
     * 检查套餐分页查询
     * @param queryString
     * @return
     */
    Page<CheckItem> selectByCondition(String queryString);

    /**
     * 查询所有的套餐信息
     * @return
     */
    List<Setmeal> findAll();

    /**
     * 根据套餐id查询套餐详细信息
     * @param id
     * @return
     */
    Setmeal findById(int id);
}
