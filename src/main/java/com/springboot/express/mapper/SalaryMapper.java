package com.springboot.express.mapper;

import com.springboot.express.entity.Salary;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lbl
 * @since 2020-11-29
 */
public interface SalaryMapper extends BaseMapper<Salary> {
    public List<Salary> getSalaryByPostmanId(Integer pid);
}
