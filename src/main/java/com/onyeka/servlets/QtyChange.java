package com.onyeka.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.onyeka.model.Cart;

/**
 * Servlet implementation class QtyChange
 */
public class QtyChange extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QtyChange() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter writer=response.getWriter();	
		int id=Integer.parseInt(request.getParameter("id"));
		String operation=request.getParameter("operation");
		ArrayList<Cart> cart_list=(ArrayList<Cart>)request.getSession().getAttribute("cart-list");
		if(operation.equals("inc")&&id>=1) {
			for(Cart cart:cart_list) {
				if(cart.getProdID()==id) {
					int Qty=cart.getQuantity();
					Qty++;
					cart.setQuantity(Qty);
				}
			}
			response.sendRedirect("cart.jsp");
		}
		if(operation.equals("dec")&&id>=1) {
			for(Cart cart:cart_list) {
				if(cart.getProdID()==id) {
					int Qty=cart.getQuantity();
					if(Qty>1) {
						Qty--;
						cart.setQuantity(Qty);
					}
				}
			}
			response.sendRedirect("cart.jsp");
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
