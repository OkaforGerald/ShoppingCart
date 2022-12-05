<%@page import="java.util.*"%>
<%@page import="java.sql.*"%>
<%@page import="com.onyeka.connection.*"%>
<%@page import="com.onyeka.model.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
User auth = (User) request.getSession().getAttribute("auth");
if (auth != null) {
	request.getSession().setAttribute("auth", auth);
} else {
	response.sendRedirect("login.jsp");
}
ArrayList<Cart> cartProducts = (ArrayList<Cart>) session.getAttribute("cart-list");
if (cartProducts != null) {
	request.setAttribute("cart_list", cartProducts);
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Orders</title>
<%@include file="includes/header.jsp"%>
</head>
<body>
	<%@include file="includes/navBar.jsp"%>
	<%
	List<Orders> orders = new ArrayList<>();
	if(auth!=null){
	String query = "SELECT * FROM ORDERS AS o INNER JOIN products AS p ON (o.p_id=p.ProdID AND o.u_id=?)";
	Connection con = DBConnection.getConnection();
	PreparedStatement pst = con.prepareStatement(query);
	pst.setInt(1, auth.getId());
	ResultSet rs = pst.executeQuery();
	while (rs.next()) {
		Orders ord = new Orders();
		ord.setOrderId(rs.getInt(1));
		ord.setDate(rs.getString(5));
		ord.setProdName(rs.getString(7));
		ord.setCategory(rs.getString(8));
		ord.setQuantity(rs.getInt(4));
		ord.setPrice(rs.getDouble(9) * rs.getInt(4));
		orders.add(ord);
	}
	}else{
		response.sendRedirect("login.jsp");
	}
	%>
	<div class="card-header mx-3">All Orders</div>
	<div class="container">
	<table class="table table-light">
		<thead>
			<tr>
				<th scope="col">Date</th>
				<th scope="col">Name</th>
				<th scope="col">Category</th>
				<th scope="col">Quantity</th>
				<th scope="col">Price($)</th>
				<th scope="col">Cancel</th>
			</tr>
		</thead>
		<tbody>
		<%for(Orders order:orders){ %>
		<tr>
		<td><%=order.getDate() %></td>
		<td><%=order.getProdName() %></td>
		<td><%=order.getCategory() %></td>
		<td><%=order.getQuantity() %></td>
		<td><%=order.getPrice() %></td>
		<td><a href="cancelOrder?id=<%=order.getOrderId() %>" class="btn btn-danger btn-sm">Cancel</a></td>
		</tr>
		<%} %>
		</tbody>
	</table>
	</div>
	<%@include file="includes/footer.jsp"%>
</body>
</html>