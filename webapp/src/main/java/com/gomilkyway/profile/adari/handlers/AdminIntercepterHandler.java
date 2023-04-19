/*
 * Copyright 2023 Ari Adari
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.gomilkyway.profile.adari.handlers;

import org.springframework.lang.Nullable;
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
