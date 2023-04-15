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
