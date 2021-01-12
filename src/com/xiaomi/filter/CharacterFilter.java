package com.xiaomi.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.Map;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * Servlet Filter implementation class CharacterFilter
 */
@WebFilter("/*")
public class CharacterFilter implements Filter {

    private static final ServletRequest HttpServletRequest = null;

	/**
     * Default constructor. 
     */
    public CharacterFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		if(((HttpServletRequest)request).getMethod().toLowerCase().equals("get")) {
			request = new characUTF8((HttpServletRequest)request);
		}
		chain.doFilter(request, response);
	}
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}
	class characUTF8 extends HttpServletRequestWrapper{
		@Override
		public String getParameter(String name) {
				String oldValue = super.getParameter(name);
				String newValue = "";
				try {
					if(oldValue!=null)
					newValue = new String(oldValue.getBytes("ISO-8859-1"),"utf-8");
				} catch (UnsupportedEncodingException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
				return newValue;
		}
		public characUTF8(javax.servlet.http.HttpServletRequest request) {
			super(request);
		}
	}
}
