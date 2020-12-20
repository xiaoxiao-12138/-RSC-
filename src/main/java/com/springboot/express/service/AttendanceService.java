package com.springboot.express.service;

import com.springboot.express.entity.Attendance;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lbl
 * @since 2020-12-13
 */
public interface AttendanceService extends IService<Attendance> {
    public List<Attendance> getAllattendance ();
}
