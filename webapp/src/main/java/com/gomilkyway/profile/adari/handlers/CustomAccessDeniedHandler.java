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

import java.io.IOException;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CustomAccessDeniedHandler implements AccessDeniedHandler{

    private final AccessDeniedHandlerImpl accessDeniedHandler = new AccessDeniedHandlerImpl();
    private String redirectPage = null;

    public CustomAccessDeniedHandler(String redirectPage) {
        if (redirectPage != null && !redirectPage.startsWith("redirect:")) {
            accessDeniedHandler.setErrorPage(redirectPage);
        }
        this.redirectPage = redirectPage;
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
            AccessDeniedException accessDeniedException) throws IOException, ServletException {
        
        
        if (redirectPage != null && redirectPage.startsWith("redirect:")) {
            response.sendRedirect(redirectPage.substring(9));
            
        } else {
            accessDeniedHandler.handle(request, response, accessDeniedException);
        }
    }

    
    
}
