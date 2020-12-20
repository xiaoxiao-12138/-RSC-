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
public class Region implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "区域id" )
    @TableId(value = "id" , type = IdType.AUTO)
    private Integer id;
    @ApiModelProperty(value = "区域名" )
    private String region;


}
