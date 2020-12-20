package com.springboot.express.controller;


import com.springboot.express.service.impl.SalaryServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/salary")
public class SalaryController {
/*    private SalaryServiceImpl salaryService;
    @RequestMapping("/login")
    public String login()
    {
        List list=salaryService.getSalaryByPostmanId(1);
        System.out.print(list);
        return "/customer/login";
    }*/
}

