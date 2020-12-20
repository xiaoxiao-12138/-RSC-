package com.springboot.express.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.springboot.express.entity.Attendance;
import com.springboot.express.entity.Mail;
import com.springboot.express.entity.MailVO;
import com.springboot.express.entity.Postman;
import com.springboot.express.service.impl.AttendanceServiceImpl;
import com.springboot.express.service.impl.MailServiceImpl;
import com.springboot.express.service.impl.PostmanServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lbl
 * @since 2020-11-29
 */
@Controller
@RequestMapping("/postman")
public class PostmanController {
    @Autowired
    private PostmanServiceImpl postmanService;
    @Autowired
    private MailServiceImpl mailService;
    @Autowired
    private AttendanceServiceImpl attendanceService;

    @RequestMapping("/login")
    public String postmanLogin(HttpSession session){
        Object hasLogin = session.getAttribute("hasLogin");
        if (hasLogin == null) {
            session.setAttribute("role","postman");
            session.setAttribute("hasLogin",false);
        }
        return "/postman/login";
    }


    //登录验证
    @RequestMapping("loginVerify")
    public String postmanloginVerify(Postman postman, Model model, HttpSession session){
        if (session.getAttribute("role").equals("postman") || (boolean) session.getAttribute("hasLogin") == false) {
            //查询构造器
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("phone",postman.getPhone());
            queryWrapper.eq("password", DigestUtils.md5DigestAsHex(postman.getPassword().getBytes()));

            //查询是否存在该快递员
            Postman postman1 = postmanService.getOne(queryWrapper);
            if (postman1 != null) {
                session.setAttribute("postman",postman1);
                session.setAttribute("role","postman");
                session.setAttribute("hasLogin",true);
                return "redirect:/postman/signPage/" + postman1.getId();
            }else {
                model.addAttribute("msg","手机号或密码错误！");
                return "/postman/login";
            }
        } else {
            model.addAttribute("msg","请先退出其他账号再登录！");
            return "/postman/login";
        }

    }


    //跳转到签到页面
    @RequestMapping("/signPage/{postmanId}")
    public String signPage(@PathVariable("postmanId") int postmanId,HttpSession session){
        //获取日历对象
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        //查询构造器(查出该快递员当天已经签到的记录)
        QueryWrapper<Attendance> queryWrapper = new QueryWrapper();
        queryWrapper.eq("pid",postmanId)
                .eq("year",year)
                .eq("month",month)
                .eq("day",day)
                .eq("duty",1);
        Attendance hasDaty = attendanceService.getOne(queryWrapper);
        if (hasDaty == null) {
            session.setAttribute("hasDuty",0);
        }else {
            session.setAttribute("hasDuty",1);
        }
        return "/postman/sign";
    }

    //快递员签到
    @RequestMapping("/sign/{postmanId}")
    public String postmanSign(@PathVariable("postmanId") int postmanId,HttpSession session){
        //获取日历对象
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        Attendance attendance = new Attendance();
        attendance.setPid(postmanId);
        attendance.setYear(year);
        attendance.setMonth(month);
        attendance.setDay(day);
        attendance.setDuty(1);
        attendanceService.save(attendance);

        //重定向跳回签到页面
        Postman postman = (Postman) session.getAttribute("postman");
        return "redirect:/postman/signPage/" + postman.getId();
    }


    //跳转到收件显示页面
    @RequestMapping("/receive/{postmanId}")
    public String postmanReceive(@PathVariable("postmanId") int postmanId, Model model){
        //查出所有邮件信息
        List<MailVO> allMailVO = mailService.getAllMailVO();

        //在所有邮件中找到属于该快递员待收件的邮件信息
        List<MailVO> MailVOs = new ArrayList<>();
        for (int i = 0; i < allMailVO.size(); i++) {
            if (allMailVO.get(i).getReceivePid() == postmanId && allMailVO.get(i).getReceiveState() == 1) {
                MailVOs.add(allMailVO.get(i));
            }
        }
        //在所有邮件中找到属于该快递员已经收件完成的邮件信息
        List<MailVO> MailVOeds = new ArrayList<>();
        for (int i = 0; i < allMailVO.size(); i++) {
            if ((allMailVO.get(i).getReceivePid() == postmanId) && (allMailVO.get(i).getReceiveState() == 2 || allMailVO.get(i).getReceiveState() == 3)) {
                MailVOeds.add(allMailVO.get(i));
            }
        }
        model.addAttribute("MailVOs",MailVOs);
        model.addAttribute("MailVOeds",MailVOeds);
        return "/postman/receive";
    }

    //跳转到派件显示页面
    @RequestMapping("/assign/{postmanId}")
    public String postmanAssign(@PathVariable("postmanId") int postmanId, Model model){
        //查出所有邮件信息
        List<MailVO> allMailVO = mailService.getAllMailVO();

        //在所有邮件中找到属于该快递员待派件的邮件信息
        List<MailVO> MailVOs = new ArrayList<>();
        for (int i = 0; i < allMailVO.size(); i++) {
            if (allMailVO.get(i).getAssignPid() == postmanId && allMailVO.get(i).getAssignState() == 1) {
                MailVOs.add(allMailVO.get(i));
            }
        }
        //在所有邮件中找到属于该快递员已经派件完成的邮件信息
        List<MailVO> MailVOeds = new ArrayList<>();
        for (int i = 0; i < allMailVO.size(); i++) {
            if ((allMailVO.get(i).getAssignPid() == postmanId) && (allMailVO.get(i).getAssignState() == 2 || allMailVO.get(i).getAssignState() == 3)) {
                MailVOeds.add(allMailVO.get(i));
            }
        }
        model.addAttribute("MailVOs",MailVOs);
        model.addAttribute("MailVOeds",MailVOeds);
        return "/postman/assign";
    }

    //跳转到所有邮件显示页面
    @RequestMapping("/mails/{postmanId}")
    public String postmanMails(@PathVariable("postmanId") int postmanId, Model model){
        //查出所有邮件信息
        List<MailVO> allMailVO = mailService.getAllMailVO();

        //在所有邮件中找到属于该快递员收件的邮件信息
        List<MailVO> receiveMailVOs = new ArrayList<>();
        for (int i = 0; i < allMailVO.size(); i++) {
            if ((allMailVO.get(i).getReceivePid() == postmanId) && (allMailVO.get(i).getReceiveState() != 0)) {
                receiveMailVOs.add(allMailVO.get(i));
            }
        }

        //在所有邮件中找到属于该快递员派件的邮件信息
        List<MailVO> assignMailVOs = new ArrayList<>();
        for (int i = 0; i < allMailVO.size(); i++) {
            if ((allMailVO.get(i).getAssignPid() == postmanId) && (allMailVO.get(i).getAssignState() != 0)) {
                assignMailVOs.add(allMailVO.get(i));
            }
        }
        model.addAttribute("receiveMailVOs",receiveMailVOs);
        model.addAttribute("assignMailVOs",assignMailVOs);
        return "/postman/mails";
    }

    //注销登录
    @GetMapping("/logout")
    public String customerLogout(HttpSession session){
        session.setAttribute("hasLogin",false);
        return "/postman/login";
    }

}

