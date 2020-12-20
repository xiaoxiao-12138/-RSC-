package com.springboot.express.mapper;

import com.springboot.express.entity.*;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lbl
 * @since 2020-11-29
 */
public interface PostmanMapper extends BaseMapper<Postman> {
    //查询所有员工的数据
   public List<PostmanInfo> getPostmanInfo( );
    //查询单个员工的数据
    public List<PostmanInfo> getOnePostmanInfo(@Param("pid")Integer pid , @Param("year")Integer year);

    public PostmanInfo getOnePostmanInfobyNameandMonth(@Param("pid")Integer pid,@Param("month")Integer month, @Param("year")Integer year);

    public List<Postman> getAllPostmans();

    List<Postman> getAllPostman(Integer region_id,Integer day);
}
