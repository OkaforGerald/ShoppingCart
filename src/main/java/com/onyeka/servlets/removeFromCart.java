package com.onyeka.servlets;

import jakarta.servlet.ServletException;
import java.util.*;
import com.onyeka.model.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class removeFromCart
 */
public class removeFromCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public removeFromCart() {
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
		String ID=request.getParameter("id");
		if(ID!=null) {
		ArrayList<Cart> cartList=(ArrayList<Cart>)request.getSession().getAttribute("cart-list");
		if(cartList!=null) {
		for(Cart c:cartList) {
			if(c.getProdID()==Integer.parseInt(ID)) {
				try {
					int index=cartList.indexOf(c);
				cartList.remove(index);
				break;
				}catch(ConcurrentModificationException e) {
					e.printStackTrace();
				}
			}else {
				writer.print("Product doesn't exist");
			}
		}
		response.sendRedirect("cart.jsp");
	}
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
