package com.springboot.express.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * @author lbl
 * @Description:
 * @since 2020-11-29
 */
@ApiModel(value = "" )
@Data
@EqualsAndHashCode(callSuper = false)
public class Mail implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "邮件表id" )
    @TableId(value = "id" , type = IdType.AUTO)
    private Integer id;
    @ApiModelProperty(value = "收件人名字" )
    private String addresseeName;
    @ApiModelProperty(value = "收件人电话" )
    private String addresseePhone;
    @ApiModelProperty(value = "收件人地址" )
    private String address;
    @ApiModelProperty(value = "寄件人id" )
    private Integer customerId;
    @ApiModelProperty(value = "收件区域id" )
    private Integer receiveRid;
    @ApiModelProperty(value = "派件区域id" )
    private Integer assignRid;
    @ApiModelProperty(value = "收件快递员id" )
    private Integer receivePid;
    @ApiModelProperty(value = "寄件快递员id" )
    private Integer assignPid;
    @ApiModelProperty(value = "收件状态" )
    private Integer receiveState;
    @ApiModelProperty(value = "派件状态" )
    private Integer assignState;
    @ApiModelProperty(value = "收件次数" )
    private Integer receiveFrequency;
    @ApiModelProperty(value = "派件次数" )
    private Integer assignFrequency;
    @ApiModelProperty(value = "原因" )
    private String reason;
    @ApiModelProperty(value = "下单时间" )
    private Date createTime;
    @ApiModelProperty(value = "收件时间" )
    private Date receiveTime;
    @ApiModelProperty(value = "派件时间" )
    private Date assignTime;
    @ApiModelProperty(value = "是否删除订单" )
    private Integer deleteState;
    @ApiModelProperty(value = "分配收件时间" )
    private Date distributeReceiveTime;
    @ApiModelProperty(value = "分配派件时间" )
    private Date distributeAssignTime;


}
