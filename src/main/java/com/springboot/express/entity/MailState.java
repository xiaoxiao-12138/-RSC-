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
public class MailState implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "邮件状态id" )
    @TableId(value = "id" , type = IdType.AUTO)
    private Integer id;
    @ApiModelProperty(value = "邮件状态" )
    private String state;


}
