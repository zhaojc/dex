package com.lotoquebec.cardexCommun.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.lotoquebec.cardexCommun.exception.ExceptionHandler;


public class ExceptionFilter implements Filter{

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try{
        	chain.doFilter(request, response);
        }catch(Throwable e){
        	ExceptionHandler.getInstance().traiterException((HttpServletRequest) request, e);
        }
	}

	public void init(FilterConfig arg0) throws ServletException {
	}

}
