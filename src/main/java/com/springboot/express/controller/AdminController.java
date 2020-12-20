package com.springboot.express.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.springboot.express.entity.Admin;
import com.springboot.express.entity.Mail;
import com.springboot.express.entity.MailVO;
import com.springboot.express.entity.Postman;
import com.springboot.express.service.impl.AdminServiceImpl;
import com.springboot.express.service.impl.MailServiceImpl;
import com.springboot.express.service.impl.PostmanServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import com.springboot.express.entity.*;
import com.springboot.express.service.MailService;
import com.springboot.express.service.impl.PostmanServiceImpl;
import com.springboot.express.service.impl.WorkloadServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lbl
 * @since 2020-12-09
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    PostmanServiceImpl postmanService;
    @Autowired
    WorkloadServiceImpl workloadService;
    @Autowired
    AdminServiceImpl adminService;
    @Autowired
    MailServiceImpl mailService;

    //按月查询邮递员邮件信息
    @RequestMapping("/findWorkloadofmonth")
    public String  findWorkloadofmonth(HttpServletRequest request, ModelMap mmap, HttpSession session, Workload workload) {
        //用于显示月份列表
        ArrayList<Object> monthList = new ArrayList();
        for (int i = 1; i <= 12; i++) {
            monthList.add(i);
        }
        session.setAttribute("monthList", monthList);
        if (workload.getMonth() == null) {
            workload.setMonth(12);
        }

        //ArrayList<Object> result=postmanService.getOnePostmanInfobyNameandMonth(workload.getMonth());
        ArrayList<WorkloadVO> result = workloadService.getWorkloadVObyMonthforlist(workload.getMonth());
        System.out.println(result);
         if (result.isEmpty()){

            HashMap<String, Object> postmanmap = new HashMap<>();
            postmanmap.put("names", "此快递员没有1");
            postmanmap.put("assignWorkloads", 1);
            postmanmap.put("receiveWorkloads", 2);
            postmanmap.put("Month", 3);
            postmanmap.put("receiveFaults", 4);
            postmanmap.put("assignFaults", 5);
            postmanmap.put("maxWorkloads", 6);
            postmanmap.put("minWorkloads", 7);

            HashMap<String, Object> postmanmap2 = new HashMap<>();
            postmanmap2.put("names", "此快递员没有2");
            postmanmap2.put("assignWorkloads", 7);
            postmanmap2.put("receiveWorkloads", 6);
            postmanmap2.put("Month", 5);
            postmanmap2.put("receiveFaults", 4);
            postmanmap2.put("assignFaults", 3);
            postmanmap2.put("maxWorkloads", 2);
            postmanmap2.put("minWorkloads", 1);

            HashMap<String, Object> postmanmap3 = new HashMap<>();
            postmanmap3.put("names", "此快递员没有2");
            postmanmap3.put("assignWorkloads", 7);
            postmanmap3.put("receiveWorkloads", 6);
            postmanmap3.put("Month", 5);
            postmanmap3.put("receiveFaults", 8);
            postmanmap3.put("assignFaults", 9);
            postmanmap3.put("maxWorkloads", 2);
            postmanmap3.put("minWorkloads", 1);

        }
        mmap.put("list", result);
        return "/admin/findWorkloadofmonth";
    }



        //邮件信息
    @RequestMapping("/mailinfo")
    public String adminMaillist(HttpServletRequest request, ModelMap mmap, HttpSession session, Workload workload){
        //用于显示年份列表
        ArrayList<Object> yearList =new ArrayList();
        yearList.add(2020);
        yearList.add(2021);
        session.setAttribute("yearList",yearList);
        if (workload.getYear()==null){
            workload.setYear(2020);
        }
        ArrayList<Object> result= workloadService.getAllworkloadbyMonth(workload.getYear());
        mmap.put("list",result);

        //System.out.println("获取的result值"+result);
        return "/admin/maillist";
    }

    //快递员信息
    @RequestMapping("/postmaninfo")
    public String adminPostmanList(ModelMap mmap, HttpSession session,Attendance attendance){
        //快递员列表
        ArrayList<Object> postmanList =new ArrayList();
        //用于显示月份列表
        ArrayList<Object> monthList =new ArrayList();
        for (int i=1;i<=12;i++){
            monthList.add(i);
        }
        session.setAttribute("monthList",monthList);
        //用于显示年份列表
        ArrayList<Object> yearList =new ArrayList();
            yearList.add(2020);
            yearList.add(2021);
            session.setAttribute("yearList",yearList);
        //获取所有快递员的信息 用于显示下拉列表
        List<Postman> allpostmanInfo = postmanService.getAllPostmans();
        session.setAttribute("allpostmanInfo",allpostmanInfo);

        //判断是否为空
        if (attendance.getPid()==null||attendance.getMonth()==null){
            attendance.setPid(1);
            attendance.setMonth(11);
            attendance.setYear(2020);
        }

        //获取所有快递员信息
        List<PostmanInfo> postmanInfo = postmanService.getPostmanInfo();
        session.setAttribute("postmans",postmanInfo);
        for (Object object : postmanInfo){
            System.out.println(object);
        }
//        System.out.println("postmID:"+attendance.getPid()+"月份："+attendance.getMonth()+"年份："+attendance.getYear());
        //显示柱状图的具体信息
        List list2=postmanService.getOnePostmanInfobyNameAndMonthforlist(attendance.getPid(),attendance.getMonth(),attendance.getYear());
        session.setAttribute("onepostman",list2);



        //根据快递员id 和月份查询信息
       ArrayList<Object> result=postmanService.getOnePostmanInfobyNameandMonth(attendance.getPid(),attendance.getMonth(),attendance.getYear());
        //当查询某个月数据为空时
        if (result.isEmpty()){
            HashMap<String, Object> postmanmap = new HashMap<>();
            postmanmap.put("names", "此快递员没有");
            postmanmap.put("years",attendance.getYear());
            postmanmap.put("Month", attendance.getMonth());
            result.add(postmanmap);
        }
       //把数据传到前端读取显示柱形图
        mmap.put("postmanlist",result);

        return  "/admin/postmanlist";
    }



    @RequestMapping("/login")
    public String AdminLogin(HttpSession session){
        Object hasLogin = session.getAttribute("hasLogin");
        if (hasLogin == null) {
            session.setAttribute("role","admin");
            session.setAttribute("hasLogin",false);
        }
        return "/admin/login";
    }

    //登陆验证
    @PostMapping("/loginVerify")
    public String login(Admin admin, HttpSession session, Model model){
        if (session.getAttribute("role").equals("admin") || (boolean) session.getAttribute("hasLogin") == false) {
            //查询构造器
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("name",admin.getName());
            queryWrapper.eq("password", DigestUtils.md5DigestAsHex(admin.getPassword().getBytes()));

            //查询判断该用户是否存在
            Admin one = adminService.getOne(queryWrapper);

            if(one != null){
                session.setAttribute("adminName",one.getName());
                session.setAttribute("adminId",one.getId());
                session.setAttribute("role","admin");
                session.setAttribute("hasLogin",true);
                return "redirect:/admin/mailinfo";
            }
            model.addAttribute("msg","用户名或密码错误");
            return "/admin/login";
        }else {
            model.addAttribute("msg","请先退出其他账号再登录！");
            return "/admin/login";
        }

    }

    //跳转到收件页面
    @RequestMapping("/receiveList")
    public String receiveList(HttpSession session){
        List<MailVO> allMailVO = mailService.getAllMailVO();
        session.setAttribute("receiveMail",allMailVO);
        return "/admin/receiveList";
    }

    //跳转到分配收件快递员的页面
    @GetMapping("/showReceivePostman/{id}")
    public String showReceivePostman(@PathVariable("id") Integer id,Model model,HttpSession session){
        MailVO mailVO = mailService.getAllMailVoById(id);
        //System.out.println(mailVO);
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        List<Postman> postmanList = postmanService.getAllPostman(mailVO.getReceiveRid(),day);
        //System.out.println(postmanList);
        session.setAttribute("postmanList",postmanList);
        model.addAttribute("receivePostman",mailVO);
        return "/admin/updateReceive";
    }

    //根据邮件id更新mail中的收件快递员的id
    @RequestMapping("/updateReceiveMail")
    public String updateReceivePostman(Mail mail){
        System.out.println(mail);
        Date date = new Date();
        mail.setDistributeReceiveTime(date);
        mailService.updateReceiveMail(mail);
        return "redirect:/admin/receiveList";
    }

    //跳转到派件页面
    @RequestMapping("/assignList")
    public String assignList(HttpSession session){
        List<MailVO> allMailVO = mailService.getAllMailVO();
        session.setAttribute("assignMail",allMailVO);
        return "/admin/assignList";
    }

    //跳转到分配派件快递员的页面
    @RequestMapping("/showAssignPostman/{id}")
    public String showAssignPostman(@PathVariable("id") Integer id,HttpSession session,Model model){
        MailVO mailVO = mailService.getAllMailVoById(id);
        //System.out.println(mailVO);
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        List<Postman> assignPostman = postmanService.getAllPostman(mailVO.getAssignRid(),day);
        session.setAttribute("assignPostmanList",assignPostman);
        model.addAttribute("assignPostman",mailVO);
        return "/admin/updateAssign";
    }

    //更新mail表中的派件快递员的id
    @RequestMapping("/updateAssignMail")
    public String updateAssignPostman(Mail mail){
        //System.out.println(mail);
        Date date = new Date();
        mail.setDistributeAssignTime(date);
        mailService.updateAssignMail(mail);
        return "redirect:/admin/assignList";
    }

    //查看分派情况
    @RequestMapping("/allMailInfo")
    public String allMailInfo(HttpSession session){
        List<MailVO> allMailVO = mailService.getAllMailVO();
        System.out.println(allMailVO);
        session.setAttribute("allMailVo",allMailVO);
        return "/admin/list";
    }

    //注销登录
    @GetMapping("/logout")
    public String customerLogout(HttpSession session){
        session.setAttribute("hasLogin",false);
        return "/admin/login";
    }
}

