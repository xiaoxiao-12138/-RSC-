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
public class Workload implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "工作量表id" )
    @TableId(value = "id" , type = IdType.AUTO)
    private Integer id;
    @ApiModelProperty(value = "快递员id" )
    private Integer pid;
    @ApiModelProperty(value = "年" )
    private Integer year;
    @ApiModelProperty(value = "月" )
    private Integer month;
    @ApiModelProperty(value = "日" )
    private Integer date;
    @ApiModelProperty(value = "收件工作量" )
    private Integer receiveWorkload;
    @ApiModelProperty(value = "派件工作量" )
    private Integer assignWorkload;
    @ApiModelProperty(value = "预期工作量" )
    private Integer expectationWorkload;
    @ApiModelProperty(value = "全部工作量" )
    private Integer totalWorkload;
    @ApiModelProperty(value = "收件失败数量" )
    private Integer receiveFault;
    @ApiModelProperty(value = "派件失败数量" )
    private Integer assignFault;
    @ApiModelProperty(value = "全部失败数量" )
    private Integer totalFault;


}
