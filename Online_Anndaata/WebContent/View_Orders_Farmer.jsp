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
<title>View Orders</title>
</head>
<body>

<h2> Your Orders are...</h2>
<%
try{
	HttpSession session2 = request.getSession(false);
	String aadhar =(String) session2.getAttribute("aadhar");
	
	Class.forName("com.mysql.jdbc.Driver");
	Connection con = DriverManager.getConnection("jdbc:mysql://localhost/Online_Anndaata","root","");
	String sql = "select * from f" + aadhar +"_Orders";
	PreparedStatement ps = con.prepareStatement(sql);
	ResultSet rs = ps.executeQuery();
	
	out.print("<table>");
	out.print("<tr><td> Product  </td>  <td> Qty  </td>  <td> Price(perKG*)  </td>  <td> Contact</td></tr>");
	while(rs.next()) {
		out.print("<tr><td>"+rs.getString("productName")+" </td> <td>"+rs.getString("qty")+" </td> <td>"+rs.getString("price")+" </td> <td>"+rs.getString("phoneNo")+" </td> </tr>");
	}
	
	out.print("</table>");
	
	out.print("<a href='Farmer_Home.jsp' style='text-decoration : none' > Back </a>");
	
} catch(Exception e) {
	e.printStackTrace();
}


%>

</body>
</html>