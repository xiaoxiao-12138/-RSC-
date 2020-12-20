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
public class Salary implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "工资id" )
    @TableId(value = "id" , type = IdType.AUTO)
    private Integer id;
    @ApiModelProperty(value = "快递员id" )
    private Integer pid;
    @ApiModelProperty(value = "年" )
    private Integer year;
    @ApiModelProperty(value = "月" )
    private Integer month;
    @ApiModelProperty(value = "底薪" )
    private Double basic;
    @ApiModelProperty(value = "提成" )
    private Double assessment;
    @ApiModelProperty(value = "总工资" )
    private Double total;


}
