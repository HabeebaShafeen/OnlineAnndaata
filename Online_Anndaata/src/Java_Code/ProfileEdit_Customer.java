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

@WebServlet("/ProfileEdit_Customer")
public class ProfileEdit_Customer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String name = request.getParameter("name");
		String phoneNo = request.getParameter("phoneNo");
		String location = request.getParameter("location");
		String password = request.getParameter("password");
		
		try {
			HttpSession session1 = request.getSession(false);
			String aadhar = (String) session1.getAttribute("aadhar");
			
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/Online_Anndaata","root","");
			String sql = "update customer_details set name = ?, phoneNo = ?, location =?, password = ?  where aadhar = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, phoneNo);
			ps.setString(3, location);
			ps.setString(4, password);
			ps.setString(5, aadhar);
			
			ps.executeUpdate();
			
			response.sendRedirect("EditProfileCustomer_Succesfull.jsp");
			
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
