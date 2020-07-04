package com.itheima.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.constant.RedisConstant;
import com.itheima.dao.SetmealDao;
import com.itheima.entity.PageResult;
import com.itheima.pojo.CheckItem;
import com.itheima.pojo.Setmeal;
import com.itheima.service.SetmealService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import redis.clients.jedis.JedisPool;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = SetmealService.class)
@Transactional
public class SetmealServiceImpl implements SetmealService {
    @Autowired
    private SetmealDao setmealDao;
    @Autowired
    private JedisPool jedisPool;
    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;
    @Value("${out_put_path}")
    private String outPutPath;//从配置文件中获取生成的HTML存放的目录

    /**
     * 新增检查套餐
     *
     * @param setmeal
     * @param checkgroupIds
     */
    @Override
    public void add(Setmeal setmeal, Integer[] checkgroupIds) {
        setmealDao.add(setmeal);
        if (checkgroupIds != null && checkgroupIds.length > 0) {
            this.setSetmealAndCheckGroup(setmeal.getId(), checkgroupIds);
            String fileName = setmeal.getImg();
            //图片名称保存到redis
            jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_DB_RESOURCES, fileName);
            //新增套餐后需要重新生成静态页面
            generateMobileStaticHtml();
        }
    }

    //生成静态页面
    public void generateMobileStaticHtml() {
        List<Setmeal> setmealList = setmealDao.findAll();
        //生成套餐列表静态页面
        generateMobileSetmealListHtml(setmealList);
        //生成套餐详情静态页面（多个）
        generateMobileSetmealDetailHtml(setmealList);
    }

    //生成套餐列表静态页面
    public void generateMobileSetmealListHtml(List<Setmeal> setmealList) {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("setmealList", setmealList);
        this.generateHtml("mobile_setmeal.ftl", "m_setmeal.html", dataMap);
    }

    //生成套餐详情静态页面（多个）
    public void generateMobileSetmealDetailHtml(List<Setmeal> setmealList) {
        for (Setmeal setmeal : setmealList) {
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("setmeal", this.findById(setmeal.getId()));
            this.generateHtml("mobile_setmeal_detail.ftl", "setmeal_detail_" + setmeal.getId() + ".html", dataMap);
        }

    }

    public void generateHtml(String templateName, String htmlPageName, Map map) {
        Configuration configuration = freeMarkerConfigurer.getConfiguration();
        Writer out = null;
        try {
            //加载模板数据
            Template template = configuration.getTemplate(templateName);
            //生成数据
            File docFile = new File(outPutPath + "/" + htmlPageName);
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(docFile)));
            template.process(map,out);
        } catch (Exception e) {
            e.printStackTrace();
            if (null != out) {
                try {
                    out.flush();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }

    }

    /**
     * 分页查询套餐信息
     *
     * @param currentPage
     * @param pageSize
     * @param queryString
     * @return
     */
    @Override
    public PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString) {
        PageHelper.startPage(currentPage, pageSize);
        Page<CheckItem> page = setmealDao.selectByCondition(queryString);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public List<Setmeal> findAll() {

        return setmealDao.findAll();
    }

    /**
     * 根据套餐ID查询套餐详细信息
     *
     * @param id
     * @return
     */
    @Override
    public Setmeal findById(int id) {
        return setmealDao.findById(id);

    }

    //绑定套餐和检查组的多对多关系
    private void setSetmealAndCheckGroup(Integer id, Integer[] checkgroupIds) {
        Map<String, Integer> map = null;
        for (Integer checkgroupId : checkgroupIds) {
            map = new HashMap<>();
            map.put("setmealId", id);
            map.put("checkgroupId", checkgroupId);
            setmealDao.setSetmealAndCheckGroup(map);
        }
    }


}
