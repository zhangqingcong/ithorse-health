package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constant.MessageConstant;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.entity.Result;
import com.itheima.pojo.CheckGroup;
import com.itheima.service.CheckGroupService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 检查组管理
 */
@RestController
@RequestMapping("/checkgroup")
public class CheckGroupController {
    //用dubbo注解 寻找远程服务
    @Reference
    private CheckGroupService checkGroupService;

    /**
     * 新增检查组
     *
     * @param checkGroup
     * @param checkitemIds
     * @return
     */
    @RequestMapping("/add")
    public Result add(@RequestBody CheckGroup checkGroup, Integer[] checkitemIds) {
        try {
            checkGroupService.add(checkGroup, checkitemIds);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_CHECKGROUP_FAIL);//新增检查组失败
        }
        return new Result(true, MessageConstant.ADD_CHECKGROUP_SUCCESS);//新增检查组成功
    }

    /**
     * 检查组分页查询
     *
     * @param queryPageBean
     * @return
     */
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean) {
        return checkGroupService.pageQuery(queryPageBean);
    }

    /**
     * 根据id查询检查组
     *
     * @param id
     * @return
     */
    @RequestMapping("/findById")
    public Result findById(Integer id) {
        CheckGroup checkGroup = null;
        try {
            checkGroup = checkGroupService.findById(id);
            return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS, checkGroup);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_CHECKGROUP_FAIL);
        }
    }

    @RequestMapping("/findCheckItemIdsByCheckGroup")
    public Result findCheckItemIdsByCheckGroup(Integer id) {
        List<Integer> checkitemIds = null;
        try {
            checkitemIds = checkGroupService.findCheckItemIdsByCheckGroup(id);
            return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS, checkitemIds);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.DELETE_CHECKITEM_FAIL);
        }
    }

    /**
     * 编辑检查项
     *
     * @param checkGroup
     * @param checkitemIds
     * @return
     */
    @RequestMapping("/edit")
    public Result edit(@RequestBody CheckGroup checkGroup, Integer[] checkitemIds) {
        try {
            checkGroupService.edit(checkGroup, checkitemIds);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.EDIT_CHECKITEM_FAIL);
        }
        return new Result(true, MessageConstant.EDIT_CHECKITEM_SUCCESS);
    }

    /**
     * 查询所有检查组
     * @return
     */
    @RequestMapping("/findAll")
    public Result findAll() {
        List<CheckGroup> checkGroups = null;
        try {
            checkGroups = checkGroupService.findAll();
            return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS, checkGroups);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_CHECKGROUP_FAIL);
        }
    }
}
