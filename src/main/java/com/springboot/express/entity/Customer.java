package com.springboot.express.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author lbl
 * @Description:
 * @since 2020-11-29
 */
@ApiModel(value = "" )
@Data
@EqualsAndHashCode(callSuper = false)
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "寄件人id" )
    @TableId(value = "id" , type = IdType.AUTO)
    private Integer id;
    @ApiModelProperty(value = "寄件人电话/账号" )
    private String phone;
    @ApiModelProperty(value = "寄件人密码" )
    private String password;
    @ApiModelProperty(value = "寄件人名字" )
    private String name;
    @ApiModelProperty(value = "寄件人地址" )
    private String address;
    @ApiModelProperty(value = "寄件人所在区域id" )
    private Integer regionId;


}
