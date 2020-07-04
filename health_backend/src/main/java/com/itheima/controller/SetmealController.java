package com.itheima.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.constant.MessageConstant;
import com.itheima.constant.RedisConstant;
import com.itheima.entity.PageResult;
import com.itheima.entity.QueryPageBean;
import com.itheima.entity.Result;
import com.itheima.pojo.Setmeal;
import com.itheima.service.SetmealService;
import com.itheima.utils.QiniuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.JedisPool;

import java.io.IOException;
import java.util.UUID;

/**
 * 体检套餐管理
 */
@RestController
@RequestMapping("/setmeal")
public class SetmealController {
    /**
     * 图片上传
     * @param imgFile
     * @return
     */
    //使用JedisPool操作redis服务
    @Autowired
    private JedisPool jedisPool;

    @RequestMapping("/upload")
    public Result upload(@RequestParam("imgFile")  MultipartFile imgFile){
        String originalFilename = imgFile.getOriginalFilename();//获取原始文件名
        int index = originalFilename.lastIndexOf(".");
        String substring = originalFilename.substring(index);//获取.jpg
        String fileName = UUID.randomUUID().toString()+substring;
        try {
            //将文件上传到七牛云服务器
            QiniuUtils.upload2Qiniu(imgFile.getBytes(),fileName);
            jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_RESOURCES,fileName);
        } catch (IOException e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.PIC_UPLOAD_FAIL);
        }
        return new Result(true,MessageConstant.UPLOAD_SUCCESS,fileName);
    }
    @Reference
    private SetmealService setmealService;

    /**
     * 新增检查组
     * @param setmeal
     * @param checkgroupIds
     * @return
     */
    @RequestMapping("/add")
    public Result add(@RequestBody Setmeal setmeal, Integer[] checkgroupIds){
        try {
            setmealService.add(setmeal,checkgroupIds);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.ADD_SETMEAL_FAIL);
        }
        return new Result(true,MessageConstant.ADD_SETMEAL_SUCCESS);
    }
@RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
       PageResult pageResult = setmealService.pageQuery(
            queryPageBean.getCurrentPage(),
            queryPageBean.getPageSize(),
            queryPageBean.getQueryString()
        );
        return pageResult;
    }

}
