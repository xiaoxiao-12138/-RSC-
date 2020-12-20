package com.springboot.express.service.impl;

import com.springboot.express.entity.Customer;
import com.springboot.express.entity.CustomerVO;
import com.springboot.express.entity.PostmanInfo;
import com.springboot.express.entity.Workload;
import com.springboot.express.mapper.CustomerMapper;
import com.springboot.express.mapper.PostmanMapper;
import com.springboot.express.mapper.WorkloadMapper;
import com.springboot.express.service.CustomerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Param;
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
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements CustomerService {
    @Autowired
    private CustomerMapper customerMapper;



    @Override
    public List<CustomerVO> customerVOS(Integer id) {
        return null;
    }

    @Override
    public List<CustomerVO> getCustomerMail(Integer id) {
        return customerMapper.getCustomerMail(id);
    }


}
