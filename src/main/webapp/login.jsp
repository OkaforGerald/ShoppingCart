<%@page import="java.util.*" %>
<%@page import= "com.onyeka.model.*" %><%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	 <%
User auth=(User) request.getSession().getAttribute("auth");
	 if(auth!=null){
		   response.sendRedirect("index.jsp");
	   }
	 ArrayList<Cart> cartProducts=(ArrayList<Cart>)session.getAttribute("cart-list");
	   if(cartProducts!=null){
	   request.setAttribute("cart_list",cartProducts);
	   }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<style>
</style>
<%@include file="includes/header.jsp"%>
</head>
<body>
	<%@include file="includes/navBar.jsp"%>
	<div class="container">
		<div class="card w-50 mx-auto my-5">
			<div class="card-header text-center">LOGIN</div>
			<div class="card-body">
				<form action="${pageContext.request.contextPath}/logInServlet"
					method="post">
					<div class="form-group">
						<label for="email">E-mail Address</label> <input type="email"
							class="form-control" id="email" name="loginEmail"
							placeholder="Enter Your Email" required>
					</div>
					<div class="form-group">
						<label for="pw">Password</label> <input type="password" id="pw"
							class="form-control" name="loginPassword" placeholder="Password"
							required>
					</div>
					<div class="text-center">
						<button type="submit" class="btn btn-primary">LOGIN</button>
					</div>
				</form>
			</div>
		</div>
		<div class="text-center">
			<a href="Register.jsp"><button type="button"
					class="btn btn-secondary">CREATE NEW ACCOUNT</button></a>
		</div>
	</div>
	<%@include file="includes/footer.jsp"%>
</body>
</html>