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
		
		//���ﳵ��Ϣ���£����¹��ﳵ��������ע�⣺���ﳵ�������ܳ�����Ʒ�����
		if("change_number".equals(operate)) {
			//��ȡ���µĹ��ﳵ����
		/*	int good_num = Integer.valueOf(request.getParameter("good_num"));
			//��ȡ���ﳵid
			int cart_id = Integer.valueOf(request.getParameter("cart_id"));
			Float good_price  =Float.parseFloat(request.getParameter("good_price"))  * good_num;*/
			
			String[] good_nums = request.getParameterValues("good_num");
			String[] cart_ids = request.getParameterValues("cart_id");
			String[] good_prices = request.getParameterValues("good_price");
			
			//���������ύ������еĹ��ﳵ��Ϣһ���ύ��˼�����ܲ��ܵ������޸ĵĹ��ﳵ��Ϣ���и��£���ǰ̨ҳ���ȡ����¼������Ĺ��ﳵ��¼��ʹ��ajax�ύ��
			for(int i = 0 ; i < cart_ids.length ; i++) {
				//�ֱ�ͨ���±�ķ�ʽȥ��ȡÿһ�����ﳵ��Ϣ
				int good_num = Integer.valueOf(good_nums[i]);
				//��ȡ���ﳵid
				int cart_id = Integer.valueOf(cart_ids[i]);
				Float good_price  =Float.parseFloat(good_prices[i])  * good_num;
				
				Cart cart = new Cart();
				cart.setGoodNum(good_num);
				cart.setPreId(cart_id);
				cart.setPrice(good_price);
				
				//���¹��ﳵ��Ϣ
				 cartService.updateCart(cart);
			}
			List<Cart> cartList = getCartList(user);
			request.setAttribute("cartlist", cartList);
			request.getRequestDispatcher("cartlist.jsp").forward(request, response);
	
			return;
		}
		else if("change_number2".equals(operate)) {
			int good_num = Integer.valueOf(request.getParameter("good_num"));
			//��ȡ���ﳵid
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
		// ������Ϣɾ��
		else if ("deleteCart".equals(operate)) {
			String preId = request.getParameter("id");
			boolean deleteFlag = cartService.deleteCartByPreId(preId);
			if (deleteFlag) {
				if (user != null) {
					List<Cart> cartList = getCartList(user);
					//������ﳵ�б�������Ϣ
					if(cartList.size()>0) {
						// �����ﳵ�б���ʾ��ǰ̨ҳ��
						request.setAttribute("cartlist", cartList);
						request.getRequestDispatcher("cartlist.jsp").forward(request, response);
					}
					//������ﳵ�б���
					else {
						request.getRequestDispatcher("errorempty.jsp").forward(request, response);
					}
				}else {
					request.getRequestDispatcher("errorempty.jsp").forward(request, response);
				}
			} else {
				if (user != null) {
					List<Cart> cartList = getCartList(user);
					// �����ﳵ�б���ʾ��ǰ̨ҳ��
					request.setAttribute("cartlist", cartList);
					request.setAttribute("errorMess", "ɾ��ʧ�ܣ�");
					request.getRequestDispatcher("cartlist.jsp").forward(request, response);
				}else {
					request.getRequestDispatcher("errorempty.jsp").forward(request, response);
				}
			}
		}else if("jiesuan".equals(operate)){
			//��ȡǰ̨��ѡ�����й��ﳵ��Ϣ
			 String[] checkValues = request.getParameterValues("check");
			 
			//�ı乺�ﳵ״̬
			 for(String checkValue:checkValues) {
				 Cart cart = new Cart();
				 cart.setPreId(Integer.valueOf(checkValue));
				 cart.setStatus(1);
				 cartService.updateCart(cart);
			 }
			
			//��ӹ��ﳵ��Ϣ
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
			// �����ﳵ�б���ʾ��ǰ̨ҳ��
			request.setAttribute("cartlist", cartList);
			request.getRequestDispatcher("cartlist.jsp").forward(request, response);
		}

		// ��ת���ﳵҳ��
		else {
			if (user != null) {
				// ���й��ﳵ��ʱ����ʾ���ﳵҳ�棬������ʾerroremptyҳ��
				List<Cart> cartList = getCartList(user);
				//������ﳵ�б�������Ϣ
				if(cartList.size()>0) {
					// �����ﳵ�б���ʾ��ǰ̨ҳ��
					request.setAttribute("cartlist", cartList);
					request.getRequestDispatcher("cartlist.jsp").forward(request, response);
				}
				//������ﳵ�б���
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
		// ���й��ﳵ��ʱ����ʾ���ﳵҳ�棬������ʾerroremptyҳ��
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
