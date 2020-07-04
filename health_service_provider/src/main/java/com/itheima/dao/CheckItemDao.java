package com.itheima.dao;

import com.github.pagehelper.Page;
import com.itheima.pojo.CheckItem;

import java.util.List;

public interface CheckItemDao {
    /**
     * 新增检查项
     * @param checkItem
     */
    public void add(CheckItem checkItem);

    /**
     * 分页查询
     * @param queryString
     * @return
     */
    public Page<CheckItem> selectByCondition(String queryString);

    /**
     * 查询检查项是否与检查组关联
     * @param id
     * @return
     */
    long findCountByCheckItemId(Integer id);

    /**
     * 删除检查项
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 根据id查询检查项并回显数据
     * @param id
     * @return
     */
    CheckItem findById(Integer id);

    /**
     * 编辑检查项
     * @param checkItem
     */
    void edit(CheckItem checkItem);

    /**
     * 查询出所有的检查项
     * @return
     */
    List<CheckItem> findAll();
}
