package com.springboot.express.controller;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.springboot.express.entity.Customer;
import com.springboot.express.entity.CustomerVO;
import com.springboot.express.mapper.PostmanMapper;
import com.springboot.express.service.SalaryService;
import com.springboot.express.service.impl.CustomerServiceImpl;
import com.springboot.express.service.impl.SalaryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

import static javax.swing.text.html.CSS.getAttribute;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lbl
 * @since 2020-11-25
 */
@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerServiceImpl customerService;
    @Autowired
    SalaryServiceImpl salaryService;
    //登录页
    @RequestMapping("/login")
    public String login(HttpSession session){
        Object hasLogin = session.getAttribute("hasLogin");
        if (hasLogin == null) {
            session.setAttribute("role","customer");
            session.setAttribute("hasLogin",false);
        }

        return "/customer/login";
    }

    //登录验证
    @RequestMapping("/loginVerify")
    public String login(Customer customer, Model model,HttpSession session){
        if (session.getAttribute("role").equals("customer") || (boolean) session.getAttribute("hasLogin") == false) {
            //查询构造器
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("phone",customer.getPhone());
            queryWrapper.eq("password", DigestUtils.md5DigestAsHex(customer.getPassword().getBytes()));
            System.out.println("没有拦截");
            //查询是否存在该用户记录
            Customer customer1 = customerService.getOne(queryWrapper);
            if (customer1 != null) {
                session.setAttribute("customerName",customer1.getName());
                session.setAttribute("customerId",customer1.getId());
                //表示已经登录
                session.setAttribute("role","customer");
                session.setAttribute("hasLogin",true);
                return "redirect:/customer/index";
            }
            model.addAttribute("msg","手机号或密码错误！");
            return "/customer/login";
        }else {
            model.addAttribute("msg","请先退出其他账号再登录！");
            return "/customer/login";
        }

    }

    //首页
    @RequestMapping("/index")
    public String index(){
        return "/customer/index";
    }

    //注册页面
    @RequestMapping("/register")
    public String register(){
        return "/customer/register";
    }

    //添加用户
    @RequestMapping("/addCustomer")
    public String addCustomer(Customer customer, HttpSession session,Model model){
        if (customer.getPhone().length() < 11) {
            model.addAttribute("msg","请输入正确手机号格式！");
            return "/customer/register";
        }
        //当该用户不存在时则创建
        if (customerService.getOne(new QueryWrapper<Customer>().eq("phone",customer.getPhone())) == null) {
            //密码加密
            customer.setPassword(DigestUtils.md5DigestAsHex(customer.getPassword().getBytes()));
            //保存用户
            model.addAttribute("msg","注册成功，请登录！");
            customerService.save(customer);
            //session.setAttribute("msg","注册成功，请登录！");
            return "/customer/login";

        }else { //已存在该用户
            model.addAttribute("msg","该用户已存在，请重新注册！");
            //session.setAttribute("msg","该用户已存在，请重新注册！");
            return "/customer/register";
        }

    }

    //邮件查询
    @GetMapping("/customerinfo")
    public String customerInfo(Map<String,Object> map,HttpSession session){
        int customerid = (int) session.getAttribute("customerId");
        System.out.println(customerid);
        //List<CustomerVO> customerVOS = customerService.customerVOS(customerid);
        List<CustomerVO> customerVOS = customerService.getCustomerMail(customerid);
        session.setAttribute("customers",customerVOS);
        return "/customer/list";
    }

    //跳转个人信息修改页面
    @GetMapping("/alter/{customerId}")
    public String customerAlter(@PathVariable("customerId") int customerId,Model model){
        Customer customer = customerService.getById(customerId);
        model.addAttribute("customer",customer);
        return "/customer/alter";
    }

    //个人信息修改
    @RequestMapping("/customerUpdate")
    public String customerUpdate(Customer customer,Model model,HttpSession session){
        //若没修改密码，则使用原先的密码
        if (customer.getPassword().length() <= 0) {
            customer.setPassword(customerService.getById(customer.getId()).getPassword());
        }else { //密码使用MD5方式加密
            customer.setPassword(DigestUtils.md5DigestAsHex(customer.getPassword().getBytes()));
        }
        boolean b = customerService.updateById(customer);
        //判断是否保存成功
        if (b) {
            session.setAttribute("customerName",customer.getName());
            return "/customer/index";
        } else {
            model.addAttribute("msg","保存失败！");
            return "/customer/alter";
        }
    }

    @GetMapping("/post")
    public String customerPost(){
        return "/customer/post";
    }

    @GetMapping("/logout")
    public String customerLogout(HttpSession session){
        session.setAttribute("hasLogin",false);
        return "/customer/login";
    }
}

