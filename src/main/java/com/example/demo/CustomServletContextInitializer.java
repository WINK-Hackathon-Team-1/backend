package com.example.demo;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.stereotype.Component;

@Component
public class CustomServletContextInitializer implements ServletContextInitializer {
    
    
    //세션 쿠키 전송 정보
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        servletContext.getSessionCookieConfig().setPath("/");
        servletContext.getSessionCookieConfig().setDomain("localhost");
    }

}
