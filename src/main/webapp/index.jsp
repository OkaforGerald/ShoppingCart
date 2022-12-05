<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@page import= "com.onyeka.model.*" %>
   <%@page import= "com.onyeka.DAO.*" %>
   <%@page import= "com.onyeka.connection.*" %>
   <%@page import= "java.util.*" %>
   <%
User auth=(User) request.getSession().getAttribute("auth");
   if(auth!=null){
	   request.getSession().setAttribute("auth",auth);
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
<title>Welcome to Shop</title>
<%@include file="includes/header.jsp" %>
</head>
<body>
<%@include file="includes/navBar.jsp" %>
	<div class="container">
	<div class="card-header">All Products</div>
	<%
	ProductDAO pd=new ProductDAO(DBConnection.getConnection());
	List<Product> product=pd.getProducts();
	%>
	<div class="row">
	<%if(!product.isEmpty()){
		for(Product p:product){
	 %>
	<div class="col-lg-3 d-flex align-items-stretch my-3">
	<div class="card w-100" style="width: 18rem;">
  <img class="card-img-top" src="ProductImages/<%=p.getImage() %>" alt="Card image cap">
  <div class="card-body">
    <h5 class="card-title"><%=p.getProdName()%></h5>
    <h6 class="price">Price: $<%=p.getPrice()%></h6>
    <h6 class="category">Category: <%=p.getCategory() %></h6>
    <div class="mt-3 d-flex justify-content-between"></div>
    <a href="${pageContext.request.contextPath}/addToCart?id=<%=p.getProdID() %>" class="btn btn-dark">Add to Cart</a>
    <a href="orderServlet?quantity=1&id=<%=p.getProdID() %>" class="btn btn-primary">Buy Now</a>
  </div>
</div>
	</div>
	<%}
		}%>
	</div>
</div>
<%@include file="includes/footer.jsp" %>
</body>
</html>