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
<h2> Farmers near 'u'...</h2>
<form action="Order_Products" method="get" >
<%

try {
	HttpSession session2 = request.getSession(false);
	String aadhar = (String)session2.getAttribute("aadhar");
	String location="";

	Class.forName("com.mysql.jdbc.Driver");
	Connection con = DriverManager.getConnection("jdbc:mysql://localhost/Online_Anndaata","root","");
	String sql = "select * from customer_details where aadhar = ?";
	PreparedStatement ps = con.prepareStatement(sql);
	ps.setString(1, aadhar);
	ResultSet rs = ps.executeQuery();
	
	while(rs.next()){
		location = rs.getString("location");
	}
	
	out.print("<table>");
	out.print("<tr><td>Name</td> <td> Location </td> <td> Select </td></tr>");
	
	 String sql1 = "select * from farmer_details where location = ?";
	PreparedStatement ps1 = con.prepareStatement(sql1);
	ps1.setString(1, location);
	ResultSet rs1 = ps1.executeQuery();
	
	while(rs1.next()){
		out.print("<tr> <td> "+rs1.getString("name")+" </td> <td>"+ rs1.getString("location") +" </td> <td> <input type='radio' name='selected' value='"+rs1.getString("aadhar")+"' </td> </tr>");
	}
	out.print("</table>");
	
} catch(Exception e) {
	e.printStackTrace();
}



%>

<input type="submit" value="Continue">
</form>
</body>
</html>