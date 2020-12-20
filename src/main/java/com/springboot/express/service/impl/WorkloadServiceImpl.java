package com.springboot.express.service.impl;

import com.springboot.express.entity.PostmanInfo;
import com.springboot.express.entity.Workload;
import com.springboot.express.entity.WorkloadVO;
import com.springboot.express.mapper.WorkloadMapper;
import com.springboot.express.service.WorkloadService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author lbl
 * @since 2020-11-29
 */
@Service
public class WorkloadServiceImpl extends ServiceImpl<WorkloadMapper, Workload> implements WorkloadService {
    @Autowired
    private WorkloadMapper workloadMapper;

    //统计每个月的数据
    public ArrayList<Object> getAllworkloadbyMonth(Integer year) {

        ArrayList<Object> result = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            HashMap month1 = (HashMap) getworkloadbyMonth(i,year);
            result.add(month1);
        }
        return result;
    }

    //统计一个月的收派量
    public Map getworkloadbyMonth(Integer month,Integer year) {
        //派件量，收件量，收件故障量，派件故障量 总量
        int assignWorkload = 0, receiveWorkload = 0, receiveFault = 0, assignFault = 0,allWorkload=0;
        List<Workload> list = workloadMapper.getworkloadbyMonth(month,year);
        for (Object obj : list) {
            Workload workload = (Workload) obj;
            assignWorkload += workload.getAssignWorkload();
            receiveWorkload += workload.getReceiveWorkload();
            receiveFault += workload.getReceiveFault();
            assignFault += workload.getAssignFault();

        }
        allWorkload=assignWorkload+assignFault+receiveWorkload+receiveFault;

        //把每组数据放进map集合里面
        HashMap<String, Object> month1 = new HashMap<>();
        month1.put("Month", month + "月");
        month1.put("years", year );
        month1.put("assignWorkload", assignWorkload);
        month1.put("receiveWorkload", receiveWorkload);
        month1.put("receiveFault", receiveFault);
        month1.put("assignFault", assignFault);
        month1.put("allWorkload", allWorkload);
        return month1;
    }

    //根据快月份查询工作量 返回List[object] 收件 派件 收件失败 派件失败 最大工作量 最小工作量
    @Override
    public ArrayList<WorkloadVO> getWorkloadVObyMonthforlist(Integer month){
        ArrayList list= new ArrayList<>();
        List<WorkloadVO> workloadVOs= workloadMapper.getWorkloadVObyMonthforlist(month);
        if (workloadVOs == null){
            return list;
        }
        System.out.println(workloadVOs);
        //把每组数据放进map集合里面
        for (int i = 0; i < workloadVOs.size(); i++){
            HashMap<String, Object> workloadVOmap = new HashMap<>();
            workloadVOmap.put("names",workloadVOs.get(i).getNames());
            workloadVOmap.put("Month",workloadVOs.get(i).getMonth());
            workloadVOmap.put("receiveWorkloads",workloadVOs.get(i).getReceiveWorkloads());
            workloadVOmap.put("assignWorkloads",workloadVOs.get(i).getAssignWorkloads());
            workloadVOmap.put("receiveFaults",workloadVOs.get(i).getReceiveFaults());
            workloadVOmap.put("assignFaults",workloadVOs.get(i).getAssignFaults());
            workloadVOmap.put("maxWorkloads",workloadVOs.get(i).getMaxWorkloads());
            workloadVOmap.put("minWorkloads",workloadVOs.get(i).getMinWorkloads());
            list.add(workloadVOmap);
        }
        return list;
    }
}
