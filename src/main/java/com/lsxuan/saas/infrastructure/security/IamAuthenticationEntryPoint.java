package com.lsxuan.saas.infrastructure.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

@Component
public class IamAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final String iamLoginUrl;

    public IamAuthenticationEntryPoint(@Value("${iam.login-url}") String iamLoginUrl) {

        this.iamLoginUrl = iamLoginUrl;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
        AuthenticationException authException) throws IOException, ServletException {

        String redirectUri = request.getRequestURL().toString();
        String loginUrl =
            UriComponentsBuilder.fromUriString(iamLoginUrl).queryParam("redirect_uri", redirectUri).build().encode()
                .toUriString();
        response.sendRedirect(loginUrl);
    }
}