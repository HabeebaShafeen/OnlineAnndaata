<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ page import="java.sql.Connection" %>
    <%@ page import="java.sql.DriverManager" %>
    <%@ page import="java.sql.ResultSet" %>
     <%@ page import="java.sql.PreparedStatement" %>
     <%@ page import="java.io.PrintWriter" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Order Products</title>
</head>
<body>
<%



//String farmer_aadhar = request.getParameter("selected");

try {
	HttpSession session2 = request.getSession(false);
	String customer_aadhar = (String) session2.getAttribute("aadhar");
	
	
	

	HttpSession session4 = request.getSession(false);
	String overqty = (String) session4.getAttribute("overqty");
	String farmer_aadhar = (String) session4.getAttribute("farmer_aadhar");	
	Class.forName("com.mysql.jdbc.Driver");
	Connection con = DriverManager.getConnection("jdbc:mysql://localhost/Online_Anndaata","root","");
	String sql = "select * from f"+farmer_aadhar+"";
	PreparedStatement ps = con.prepareStatement(sql);
	ResultSet rs = ps.executeQuery();
	
	out.print("<html>");
	out.print("<body>");
	out.print("<form action='Order' method='get'>");
	out.print("Products available are :");
	out.print("<table>");
	out.print("<tr> <td>Product </td> <td>Qty </td> <td>Price/kg </td> <td>ReqQty </td> <td>Selected</td></tr>");
	
	while(rs.next()) {
		out.print("<tr> <td> "+rs.getString("productName")+" </td> <td> "+rs.getString("qty")+" </td>  <td> "+rs.getString("price")+ "</td> <td><input type='text' name='"+rs.getString("productName")+"_qty' </td> <td> <input type='checkbox' name='selected' value='"+rs.getString("productName")+"' </td> </tr>");
	}
	out.print("</table>");
	out.print("<font color='red'>Inappropriate Quantity. <br> Please order "+overqty +" again. </font>");
	out.print("<input type='submit' value='Order'>");
	out.print("<a href='Order_Products.jsp'>Back</a>");
	out.print("</form>");
	out.print("</body>");
	out.print("</html>");
	
} catch(Exception e) {
	e.printStackTrace();
}

%>
</body>
</html>