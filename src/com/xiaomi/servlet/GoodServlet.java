package com.xiaomi.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xiaomi.dao.vo.Cart;
import com.xiaomi.dao.vo.Good;
import com.xiaomi.dao.vo.GoodColor;
import com.xiaomi.dao.vo.GoodType;
import com.xiaomi.dao.vo.Users;
import com.xiaomi.service.CartService;
import com.xiaomi.service.GoodService;

import net.sf.json.JSONArray;

/**
 * Servlet implementation class GoodServlet
 */
@WebServlet("/GoodServlet")
public class GoodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoodServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			String operate = request.getParameter("operate");
			GoodService goodService = new GoodService();
			
			if("xiaomi".equals(operate)) {
				List<Good> allGoods = goodService.getAllGoods();
				request.setAttribute("goodsList", allGoods);
				request.getRequestDispatcher("goods_list.jsp").forward(request, response);
			}else if("search".equals(operate)) {
				
				String good_name = request.getParameter("good_name");
				List<Good> goodsByName = goodService.getGoodsByName(good_name);
				request.setAttribute("searchgoods", goodsByName);
				request.getRequestDispatcher("searchlist.jsp").forward(request, response);
			}else if("detail".equals(operate)) {
				//1.���������ύ����Ʒ���Ʋ�ѯ��Ʒ��Ϣ
				String good_name = request.getParameter("good_name");
				Good goodByName = goodService.getGoodByName(good_name);
				//2.��ת����ϸҳ��
				request.setAttribute("good", goodByName);
				
				List<GoodType>  goodType = new ArrayList<GoodType>();
				goodType.add(new GoodType("4+64",2600d));
				goodType.add(new GoodType("3+32", 2599d));
				goodType.add(new GoodType("2+16", 1599d));
				
				
				List<GoodColor>  goodColor = new ArrayList<GoodColor>();
				goodColor.add(new GoodColor("black"));
				goodColor.add(new GoodColor("white"));
				goodColor.add(new GoodColor("pink"));
				goodColor.add(new GoodColor("gray"));
				goodColor.add(new GoodColor("blue"));
				goodColor.add(new GoodColor("yellow"));
				
				request.setAttribute("goodtList", goodType);
				request.setAttribute("goodcList", goodColor);
				request.getRequestDispatcher("goods_details.jsp").forward(request, response);
				
			}
			else if("typeforcolor".equals(operate)) {
				//������ѡ���ͣ��첽������Ʒ��ɫ
				String price = request.getParameter("price");
				String type = request.getParameter("type");
				String goodName = request.getParameter("goodName");
				
				//���ݴ��������ѯ��Ʒ����
				
				List<Good> goodList = 
						goodService.getGoodsByCondition(type, Float.valueOf(price), goodName,null);
				JSONArray ja = JSONArray.fromObject(goodList);
				
				PrintWriter writer = response.getWriter();
				writer.append(ja.toString());
			}else if("buy".equals(operate)) {
				//�����Ʒ�����ﳵ
				CartService cartService = new CartService();
				int uid = 0;
				if(request.getSession().getAttribute("user")!= null) {
					uid = ((Users)request.getSession().getAttribute("user")).getUid();
				}
			
				//�����û�ѡ����ͺš���ɫ����Ʒ���Ʋ�ѯ��ƷID
				String color = request.getParameter("color");
				String type = request.getParameter("type");
				String goodName = request.getParameter("good_name");
				List<Good> goodList = 
						goodService.getGoodsByCondition(type, 0, goodName,color);
				Good goodTarget = goodList.get(0);
				
				//�����û�ID����ƷID���в�ѯ
				Cart cartInDB = cartService.findCartByUidGid(uid, goodTarget.getGoodId());
				Cart cart = new Cart();
				if(cartInDB==null) {
					//�����Ʒ��Ϣ�����ﳵ(�״���Ӹ���Ʒ)
					cart.setGoodId(goodTarget.getGoodId());
					cart.setPrice(goodTarget.getGoodPrice());
					cart.setGoodNum(1);
					cart.setStatus(0);
					cart.setUid(uid);
					cartService.addCart(cart);
				}else {
					//������ﳵ���Ѿ��и���Ʒ(���״����)
					cart.setPreId(cartInDB.getPreId());
					cart.setGoodId(goodTarget.getGoodId());
					cart.setGoodNum(cartInDB.getGoodNum()+1);
					cart.setPrice(cart.getGoodNum()  * goodTarget.getGoodPrice());
					cart.setStatus(0);
					cart.setUid(uid);
					//����ԭ�����ݿ��е�����
					cartService.updateCart(cart);
				}
				request.getRequestDispatcher("success_add_cart.jsp").forward(request, response);
			}
			
	}

}
