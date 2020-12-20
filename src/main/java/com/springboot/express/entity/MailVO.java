package com.springboot.express.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MailVO extends Mail {
    @ApiModelProperty(value = "寄件人名字" )
    private String customerName;
    @ApiModelProperty(value = "寄件人手机" )
    private String customerPhone;
    @ApiModelProperty(value = "寄件人手机" )
    private String customerAddress;
    @ApiModelProperty(value = "收件区域" )
    private String receiveRegionName;
    @ApiModelProperty(value = "派件区域" )
    private String assignRegionName;
    @ApiModelProperty(value = "收件快递员" )
    private String receivePostmanName;
    @ApiModelProperty(value = "派件快递员" )
    private String assignPostmanName;
    @ApiModelProperty(value = "收件状态" )
    private String receiveStateName;
    @ApiModelProperty(value = "派件状态" )
    private String assignStateName;
}
