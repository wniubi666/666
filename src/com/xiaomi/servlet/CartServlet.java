package com.xiaomi.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xiaomi.dao.vo.Cart;
import com.xiaomi.dao.vo.Good;
import com.xiaomi.dao.vo.Users;
import com.xiaomi.dao.vo.Xmorder;
import com.xiaomi.service.CartService;
import com.xiaomi.service.GoodService;
import com.xiaomi.service.XmorderService;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CartService cartService = new CartService();
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CartServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String operate = request.getParameter("operate");
		Users user = (Users) request.getSession().getAttribute("user");
		
		//购物车信息更新：更新购物车的数量，注意：购物车数量不能超过商品库存量
		if("change_number".equals(operate)) {
			//获取更新的购物车数量
		/*	int good_num = Integer.valueOf(request.getParameter("good_num"));
			//获取购物车id
			int cart_id = Integer.valueOf(request.getParameter("cart_id"));
			Float good_price  =Float.parseFloat(request.getParameter("good_price"))  * good_num;*/
			
			String[] good_nums = request.getParameterValues("good_num");
			String[] cart_ids = request.getParameterValues("cart_id");
			String[] good_prices = request.getParameterValues("good_price");
			
			//不合理：表单提交会把所有的购物车信息一起提交，思考：能不能单独把修改的购物车信息进行更新（在前台页面获取点击事件触发的购物车记录，使用ajax提交）
			for(int i = 0 ; i < cart_ids.length ; i++) {
				//分别通过下标的方式去获取每一个购物车信息
				int good_num = Integer.valueOf(good_nums[i]);
				//获取购物车id
				int cart_id = Integer.valueOf(cart_ids[i]);
				Float good_price  =Float.parseFloat(good_prices[i])  * good_num;
				
				Cart cart = new Cart();
				cart.setGoodNum(good_num);
				cart.setPreId(cart_id);
				cart.setPrice(good_price);
				
				//更新购物车信息
				 cartService.updateCart(cart);
			}
			List<Cart> cartList = getCartList(user);
			request.setAttribute("cartlist", cartList);
			request.getRequestDispatcher("cartlist.jsp").forward(request, response);
	
			return;
		}
		else if("change_number2".equals(operate)) {
			int good_num = Integer.valueOf(request.getParameter("good_num"));
			//获取购物车id
			int cart_id = Integer.valueOf(request.getParameter("cart_id"));
			Float good_price  =Float.parseFloat(request.getParameter("good_price"))  * good_num;
			Cart cart = new Cart();
			cart.setGoodNum(good_num);
			cart.setPreId(cart_id);
			cart.setPrice(good_price);
			boolean updateFlag = cartService.updateCart(cart);
			JSONObject jo = new JSONObject();
			if(updateFlag) {
				jo.put("good_num", good_num);
				jo.put("good_price", good_price);
			}
			jo.put("updateFlag", updateFlag);
			response.getWriter().append(jo.toString());
			
		}
		// 购物信息删除
		else if ("deleteCart".equals(operate)) {
			String preId = request.getParameter("id");
			boolean deleteFlag = cartService.deleteCartByPreId(preId);
			if (deleteFlag) {
				if (user != null) {
					List<Cart> cartList = getCartList(user);
					//如果购物车列表中有信息
					if(cartList.size()>0) {
						// 将购物车列表显示到前台页面
						request.setAttribute("cartlist", cartList);
						request.getRequestDispatcher("cartlist.jsp").forward(request, response);
					}
					//如果购物车列表中
					else {
						request.getRequestDispatcher("errorempty.jsp").forward(request, response);
					}
				}else {
					request.getRequestDispatcher("errorempty.jsp").forward(request, response);
				}
			} else {
				if (user != null) {
					List<Cart> cartList = getCartList(user);
					// 将购物车列表显示到前台页面
					request.setAttribute("cartlist", cartList);
					request.setAttribute("errorMess", "删除失败！");
					request.getRequestDispatcher("cartlist.jsp").forward(request, response);
				}else {
					request.getRequestDispatcher("errorempty.jsp").forward(request, response);
				}
			}
		}else if("jiesuan".equals(operate)){
			//获取前台勾选的所有购物车信息
			 String[] checkValues = request.getParameterValues("check");
			 
			//改变购物车状态
			 for(String checkValue:checkValues) {
				 Cart cart = new Cart();
				 cart.setPreId(Integer.valueOf(checkValue));
				 cart.setStatus(1);
				 cartService.updateCart(cart);
			 }
			
			//添加购物车信息
			Xmorder xmorder = new Xmorder();
			 String cartId = "";
			 for(String checkValue:checkValues) {
				 cartId = cartId+checkValue+"#";
			 }
			 xmorder.setCartId(cartId);
			 xmorder.setCreateTime(new Date());
			 xmorder.setOrderStatus(0);
			 xmorder.setUid(user.getUid());
			 
			 XmorderService xmorderService = new XmorderService();
			 boolean addFlag = xmorderService.addOrder(xmorder);
			 List<Cart> cartList = getCartList(user);
			// 将购物车列表显示到前台页面
			request.setAttribute("cartlist", cartList);
			request.getRequestDispatcher("cartlist.jsp").forward(request, response);
		}

		// 跳转购物车页面
		else {
			if (user != null) {
				// 当有购物车的时候，显示购物车页面，否则显示errorempty页面
				List<Cart> cartList = getCartList(user);
				//如果购物车列表中有信息
				if(cartList.size()>0) {
					// 将购物车列表显示到前台页面
					request.setAttribute("cartlist", cartList);
					request.getRequestDispatcher("cartlist.jsp").forward(request, response);
				}
				//如果购物车列表中
				else {
					request.getRequestDispatcher("errorempty.jsp").forward(request, response);
				}
			} else {
				request.getRequestDispatcher("errorempty.jsp").forward(request, response);
			}
		}
	}

	public List<Cart> getCartList(Users user){
		List<Cart> cartlist = cartService.findCartByUid(user.getUid());
		// 当有购物车的时候，显示购物车页面，否则显示errorempty页面
		for (Cart cart : cartlist) {
			GoodService goodService = new GoodService();
			Good goodsByGid = goodService.getGoodsByGid(cart.getGoodId());
			cart.setG(goodsByGid);
		}
		return cartlist;
	}
	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
