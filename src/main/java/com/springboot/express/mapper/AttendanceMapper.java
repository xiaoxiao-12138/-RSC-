package com.springboot.express.mapper;

import com.springboot.express.entity.Attendance;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lbl
 * @since 2020-12-13
 */
public interface AttendanceMapper extends BaseMapper<Attendance> {
    public List<Attendance> getAllattendance ();
}
