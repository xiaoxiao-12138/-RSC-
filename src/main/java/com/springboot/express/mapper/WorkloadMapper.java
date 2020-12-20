package com.springboot.express.mapper;

import com.springboot.express.entity.Workload;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springboot.express.entity.WorkloadVO;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lbl
 * @since 2020-11-29
 */
public interface WorkloadMapper extends BaseMapper<Workload> {
    public List<Workload> getworkloadbyMonth(@Param("month")Integer month, @Param("year")Integer year);

    //根据快月份查询工作量 返回List[object] 收件 派件 收件失败 派件失败 最大工作量 最小工作量
    public ArrayList<WorkloadVO> getWorkloadVObyMonthforlist(Integer month);
}
