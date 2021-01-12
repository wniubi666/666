package com.xiaomi.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.xiaomi.dao.mapper.CartMapper;
import com.xiaomi.dao.mapper.GoodMapper;
import com.xiaomi.dao.mapper.XmorderMapper;
import com.xiaomi.dao.mapper.XmorderMapper;
import com.xiaomi.dao.util.DBUtil;
import com.xiaomi.dao.vo.Cart;
import com.xiaomi.dao.vo.Good;
import com.xiaomi.dao.vo.Users;
import com.xiaomi.dao.vo.Xmorder;
import com.xiaomi.service.CartService;
import com.xiaomi.service.GoodService;
import com.xiaomi.service.XmorderService;
@WebServlet("/XmOrderServlet")
public class XmorderServlet extends HttpServlet {

	XmorderService xmorderService = new XmorderService();
	GoodService goodService = new GoodService();
	CartService cartService = new CartService();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}
	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		String operate=request.getParameter("operate");
		Users user = (Users) request.getSession().getAttribute("user");
	
		 if("xmorder".equals(operate)){
			 	int uid =  user.getUid();
				String pageChange = request.getParameter("pageChange");

				List<Xmorder> orders = xmorderService.selectOrdersByUid(uid);
			
				if(orders.size() == 0) {
					request.getRequestDispatcher("CartServlet").forward(request, response);
				}
				
				List<String> imgsrc = new ArrayList<String>();
				List<Integer> order_id = new ArrayList<Integer>();
				List<Integer> order_status = new ArrayList<Integer>();
				List<Float> price = new ArrayList<Float>();
				List<String> create_time = new ArrayList<String>();
				
				
				
				for(int i = Integer.parseInt(pageChange)*3; i < orders.size(); i++) {
					String Order_cartid = orders.get(i).getCartId().substring(0, 1);
					Good good = goodService.getGoodsByGid(Integer.parseInt(Order_cartid));
					/*
					imgsrc.add(i, (String)good.getGood_img());
					order_id.add(i, orders.get(i).getOrder_id());
					order_status.add(i, orders.get(i).getOrder_status());
					price.add(i, good.getGood_price());
					create_time.add(i, orders.get(i).getCreate_time());
					*/
					imgsrc.add((String)good.getGoodImg());
					order_id.add(orders.get(i).getOrderId());
					order_status.add(orders.get(i).getOrderStatus());
					price.add(good.getGoodPrice());
					create_time.add(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(orders.get(i).getCreateTime()));
				}
//
				
				//response.sendRedirect("order.jsp");
				request.setAttribute("imgsrc", imgsrc);
				request.setAttribute("order_id", order_id);
				request.setAttribute("order_status", order_status);
				request.setAttribute("price", price);
				request.setAttribute("create_time", create_time);
				
				request.getRequestDispatcher("xmorder.jsp").forward(request, response);

		 }else if("xmorderdetail".equals(operate)){
			 	int order_id = Integer.parseInt(request.getParameter("order_id"));
			
				
			 	Xmorder order = xmorderService.selectOneOrder(order_id);
			
				Integer order_status = 0;
				List<String> imgsrc = new ArrayList<String>();
				List<String> cart_id = new ArrayList<String>();
				List<Double> price = new ArrayList<Double>();
				Date create_time = new Date();
				List<String> good_name = new ArrayList<String>();
				List<String> good_type = new ArrayList<String>();
				List<String> good_color = new ArrayList<String>();
				List<String> good_img = new ArrayList<String>();
				List<String> good_desc = new ArrayList<String>();
				
				String CartidStr = order.getCartId();
				int i = 0;
				for(i = 0 ; CartidStr.length() != 0 ; i++){
					int index1 = CartidStr.indexOf("#");
					String str1 = CartidStr.substring(0, index1);
					int cartid1 = Integer.parseInt(str1);
					cart_id.add(str1);
					
					Cart cart = cartService.selectOneCart(cartid1);
					CartidStr = CartidStr.substring(CartidStr.indexOf("#")+1);
				
					Good good = goodService.getGoodsByGid(cart.getGoodId());
					
					order_status = order.getOrderStatus();
					
					price.add((double) good.getGoodPrice());
					create_time = order.getCreateTime();
					good_name.add(good.getGoodName());
					good_type.add(good.getGoodType());
					good_color.add(good.getGoodColor());
					good_img.add(good.getGoodImg());
					good_desc.add(good.getGoodDesc());
					
				}
							
				request.setAttribute("num", i);
				request.setAttribute("order_id", order_id);
				request.setAttribute("order_status", order_status);
				request.setAttribute("cart_id", cart_id);
				request.setAttribute("price", price);
				request.setAttribute("create_time", create_time);	
				request.setAttribute("good_name", good_name);
				request.setAttribute("good_type", good_type);
				request.setAttribute("good_color", good_color);
				request.setAttribute("good_img", good_img);
				request.setAttribute("good_desc", good_desc);
				
				request.getRequestDispatcher("xmorderDetail.jsp").forward(request, response);
		 }else{
			System.out.println("Î´ÖªÇëÇó"); 
		 }
		
	}
}
