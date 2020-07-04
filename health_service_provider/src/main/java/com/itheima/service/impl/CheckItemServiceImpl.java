package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.constant.MessageConstant;
import com.itheima.dao.CheckItemDao;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.entity.Result;
import com.itheima.pojo.CheckItem;
import com.itheima.service.CheckItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 检查项服务
 */
//注解不要写错  这里用dubbo服务的注解
@Service(interfaceClass = CheckItemService.class)
@Transactional
public class CheckItemServiceImpl implements CheckItemService {
    //注入DAO对象
    @Autowired
    private CheckItemDao checkItemDao;

    /**
     * 新增检查项
     * @param checkItem
     */
    @Override
    public void add(CheckItem checkItem) {
        checkItemDao.add(checkItem);
    }

    /**
     * 分页查询
     * @param queryPageBean
     * @return
     */
    @Override
    public PageResult pageQuery(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage(); //当前页码
        Integer pageSize = queryPageBean.getPageSize(); //每页记录数
        String queryString = queryPageBean.getQueryString();//查询条件
        //完成分页查询，基于mybatis框架提供的分页助手插件完成
        PageHelper.startPage(currentPage,pageSize);
        //分页助手查询的结果固定用Page接
        Page<CheckItem> page = checkItemDao.selectByCondition(queryString);
        long total = page.getTotal();
        List<CheckItem> rows = page.getResult();//源码  public List<E> getResult() {return this;} 这个方法返回的就是集合对象本身
        //若直接返回rows 会没有total属性
        return new PageResult(total,rows);
    }

    /**
     * 根据id删除检查项
     * @param id
     */
    @Override
    public void deleteById(Integer id) {
        //删除之前先判断要删除的检查项是否已经关联检查组
       long count = checkItemDao.findCountByCheckItemId(id);
       if (count>0){
           //说明检查项已关联到检查组，不允许删除
           throw new RuntimeException();

       }
       checkItemDao.deleteById(id);

    }

    /**
     * 根据id查询检查项并回显数据
     * @param id
     * @return
     */
    @Override
    public CheckItem findById(Integer id) {
       return checkItemDao.findById(id);
    }

    /**
     * 编辑检查项
     * @param checkItem
     */
    @Override
    public void edit(CheckItem checkItem) {
        checkItemDao.edit(checkItem);
    }

    /**
     * 查询所有的检查项
     * @return
     */
    @Override
    public List<CheckItem> findAll() {
       return checkItemDao.findAll();

    }

}
