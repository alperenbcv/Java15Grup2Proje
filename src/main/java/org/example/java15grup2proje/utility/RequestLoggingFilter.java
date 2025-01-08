package org.example.java15grup2proje.utility;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class RequestLoggingFilter implements Filter {
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		
		if ("POST".equalsIgnoreCase(httpServletRequest.getMethod()) &&
				httpServletRequest.getContentType() != null &&
				httpServletRequest.getContentType().contains("application/json")) {
			
			String requestBody = new BufferedReader(new InputStreamReader(httpServletRequest.getInputStream()))
					.lines().collect(Collectors.joining("\n"));
			
			System.out.println("Incoming Request Body: " + requestBody);
		}
		
		chain.doFilter(request, response);
	}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {}
	
	@Override
	public void destroy() {}
}