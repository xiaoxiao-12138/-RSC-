package com.springboot.express.service;

import com.springboot.express.entity.Customer;
import com.baomidou.mybatisplus.extension.service.IService;
import com.springboot.express.entity.CustomerVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lbl
 * @since 2020-11-29
 */
public interface CustomerService extends IService<Customer> {
    public List<CustomerVO> customerVOS(Integer id);

    public List<CustomerVO> getCustomerMail(Integer id);
}
