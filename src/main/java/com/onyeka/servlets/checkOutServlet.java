package com.onyeka.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.onyeka.DAO.orderDAO;
import com.onyeka.connection.DBConnection;
import com.onyeka.model.Cart;
import com.onyeka.model.Orders;
import com.onyeka.model.User;

/**
 * Servlet implementation class checkOutServlet
 */
public class checkOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public checkOutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter writer=response.getWriter();
		ArrayList<Cart> cartlist=(ArrayList<Cart>)request.getSession().getAttribute("cart-list");
		User user=(User)request.getSession().getAttribute("auth");
		SimpleDateFormat formatter=new SimpleDateFormat("yyyy/MM/dd");
		Date date=new Date();
		orderDAO ord=new orderDAO(DBConnection.getConnection());
		if(cartlist!=null&&user!=null) {
			for(Cart c:cartlist) {
				Orders order= new Orders();
				order.setProdID(c.getProdID());
				order.setUid(user.getId());
				order.setQuantity(c.getQuantity());
				order.setDate(formatter.format(date));
				ord.insertOrder(order);
			}
			cartlist.clear();
			response.sendRedirect("orders.jsp");
		}else {
			if(user==null) {
				response.sendRedirect("login.jsp");
			}else {
			response.sendRedirect("cart.jsp");
			}
		}
	}

}
