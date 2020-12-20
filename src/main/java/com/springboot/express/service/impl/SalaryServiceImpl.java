package com.springboot.express.service.impl;

import com.springboot.express.entity.Salary;
import com.springboot.express.mapper.SalaryMapper;
import com.springboot.express.service.SalaryService;
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
 * @since 2020-11-29
 */
@Service
public class SalaryServiceImpl extends ServiceImpl<SalaryMapper, Salary> implements SalaryService {
    @Autowired
    private SalaryMapper salaryMapper;

    public List<Salary> getSalaryByPostmanId(Integer pid){
        return salaryMapper.getSalaryByPostmanId(1);
    }
}
