package com.springboot.express.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.springboot.express.entity.Customer;
import com.springboot.express.entity.Mail;
import com.springboot.express.entity.Postman;
import com.springboot.express.entity.Workload;
import com.springboot.express.service.impl.CustomerServiceImpl;
import com.springboot.express.service.impl.MailServiceImpl;
import com.springboot.express.service.impl.WorkloadServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import java.util.Calendar;
import java.util.Date;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lbl
 * @since 2020-11-29
 */
@Controller
@RequestMapping("/mail")
public class MailController {

    @Autowired
    private MailServiceImpl mailService;
    @Autowired
    private CustomerServiceImpl customerService;
    @Autowired
    private WorkloadServiceImpl workloadService;

    //添加邮件
    @RequestMapping("/addMail")
    public String addMailInfo(Mail mail,HttpSession session){
        Integer customerid = (Integer) session.getAttribute("customerId");
        Customer customer = customerService.getById(customerid);
        Date date = new Date();
        mail.setCustomerId(customer.getId());
        mail.setReceiveRid(customer.getRegionId());
        mail.setCreateTime(date);
        mail.setReceivePid(666);
        mail.setAssignPid(666);
        mail.setReceiveState(0);
        mail.setAssignState(0);
        boolean b = mailService.saveOrUpdate(mail);
        session.setAttribute("addMailSuccess","添加成功");
        return "/customer/post";
    }

    //收件员收件成功
    @RequestMapping("/updateReceivePidSuccess/{id}")
    public String updateReceivePidSuccess(@PathVariable("id") int id, HttpSession session){
        //获取日历对象
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        //更新构造器
        UpdateWrapper<Mail> mailUpdateWrapper = new UpdateWrapper<>();
        mailUpdateWrapper.eq("id",id)
                .eq("receive_state",1);
        //更新mail表收件状态——已完成
        Mail mail = new Mail();
        mail.setReceiveState(2);
        mail.setReceiveTime(new Date());
        mailService.update(mail,mailUpdateWrapper);

        //在session中获取该快递员对象
        Postman postman = (Postman) session.getAttribute("postman");

        //查询构造器
        QueryWrapper<Workload> workloadQueryWrapper = new QueryWrapper<>();
        workloadQueryWrapper.eq("pid",postman.getId())
                .eq("year",year)
                .eq("month",month)
                .eq("date",day);
        //查询是否存在今天该快递员的工作量
        Workload workload = workloadService.getOne(workloadQueryWrapper);
        //如果没有工作记录,则插入新的记录
        if (workload == null) {
            Workload workloadInsert = new Workload();
            workloadInsert.setPid(postman.getId());
            workloadInsert.setYear(year);
            workloadInsert.setMonth(month);
            workloadInsert.setDate(day);
            workloadInsert.setReceiveWorkload(1);
            workloadService.save(workloadInsert);
        }else { //若已经存在记录，则更新记录
            //更新构造器
            UpdateWrapper<Workload> workloadUpdateWrapper = new UpdateWrapper<>();
            workloadUpdateWrapper.eq("pid",postman.getId())
                    .eq("year",year)
                    .eq("month",month)
                    .eq("date",day)
                    .setSql("receive_workload = receive_workload + 1");
            //更新workload表，失败收件的字段receive_fault加1
            workloadService.update(workload,workloadUpdateWrapper);
        }

        //重定向跳回收件页面
        return "redirect:/postman/receive/" + postman.getId();
    }

    //收件员收件失败
    @RequestMapping("/updateReceivePidFail/{id}")
    public String updateReceivePidFail(@PathVariable("id") int id, HttpSession session){
        //获取日历对象
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        //更新构造器
        UpdateWrapper<Mail> mailUpdateWrapper = new UpdateWrapper<>();
        mailUpdateWrapper.eq("id",id)
                .eq("receive_state",1);
        //更新mail表收件状态——失败
        Mail mail = new Mail();
        mail.setReceiveState(3);
        mail.setReceiveTime(new Date());
        mailService.update(mail,mailUpdateWrapper);

        //在session中获取该快递员对象
        Postman postman = (Postman) session.getAttribute("postman");

        //查询构造器
        QueryWrapper<Workload> workloadQueryWrapper = new QueryWrapper<>();
        workloadQueryWrapper.eq("pid",postman.getId())
                .eq("year",year)
                .eq("month",month)
                .eq("date",day);
        //查询是否存在今天该快递员的工作量
        Workload workload = workloadService.getOne(workloadQueryWrapper);
        //如果没有工作记录,则插入新的记录
        if (workload == null) {
            Workload workloadInsert = new Workload();
            workloadInsert.setPid(postman.getId());
            workloadInsert.setYear(year);
            workloadInsert.setMonth(month);
            workloadInsert.setDate(day);
            workloadInsert.setReceiveFault(1);
            workloadService.save(workloadInsert);
        }else { //若已经存在记录，则更新记录
            //更新构造器
            UpdateWrapper<Workload> workloadUpdateWrapper = new UpdateWrapper<>();
            workloadUpdateWrapper.eq("pid",postman.getId())
                    .eq("year",year)
                    .eq("month",month)
                    .eq("date",day)
                    .setSql("receive_fault = receive_fault + 1");
            //更新workload表，失败收件的字段receive_fault加1
            workloadService.update(workload,workloadUpdateWrapper);
        }

        //重定向跳回收件页面
        return "redirect:/postman/receive/" + postman.getId();
    }

    //派件员派件成功
    @RequestMapping("/updateAssignPidSuccess/{id}")
    public String updateAssignPidSuccess(@PathVariable("id") int id, HttpSession session){
        //获取日历对象
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        //更新构造器
        UpdateWrapper<Mail> mailUpdateWrapper = new UpdateWrapper<>();
        mailUpdateWrapper.eq("id",id)
                .eq("assign_state",1);
        //更新mail表收件状态——已完成
        Mail mail = new Mail();
        mail.setAssignState(2);
        mail.setAssignTime(new Date());
        mailService.update(mail,mailUpdateWrapper);

        //在session中获取该快递员对象
        Postman postman = (Postman) session.getAttribute("postman");

        //查询构造器
        QueryWrapper<Workload> workloadQueryWrapper = new QueryWrapper<>();
        workloadQueryWrapper.eq("pid",postman.getId())
                .eq("year",year)
                .eq("month",month)
                .eq("date",day);
        //查询是否存在今天该快递员的工作量
        Workload workload = workloadService.getOne(workloadQueryWrapper);
        //如果没有工作记录,则插入新的记录
        if (workload == null) {
            Workload workloadInsert = new Workload();
            workloadInsert.setPid(postman.getId());
            workloadInsert.setYear(year);
            workloadInsert.setMonth(month);
            workloadInsert.setDate(day);
            workloadInsert.setAssignWorkload(1);
            workloadService.save(workloadInsert);
        }else { //若已经存在记录，则更新记录
            //更新构造器
            UpdateWrapper<Workload> workloadUpdateWrapper = new UpdateWrapper<>();
            workloadUpdateWrapper.eq("pid",postman.getId())
                    .eq("year",year)
                    .eq("month",month)
                    .eq("date",day)
                    .setSql("assign_workload = assign_workload + 1");
            //更新workload表，失败收件的字段assign_workload加1
            workloadService.update(workload,workloadUpdateWrapper);
        }

        //重定向跳回收件页面
        return "redirect:/postman/assign/" + postman.getId();
    }

    //派件员派件失败
    @RequestMapping("/updateAssignPidFail/{id}")
    public String updateAssignPidFail(@PathVariable("id") int id, HttpSession session){
        //获取日历对象
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        //在session中获取该快递员对象
        Postman postman = (Postman) session.getAttribute("postman");

        //判断该mail是否派件失败第三次
        Mail mail = mailService.getById(id);
        if (mail.getAssignFrequency() < 2) {
            //更新构造器,更新mail表收件状态——重新归为等待分配状态
            UpdateWrapper<Mail> mailUpdateWrapper = new UpdateWrapper<>();
            mailUpdateWrapper.eq("id",id)
                    .set("assign_state",0)
                    .set("assign_pid",666)
                    .setSql("assign_frequency = assign_frequency + 1");
            mailService.update(mail,mailUpdateWrapper);
            //查询构造器，查看Workload表是否有这一天该快递员的记录
            QueryWrapper<Workload> workloadQueryWrapper = new QueryWrapper<>();
            workloadQueryWrapper.eq("pid",postman.getId())
                    .eq("year",year)
                    .eq("month",month)
                    .eq("date",day);
            //查询是否存在今天该快递员的工作量
            Workload workload = workloadService.getOne(workloadQueryWrapper);
            //如果没有工作记录,则插入新的记录
            if (workload == null) {
                Workload workloadInsert = new Workload();
                workloadInsert.setPid(postman.getId());
                workloadInsert.setYear(year);
                workloadInsert.setMonth(month);
                workloadInsert.setDate(day);
                workloadInsert.setAssignFault(1);
                workloadService.save(workloadInsert);
            }else { //若已经存在记录，则更新记录
                //更新构造器
                UpdateWrapper<Workload> workloadUpdateWrapper = new UpdateWrapper<>();
                workloadUpdateWrapper.eq("pid",postman.getId())
                        .eq("year",year)
                        .eq("month",month)
                        .eq("date",day)
                        .setSql("assign_fault = assign_fault + 1");
                //更新workload表，失败收件的字段assign_fault加1
                workloadService.update(workload,workloadUpdateWrapper);
            }
        }else { //派件第三次失败
            //更新构造器,更新mail表收件状态——重新归为等待分配状态
            UpdateWrapper<Mail> mailUpdateWrapper = new UpdateWrapper<>();
            mailUpdateWrapper.eq("id",id)
                    .eq("assign_pid",postman.getId())
                    .set("assign_state",3)
                    .setSql("assign_frequency = assign_frequency + 1");
            mail.setAssignTime(new Date());
            mailService.update(mail,mailUpdateWrapper);
        }

        //重定向跳回收件页面
        return "redirect:/postman/assign/" + postman.getId();
    }
}

