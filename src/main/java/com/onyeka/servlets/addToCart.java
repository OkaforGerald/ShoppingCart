package com.onyeka.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.onyeka.model.Cart;

/**
 * Servlet implementation class addToCart
 */
public class addToCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addToCart() {
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
		Cart cart=new Cart();
		cart.setProdID(id);
		cart.setQuantity(1);
		ArrayList<Cart> cartElements=new ArrayList<>();
		
		HttpSession session=request.getSession();
		ArrayList<Cart> cartlist=(ArrayList<Cart>) session.getAttribute("cart-list");
		if(cartlist==null) {
			cartElements.add(cart);
			session.setAttribute("cart-list", cartElements);
			response.sendRedirect("index.jsp");
		}else {
			boolean isExists=false;
			cartElements=cartlist;
			for(Cart c:cartElements) {
				if(c.getProdID()==cart.getProdID()) {
					isExists=true;
					writer.print("<h5><p style='color:red;' class='text-center'>PRODUCT ALREADY EXISTS <a href='cart.jsp'>GO TO CART</a></p></h5>");
				}
			}
			if(!isExists) {
			cartElements.add(cart);
			response.sendRedirect("index.jsp");
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
