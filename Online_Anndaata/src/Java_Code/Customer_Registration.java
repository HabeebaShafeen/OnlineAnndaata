package Java_Code;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/Customer_Registration")
public class Customer_Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String name = request.getParameter("name");
		String aadhar = request.getParameter("aadhar");
		String phoneNo = request.getParameter("phoneNo");
		String location = request.getParameter("location");
		String password = request.getParameter("password");
		String re_password = request.getParameter("re-password");
		
		try {
			HttpSession session2 = request.getSession();
			session2.setAttribute("name", name);
			session2.setAttribute("aadhar", aadhar);
			session2.setAttribute("location", location);
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/Online_Anndaata","root","");
			if(password.equals(re_password)){
				
				String sql = "insert into customer_details values(?,?,?,?,?)";
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, name);
				ps.setString(2, aadhar);
				ps.setString(3, phoneNo);
				ps.setString(4, location);
				ps.setString(5, password);
				ps.executeUpdate();
				
				String sql1 = "create table f"+ aadhar +"_Orders(productName varchar(100), qty varchar(100), price varchar(100),aadhar varchar(100), phoneNo varchar(100))";
				PreparedStatement ps1 = con.prepareStatement(sql1);
				ps1.executeUpdate(); 
				
				String sql2 = "insert into all_details values(?,?)";
				PreparedStatement ps2 = con.prepareStatement(sql2);
				ps2.setString(1, aadhar);
				ps2.setString(2, "customer");
				ps2.executeUpdate();
				
				
				response.sendRedirect("Customer_Home.jsp");
				
				
			} else {
				response.sendRedirect("CustomerRegistration_PasswordMismatch.jsp");
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
