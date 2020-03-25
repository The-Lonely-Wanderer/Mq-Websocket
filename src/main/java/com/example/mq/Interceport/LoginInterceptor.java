package com.example.mq.Interceport;

import com.example.mq.po.User;
import com.example.mq.service.UserService;
import com.example.mq.util.FinalParmarConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;
import java.util.HashMap;

@Configuration
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private UserService userService;
    /**
     * 拦截器
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String username = request.getParameter("userName");
        if(StringUtils.isEmpty(username)){
            username = (String)request.getSession().getAttribute("userName");
        }
        if (StringUtils.isEmpty(username)) {
            request.setAttribute("USERNAEM_ISMUST", FinalParmarConfig.USERNAEM_ISMUST);
            request.getRequestDispatcher("/login.html").forward(request, response);
            //请求被拦截，执行转发路径
            return false;
        } else {
            //执行后面的请求
            request.getSession().setAttribute("userName",username);
            return true;
        }
    }
}
