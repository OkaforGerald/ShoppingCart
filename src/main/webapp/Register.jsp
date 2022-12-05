<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@page import="com.onyeka.model.*" %>
	<%User auth=null; %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@include file="includes/header.jsp"%>
<title>Register</title>
</head>
<body>
	<%@include file="includes/navBar.jsp"%>
	<div class="container">
		<div class="card w-50 mx-auto my-5">
			<div class="card-header text-center">SIGN UP</div>
			<div class="card-body">
				<form action="${pageContext.request.contextPath}/RegisterServlet"
					method="post">
					<div class="form-group">
						<label for="Fname">First Name</label> <input type="text"
							class="form-control" id="Fname" name="Fname"
							placeholder="First Name" required autofocus>
					</div>
					<div class="form-group">
						<label for="lname">Last Name</label> <input type="text" id="lname"
							class="form-control" name="lname" placeholder="Last Name"
							required>
					</div>
					<div class="form-group">
						<label for="email">E-mail</label> <input type="email" id="email"
							class="form-control" name="email" placeholder="Enter Your E-mail"
							required>
					</div>
					<div class="form-group">
						<label for="pw">Password</label> <input type="password" id="pw"
							class="form-control" name="pw" placeholder="Password"
							required>
					</div>
					<div class="text-center">
						<button type="submit" class="btn btn-primary">SUBMIT</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<%@include file="includes/footer.jsp"%>
</body>
</html>