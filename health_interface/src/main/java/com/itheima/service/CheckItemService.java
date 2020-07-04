package com.itheima.service;

import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.CheckItem;

import java.util.List;

//服务接口
public interface CheckItemService {
    /**
     * 新增检查项
     * @param checkItem
     */
    public void add(CheckItem checkItem);

    /**
     * 分页查询
     * @param queryPageBean
     * @return
     */
    public PageResult pageQuery(QueryPageBean queryPageBean);

    /**
     * 根据id删除检查项
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 根据id查询检查项信息并回显数据
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
