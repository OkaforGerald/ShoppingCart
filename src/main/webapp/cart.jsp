<%@page import="java.util.*" %>
<%@page import="com.onyeka.model.*"%>
<%@page import="com.onyeka.DAO.*"%>
<%@page import="java.text.*"%>
<%@page import="com.onyeka.connection.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
User auth = (User) request.getSession().getAttribute("auth");
if (auth != null) {
	request.getSession().setAttribute("auth", auth);
}
ArrayList<Cart> cartProducts=(ArrayList<Cart>)session.getAttribute("cart-list");
ArrayList<Cart> cartElements=null;
String price=null;
if(cartProducts!=null){
ProductDAO pd=new ProductDAO(DBConnection.getConnection());
cartElements=pd.getCartItems(cartProducts);
request.setAttribute("cart_list",cartProducts);
NumberFormat formatter=new DecimalFormat("#0.00");
price=formatter.format((getTotalPrice(cartElements)));
session.setAttribute("dcf",formatter);
}
%>
<%!
	public double getTotalPrice(ArrayList<Cart> c){
	double sum=0;
	if(c!=null){
	for(Cart items:c){
		sum+=items.getPrice();
	}
	return sum;
	}else{
		return 0;
	}
}

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cart</title>
<%@include file="includes/header.jsp"%>
<style type="text/css">
.table tbody td {
	vertical-align: middle;
}

.btn-incre, .btn-decre {
	box-shadow: none;
	font-size:25px;
}
</style>
</head>
<body>
	<%@include file="includes/navBar.jsp"%>
	<div class="container">
		<div class="d-flex py-3">
		<%price=(cartElements!=null)?price:"0.00"; %>
			<h3>Total Price: $<%=price %></h3>
			<a href="checkOut" class="mx-3 btn btn-primary">Check Out</a>
		</div>
		<table class="table table-light">
			<thead>
				<tr>
					<th scope="col">Name</th>
					<th scope="col">Category</th>
					<th scope="col">Price($)</th>
					<th scope="col">Buy Now</th>
					<th scope="col">Cancel</th>
				</tr>
			</thead>
			<tbody>
			<% if(cartElements!=null){
					for(Cart prod:cartElements){
						NumberFormat deci=null;
						deci=(NumberFormat)session.getAttribute("dcf");
				%>
				<tr>
					<td><%=prod.getProdName()%></td>
					<td><%=prod.getCategory() %></td>
					<td><%=deci.format(prod.getPrice()) %></td>
					<td>
						<form action="orderServlet" method="post" class="form-inline">
							<input type="hidden" name="id" value="<%=prod.getProdID() %>" class="form-input">
							<div class="form-group d-flex justify-content-between">
								<a class="btn btn-sm btn-decre" href="${pageContext.request.contextPath }/QtyChange?id=<%=prod.getProdID()%>&operation=dec"><i
									class="fas fa-minus-square"></i></a> <input type="text"
									name="quantity" value="<%=prod.getQuantity() %>" class="form-control text-center" readonly>
								<a class="btn btn-sm btn-incre" href="${pageContext.request.contextPath }/QtyChange?id=<%=prod.getProdID()%>&operation=inc"><i
									class="fas fa-plus-square"></i></a>
							</div>
							<button type="submit" class="btn btn-primary btn-sm">Buy Now</button>
						</form>
					</td>
					<td><a href="${pageContext.request.contextPath}/removeFromCart?id=<%=prod.getProdID() %>" class="btn btn-danger">Remove</a></td>
				</tr>
				<%}
				}else{
				%>
				<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<%} %>
			</tbody>
		</table>
	</div>
	<%@include file="includes/footer.jsp"%>
</body>
</html>