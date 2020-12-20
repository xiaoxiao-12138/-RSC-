package com.springboot.express.mapper;

import com.springboot.express.entity.Mail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springboot.express.entity.MailVO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lbl
 * @since 2020-11-29
 */

public interface MailMapper extends BaseMapper<Mail> {
    public List<MailVO> getAllMailVO();

    MailVO getAllMailVoById(Integer id);

    void updateReceiveMail(Mail mail);

    void updateAssignMail(Mail mail);
}
