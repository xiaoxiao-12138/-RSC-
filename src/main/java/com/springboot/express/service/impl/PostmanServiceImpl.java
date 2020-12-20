package com.springboot.express.service.impl;

import com.springboot.express.entity.*;
import com.springboot.express.mapper.PostmanMapper;
import com.springboot.express.service.PostmanService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lbl
 * @since 2020-11-29
 */
@Service
public class PostmanServiceImpl extends ServiceImpl<PostmanMapper, Postman> implements PostmanService {
    @Autowired
    private PostmanMapper postmanMapper;
    //获取所以的快递员信息
    @Override
    public List<PostmanInfo> getPostmanInfo() {
        return postmanMapper.getPostmanInfo();
    }
    //根据快递员ID获取
    @Override
    public List<PostmanInfo> getOnePostmanInfo(Integer pid,Integer year) {
        return postmanMapper.getOnePostmanInfo(pid,year);
    }

    //根据快递员id 和月份查询 返回list[map] 工作情况 工资 个人信息
    @Override
    public ArrayList<Object> getOnePostmanInfobyNameandMonth( Integer pid,Integer month,Integer year) {
        ArrayList list= new ArrayList<>();
        PostmanInfo postmanInfo= postmanMapper.getOnePostmanInfobyNameandMonth(pid,month,year);
        if (postmanInfo==null){
            return list;
        }
        System.out.println(postmanInfo.getPostman().getName());
        //把每组数据放进map集合里面
        HashMap<String, Object> postmanmap = new HashMap<>();
        postmanmap.put("names",postmanInfo.getPostman().getName());
        postmanmap.put("id",postmanInfo.getPostman().getId());
        postmanmap.put("phone",postmanInfo.getPostman().getPhone());

        postmanmap.put("basic",postmanInfo.getSalary().getBasic());
        postmanmap.put("assessment",postmanInfo.getSalary().getAssessment());
        postmanmap.put("total",postmanInfo.getSalary().getTotal());
        postmanmap.put("duty",postmanInfo.getAttendance().getDuty());
        postmanmap.put("leavess",postmanInfo.getAttendance().getLeaves());
        postmanmap.put("overtime",postmanInfo.getAttendance().getOvertime());
        postmanmap.put("vacation",postmanInfo.getAttendance().getVacation());
        postmanmap.put("years",postmanInfo.getAttendance().getYear());
        postmanmap.put("Month",postmanInfo.getAttendance().getMonth());
        postmanmap.put("reciveWorkload",postmanInfo.getWorkload().getReceiveWorkload());
        postmanmap.put("assignWorkload",postmanInfo.getWorkload().getAssignWorkload());
        postmanmap.put("receiveFault",postmanInfo.getWorkload().getReceiveFault());
        postmanmap.put("assignFault",postmanInfo.getWorkload().getAssignFault());
        list.add(postmanmap);
        return list;
    }

    //根据快递员id 和月份查询 返回List[object] 工作情况 工资 个人信息
    @Override
    public List<PostmanInfo> getOnePostmanInfobyNameAndMonthforlist(Integer pid, Integer month,Integer year) {
        List<PostmanInfo> list= new ArrayList<>();
        PostmanInfo postmanInfo=postmanMapper.getOnePostmanInfobyNameandMonth(pid,month,year);
        if (postmanInfo==null){
            return list;
        }
        list.add(postmanInfo);
        return list;
    }

    //根据快递员表获取快递员
    public List<Postman> getAllPostmans(){
        return  postmanMapper.getAllPostmans();
    }

    public List<Postman> getAllPostman(Integer region_id,Integer day) {
        return postmanMapper.getAllPostman(region_id,day);
    }
}
