package com.springboot.express.mapper;

import com.springboot.express.entity.Customer;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springboot.express.entity.CustomerVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lbl
 * @since 2020-11-29
 */
@Mapper
public interface CustomerMapper extends BaseMapper<Customer> {

     @Select("SELECT DISTINCT a.name,a.phone,a.region_id,a.address,b.id,b.addressee_name,b.addressee_phone,b.receive_rid,b.address addressee_address,b.receive_state,b.assign_state,b.receive_time,b.assign_time,b.receive_pid receivePostman,b.assign_pid assignPostman from customer a,mail b where a.id=b.customer_id and a.id=#{id}")
     List<CustomerVO> customerVO(@Param("id") Integer id);

     List<CustomerVO> getCustomerMail(int id);
}
