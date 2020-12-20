package com.springboot.express.service;

import com.springboot.express.entity.Postman;
import com.baomidou.mybatisplus.extension.service.IService;
import com.springboot.express.entity.PostmanInfo;
import com.springboot.express.entity.WorkloadVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lbl
 * @since 2020-11-29
 */
public interface PostmanService extends IService<Postman> {
    //获取所以的快递员信息
    public List<PostmanInfo> getPostmanInfo( );
    //根据快递员ID获取
    public List<PostmanInfo> getOnePostmanInfo(Integer pid ,Integer year);
    //根据快递员id 和月份查询 返回list[map] 工作情况 工资 个人信息
    public ArrayList<Object> getOnePostmanInfobyNameandMonth(Integer pid,Integer month,Integer year);
    //根据快递员id 和月份查询 返回List[object] 工作情况 工资 个人信息
    public List<PostmanInfo> getOnePostmanInfobyNameAndMonthforlist(Integer pid,Integer month,Integer year);
    //根据快递员表获取快递员
    public List<Postman> getAllPostmans();

    public List<Postman> getAllPostman(Integer region_id,Integer day);
}
