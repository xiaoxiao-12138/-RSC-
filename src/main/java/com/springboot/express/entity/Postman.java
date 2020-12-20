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
public class Postman implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "快递员id" )
    @TableId(value = "id" , type = IdType.AUTO)
    private Integer id;
    @ApiModelProperty(value = "快递员电话/账号" )
    private String phone;
    @ApiModelProperty(value = "快递员登录密码" )
    private String password;
    @ApiModelProperty(value = "快递员名字" )
    private String name;
    @ApiModelProperty(value = "快递员配送区域" )
    private Integer regionId;


}
