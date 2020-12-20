package com.springboot.express.service;

import com.springboot.express.entity.Admin;
import com.baomidou.mybatisplus.extension.service.IService;
import com.springboot.express.entity.Mail;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lbl
 * @since 2020-12-09
 */
public interface AdminService extends IService<Admin> {
  public Map getMailCount();

}
