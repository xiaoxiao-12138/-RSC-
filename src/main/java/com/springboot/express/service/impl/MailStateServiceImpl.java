package com.springboot.express.service.impl;

import com.springboot.express.entity.MailState;
import com.springboot.express.mapper.MailStateMapper;
import com.springboot.express.service.MailStateService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lbl
 * @since 2020-11-29
 */
@Service
public class MailStateServiceImpl extends ServiceImpl<MailStateMapper, MailState> implements MailStateService {

}
