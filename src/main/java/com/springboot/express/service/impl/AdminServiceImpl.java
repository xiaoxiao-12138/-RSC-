package com.springboot.express.service.impl;

import com.springboot.express.entity.Admin;
import com.springboot.express.mapper.AdminMapper;
import com.springboot.express.service.AdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lbl
 * @since 2020-12-09
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

    @Override
    public Map getMailCount() {
        return null;
    }
}
