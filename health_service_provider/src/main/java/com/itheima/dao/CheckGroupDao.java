package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.pojo.CheckGroup;

import java.util.List;
import java.util.Map;

public interface CheckGroupDao {
    /**
     * 新增检查组
     * @param checkGroup
     */
    void add(CheckGroup checkGroup);

    /**
     * 新增检查组与检查项关联关系
     * @param map
     */
    void setCheckgroupAndCheckItem(Map<String, Integer> map);

    /**
     * 根据条件查询检查组
     * @param queryString
     * @return
     */
    Page<CheckGroup> findByCondition(String queryString);

    /**
     * 根据id查询检查组
     * @param id
     * @return
     */
    CheckGroup findById(Integer id);

    /**
     * 查询复选的检查项
     * @param id
     * @return
     */
    List<Integer> findCheckItemIdsByCheckGroup(Integer id);

    /**
     * 修改检查组基本信息
     * @param checkGroup
     */
    void edit(CheckGroup checkGroup);

    /**
     * 根据检查组id删除关联关系
     * @param id
     */
    void deleteAssocication(Integer id);

    /**
     * 查询所有的检查组
     * @return
     */
    List<CheckGroup> findAll();
}
