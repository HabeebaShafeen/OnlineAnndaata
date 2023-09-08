package Java_Code;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/Login1")
public class Login1 extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String aadhar = request.getParameter("aadhar");
		String password = request.getParameter("password");
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/Online_Anndaata","root","");
			String sql="select * from all_details where aadhar = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, aadhar);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				if(rs.getString("role").equals("farmer")) {
					String sql1 = "select * from farmer_details where aadhar = ?";
					PreparedStatement ps1 = con.prepareStatement(sql1);
					ps1.setString(1, aadhar);
					ResultSet rs1 = ps1.executeQuery();
					
					while(rs1.next()){
						if(rs1.getString("password").equals(password)) {
							HttpSession session = request.getSession();
							session.setAttribute("aadhar", aadhar);
							response.sendRedirect("Farmer_Home.jsp");
						} else {
							response.sendRedirect("Login_InvalidPassword.jsp");
						}
					}
					
					
				} else {
					
					String sql1 = "select * from customer_details where aadhar = ?";
					PreparedStatement ps1 = con.prepareStatement(sql1);
					ps1.setString(1, aadhar);
					ResultSet rs1 = ps1.executeQuery();
					
					while(rs1.next()){
						if(rs1.getString("password").equals(password)) {
							HttpSession session2 = request.getSession();
							session2.setAttribute("aadhar", aadhar);
							response.sendRedirect("Customer_Home.jsp");
						} else {
							response.sendRedirect("Login_InvalidPassword.jsp");
						}
					}
					
				}
			}
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
