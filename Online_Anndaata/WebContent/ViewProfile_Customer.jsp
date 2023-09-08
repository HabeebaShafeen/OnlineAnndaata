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
<title>View Profile</title>
</head>
<body>

<%
HttpSession session1 = request.getSession(false);
String aadhar = (String)session1.getAttribute("aadhar");

Class.forName("com.mysql.jdbc.Driver");
Connection con = DriverManager.getConnection("jdbc:mysql://localhost/Online_Anndaata","root","");

String sql = "select * from customer_details where aadhar = ?";
PreparedStatement ps = con.prepareStatement(sql);
ps.setString(1,aadhar);
ResultSet rs= ps.executeQuery();

while(rs.next()) {
	
	out.print("<font color='blue' >Name :</font> "+rs.getString("name")+"");
	out.print("<font color='blue' >Aadhar No :</font> "+rs.getString("aadhar")+"");
	out.print("<font color='blue' >Phone :</font> "+rs.getString("phoneNo")+"");
	out.print("<font color='blue' >Location:</font> "+rs.getString("location")+"");
	out.print("<font color='blue' >Password:</font> "+rs.getString("password")+"");
	
}

out.print("<a href='ProfileEdit_Customer.jsp'>Edit</a>");
out.print("<a href='Customer_Home.jsp'>Back</a>");
%>

</body>
</html>