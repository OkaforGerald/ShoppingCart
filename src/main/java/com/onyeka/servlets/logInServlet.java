package com.onyeka.servlets;

import jakarta.servlet.ServletException;
import com.onyeka.DAO.UserDAO;
import com.onyeka.connection.DBConnection;
import com.onyeka.model.User;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class logInServlet
 */
public class logInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public logInServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.sendRedirect("index.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter writer=response.getWriter();
		String email=request.getParameter("loginEmail");
		String password=request.getParameter("loginPassword");
		UserDAO userDB=new UserDAO(DBConnection.getConnection());
		User user=userDB.getUser(email, password);
		if(user==null) {
			writer.println("Invalid Username/password... Try again");
		}else {
		HttpSession session=request.getSession();
		session.setAttribute("auth", user);
		response.sendRedirect("index.jsp");
		}
	}

}
