package com.springboot.express.service;

import com.springboot.express.entity.Workload;
import com.baomidou.mybatisplus.extension.service.IService;
import com.springboot.express.entity.WorkloadVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lbl
 * @since 2020-11-29
 */
public interface WorkloadService extends IService<Workload> {
    //统计每个月的数据
    public ArrayList<Object> getAllworkloadbyMonth(Integer year);
    //统计一个月的收派量
    public Map getworkloadbyMonth(Integer month,Integer year);
    //根据快月份查询工作量 返回List[object] 收件 派件 收件失败 派件失败 最大工作量 最小工作量
    public ArrayList<WorkloadVO> getWorkloadVObyMonthforlist(Integer month);
}
