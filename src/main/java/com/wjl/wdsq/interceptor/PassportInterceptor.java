package com.wjl.wdsq.interceptor;

import com.wjl.wdsq.dao.LoginTicketDAo;
import com.wjl.wdsq.dao.UserDAO;
import com.wjl.wdsq.model.HostHolder;
import com.wjl.wdsq.model.LoginTicket;
import com.wjl.wdsq.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;


@Component
public class PassportInterceptor implements HandlerInterceptor {

    @Autowired
    LoginTicketDAo loginTicketDAo;

    @Autowired
    UserDAO userDAO;

    @Autowired
    HostHolder hostHolder;

    //请求开始前
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String ticket = null;
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals("ticket")) {
                    ticket = cookie.getValue();
                    break;
                }
            }
        }
        if (ticket != null) {
            LoginTicket loginTicket = loginTicketDAo.selectByTicket(ticket);
            if (loginTicket == null || loginTicket.getExpired().before(new Date()) || loginTicket.getStatus() != 0) {
                return true;
            }
            //判断用户是谁，然后放到ThreadLocal里面，后面所有请求都可以访问变量
            User user = userDAO.selectById(loginTicket.getUserId());
            hostHolder.setUser(user);

        }
        return true;
    }

    //handler处理完
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //渲染前加入user
        if (modelAndView != null) {
            modelAndView.addObject("user", hostHolder.getUser());
        }
    }

    //渲染完
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //清除用户
        hostHolder.clear();
    }
}
