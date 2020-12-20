package com.springboot.express.service;

import com.springboot.express.entity.Salary;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lbl
 * @since 2020-11-29
 */
public interface SalaryService extends IService<Salary> {
    public List<Salary> getSalaryByPostmanId(Integer pid);
}
