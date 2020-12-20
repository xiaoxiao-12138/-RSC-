package com.springboot.express.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;

@Data
public class CustomerVO {

    private int id;
    private String addresseeName;
    private String addresseePhone;
    private int assignRid;
    private String addresseeAddress;
    private int receiveState;
    private int assignState;
    private Date receiveTime;
    private Date assignTime;

    //其他实体属性
    //用户
    @TableField(exist = false)
    private Customer customer;
    //收件员
    @TableField(exist = false)  //不是表字段
    private Postman receivePostman;
    //派件员
    @TableField(exist = false)  //不是表字段
    private Postman assignPostman;
}
