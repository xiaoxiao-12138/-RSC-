package com.springboot.express.service.impl;

import com.springboot.express.entity.Attendance;
import com.springboot.express.mapper.AttendanceMapper;
import com.springboot.express.service.AttendanceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lbl
 * @since 2020-12-13
 */
@Service
public class AttendanceServiceImpl extends ServiceImpl<AttendanceMapper, Attendance> implements AttendanceService {
    @Autowired
    AttendanceMapper attendanceMapper;
    @Override
    //查出所有快递员的出勤表
    public List<Attendance> getAllattendance() {
        return attendanceMapper.getAllattendance();
    }
}
