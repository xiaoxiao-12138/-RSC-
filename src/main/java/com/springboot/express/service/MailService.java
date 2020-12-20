package com.springboot.express.service;

import com.springboot.express.entity.Mail;
import com.baomidou.mybatisplus.extension.service.IService;
import com.springboot.express.entity.MailVO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lbl
 * @since 2020-11-29
 */
public interface MailService extends IService<Mail> {

    //获取所有邮件信息
    List<MailVO> getAllMailVO();

    //获取所有邮件信息通过邮件id查询
    MailVO getAllMailVoById(Integer id);

    //根据邮件的id更改邮件表中的收件邮递员的id
    void updateReceiveMail(Mail mail);

    //根据邮件的id更改邮件表中的派件邮递员的id
    void updateAssignMail(Mail mail);
}
