package com.onyeka.servlets;

import jakarta.servlet.ServletException;
import com.onyeka.DAO.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.onyeka.connection.DBConnection;
import com.onyeka.model.Cart;
import com.onyeka.model.Orders;
import com.onyeka.model.User;

/**
 * Servlet implementation class orderServlet
 */
public class orderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public orderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter writer= response.getWriter();
		User user=(User)request.getSession().getAttribute("auth");
		SimpleDateFormat formatter=new SimpleDateFormat("yyyy/MM/dd");
		Date date=new Date();
		if(user!=null) {
			int ID=Integer.parseInt(request.getParameter("id"));
			int Qty=Integer.parseInt(request.getParameter("quantity"));
			if(Qty<=0) {
				Qty=1;
			}
			Orders order=new Orders();
			order.setUid(user.getId());
			order.setProdID(ID);
			order.setQuantity(Qty);
			order.setDate(formatter.format(date));
			
			ArrayList<Cart> cartList=(ArrayList<Cart>)request.getSession().getAttribute("cart-list");
			orderDAO ord=new orderDAO(DBConnection.getConnection());
			if(ord.insertOrder(order)) {
			if(cartList!=null) {
				for(Cart c: cartList) {
					if(c.getProdID()==order.getProdID()) {
						cartList.remove(cartList.indexOf(c));
						response.sendRedirect("cart.jsp");
						break;
					}
				}
			}
			response.sendRedirect("orders.jsp");
			}
		}else {
			response.sendRedirect("login.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
