package com.xiaomi.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xiaomi.dao.vo.Users;
import com.xiaomi.service.UserService;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String operate = request.getParameter("operate");
		UserService userService = new UserService();
		
		
		if("register".equals(operate)) {
			String codeImg = request.getParameter("image");
			
			
			boolean codeFlag = userService.imgCodeValidate(codeImg,request);
			if(!codeFlag) {
				request.getRequestDispatcher("register.jsp").forward(request, response);
				return;
			}
			
			String username = request.getParameter("username");
			String password = request.getParameter("password"); 
			String phonenumber = request.getParameter("phonenumber");
			String address = request.getParameter("address");
			String hobby = request.getParameter("hobby");
			String sign = request.getParameter("sign");
			Users user = new Users(username, password, phonenumber, address, hobby, sign);
			
			//调用service实现业务逻辑
			boolean registerFlag = userService.register(user,request);
			
			//根据service反馈的结果，决定页面的分发与跳转
			if(registerFlag) {
				response.sendRedirect("login.jsp");
			}else {
				request.getRequestDispatcher("register.jsp").forward(request, response);
			}
			
		}else if("login".equals(operate)) {
			String codeImg = request.getParameter("image");
			
			boolean codeFlag = userService.imgCodeValidate(codeImg,request);
			if(!codeFlag) {
				request.getRequestDispatcher("login.jsp").forward(request, response);
				return;
			}
			
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			
			boolean loginFlag = userService.login(username, password, request);
			//根据service反馈的结果，决定页面的分发与跳转
			if(loginFlag) {
				response.sendRedirect("index.jsp");
			}else {
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		}else if("selfinfo".equals(operate)) {
			//进入个人中心
			request.getRequestDispatcher("self_info.jsp").forward(request, response);
		}else if("edit".equals(operate)) {
			//进入用户资料修改页面
			request.getRequestDispatcher("edituser.jsp").forward(request, response);
		}else if("editupdate".equals(operate)) {
			//修改用户信息
			String uid = request.getParameter("uid");
			String username = request.getParameter("username");
			String password = request.getParameter("password"); 
			String phonenumber = request.getParameter("phonenumber");
			String address = request.getParameter("address");
			String hobby = request.getParameter("hobby");
			String sign = request.getParameter("sign");
			Users users = new Users(Integer.valueOf(uid),username,password,phonenumber,address,hobby,sign);
			
			boolean updateFlag = userService.updateUser(users);
			
			if(updateFlag) {
				request.getSession().setAttribute("user", users);
				request.getRequestDispatcher("self_info.jsp").forward(request, response);
			}else {
				
				request.getRequestDispatcher("edituser.jsp").forward(request, response);
			}
		}else if("logout".equals(operate)) {
			//用户登出
			request.getSession().invalidate();
			response.sendRedirect("login.jsp");
			
		}
	}

}
