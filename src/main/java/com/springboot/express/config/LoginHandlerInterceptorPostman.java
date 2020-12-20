package com.springboot.express.config;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//自定义拦截器，权限控制
public class LoginHandlerInterceptorPostman implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        System.out.println("Postman路径：" + request.getRequestURL());
        //获取session域中的角色以及登录状态
        String role = (String)request.getSession().getAttribute("role");
        boolean hasLogin = (boolean) request.getSession().getAttribute("hasLogin");

        System.out.println("Postman中的role：" + role);
        System.out.println("Postman中的hasLogin：" + hasLogin);
        if (role.equals("postman") && hasLogin == false) {
            request.setAttribute("msg","没有权限，请先登录");
            //请求转发
            request.getRequestDispatcher("/postman").forward(request,response);
            return false;
        }else if (role.equals("postman") && hasLogin == true){
            return true;
        } else {
            response.sendRedirect("/error/404");
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
