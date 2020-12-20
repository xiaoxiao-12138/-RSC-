package com.springboot.express.service.impl;

import com.springboot.express.entity.Mail;
import com.springboot.express.entity.MailVO;
import com.springboot.express.mapper.MailMapper;
import com.springboot.express.service.MailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lbl
 * @since 2020-11-29
 */
@Service
public class MailServiceImpl extends ServiceImpl<MailMapper, Mail> implements MailService {
    @Autowired
    private MailMapper mailMapper;

    //获取所有邮件信息
    @Override
    public List<MailVO> getAllMailVO() {
        return mailMapper.getAllMailVO();
    }

    //获取所有邮件信息通过邮件id查询
    @Override
    public MailVO getAllMailVoById(Integer id) {
        return mailMapper.getAllMailVoById(id);
    }

    //根据邮件的id更改邮件表中的收件邮递员的id
    @Override
    public void updateReceiveMail(Mail mail) {
        mailMapper.updateReceiveMail(mail);
    }

    //根据邮件的id更改邮件表中的派件邮递员的id
    @Override
    public void updateAssignMail(Mail mail) {
        mailMapper.updateAssignMail(mail);
    }
}
