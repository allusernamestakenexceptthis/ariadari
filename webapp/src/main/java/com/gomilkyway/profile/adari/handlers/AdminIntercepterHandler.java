package com.gomilkyway.profile.adari.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.gomilkyway.profile.adari.utils.AppVersionUtil;

public class AdminIntercepterHandler implements HandlerInterceptor {
  
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                            Object handler, @Nullable ModelAndView modelAndView) throws Exception 
    {
            if (modelAndView == null || modelAndView.getViewName() == null){
                return;
            }

            String viewName = modelAndView.getViewName();
            if (viewName == null){
                return;
            }
         
            if (viewName.startsWith("admin/")) {
                modelAndView.addObject("currentPage", viewName.replace("admin/", ""));
                modelAndView.addObject("appversion", AppVersionUtil.getVersion());
            }
            
    }

}
