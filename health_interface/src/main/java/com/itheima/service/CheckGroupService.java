package com.itheima.service;

import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.CheckGroup;

import java.util.List;

public interface CheckGroupService {
    /**
     * 新增检查组及检查组与检查项关联关系
     * @param checkGroup
     * @param checkitemIds
     */
    public void add(CheckGroup checkGroup,Integer[] checkitemIds);

    /**
     * 检查组分页查询
     * @param queryPageBean
     * @return
     */
    PageResult pageQuery(QueryPageBean queryPageBean);

    /**
     * 根据ID查询检查组
     * @param id
     * @return
     */
    CheckGroup findById(Integer id);

    /**
     * 查询选择的检查项
     * @param id
     * @return
     */
    List<Integer> findCheckItemIdsByCheckGroup(Integer id);

    /**
     * 编辑检查项
     * @param checkGroup
     * @param checkitemIds
     */
    void edit(CheckGroup checkGroup, Integer[] checkitemIds);

    /**
     * 查询所有检查组
     * @return
     */
    List<CheckGroup> findAll();
}
