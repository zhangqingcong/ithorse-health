package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.dao.CheckGroupDao;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.pojo.CheckGroup;
import com.itheima.service.CheckGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = CheckGroupService.class)
@Transactional
public class CheckGroupServiceImpl implements CheckGroupService {
    @Autowired
    private CheckGroupDao checkGroupDao;

    /**
     * 新增检查组，同时需要让检查组关联检查项
     *
     * @param checkGroup
     * @param checkitemIds
     */
    @Override
    public void add(CheckGroup checkGroup, Integer[] checkitemIds) {
        //新增检查组 操作的是t_checkgroup表
        checkGroupDao.add(checkGroup);
        //设置检查组和检查项的多对多的关联关系 操作t_checkgroup_checkitem
        Integer checkGroupId = checkGroup.getId();
        this.setCheckgroupAndCheckItem(checkGroupId,checkitemIds);
    }

    /**
     * 检查组分页查询
     *
     * @param queryPageBean
     * @return
     */
    @Override
    public PageResult pageQuery(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();
        PageHelper.startPage(currentPage, pageSize);
        Page<CheckGroup> page = checkGroupDao.findByCondition(queryString);
        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     * 根据id查询检查组
     * @param id
     * @return
     */
    @Override
    public CheckGroup findById(Integer id) {
        return checkGroupDao.findById(id);
    }

    @Override
    public List<Integer> findCheckItemIdsByCheckGroup(Integer id) {
        return checkGroupDao.findCheckItemIdsByCheckGroup(id);
    }

    @Override
    public void edit(CheckGroup checkGroup, Integer[] checkitemIds) {
        //修改检查组基本信息，操作检查组t_checkgroup表
        checkGroupDao.edit(checkGroup);
        //删除关联关系
        checkGroupDao.deleteAssocication(checkGroup.getId());
        //重新建立检查组与检查项的关联关系
     Integer checkGroupId = checkGroup.getId();
        this.setCheckgroupAndCheckItem(checkGroupId,checkitemIds);
    }

    @Override
    public List<CheckGroup> findAll() {
        return checkGroupDao.findAll();
    }

    /**
     * 把操作见检查组与检查项多对多的代码抽取成方法
     * @param checkGroupId
     * @param checkitemIds
     */
    public void setCheckgroupAndCheckItem(Integer checkGroupId,Integer[] checkitemIds){
        if (checkitemIds != null && checkitemIds.length > 0) {
            Map<String, Integer> map = null;
            for (Integer checkitemId : checkitemIds) {
                map = new HashMap<>();
                map.put("checkgroupId", checkGroupId);
                map.put("checkitemId", checkitemId);
                checkGroupDao.setCheckgroupAndCheckItem(map);
            }
        }
    }
}
