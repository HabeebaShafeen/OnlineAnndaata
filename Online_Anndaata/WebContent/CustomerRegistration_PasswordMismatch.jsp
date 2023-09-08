<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Customer Registration</title>
</head>
<body>
<form action="Customer_Registration" method="get" >
<input type="text" placeholder="Name" name="name" >
<input type="text" placeholder="Aadhar No" name="aadhar" >
<input type="text" placeholder="Contact" name="phoneNo" >
<input type="text" placeholder="Location" name="location" >
<input type="password" placeholder="Password" name="password" >
<input type="password" placeholder="Re-enter Password" name="re-password" >
<font color="red"> Password Mismatch.</font>
<input type="submit" value="Register" >


</form>
</body>
</html>