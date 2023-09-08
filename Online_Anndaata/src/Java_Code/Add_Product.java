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


@WebServlet("/Add_Product")
public class Add_Product extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String productName = request.getParameter("productName");
		String qty = request.getParameter("qty");
		String price = request.getParameter("price");
		
		
		try {
			HttpSession session = request.getSession(false);
			String aadhar = (String) session.getAttribute("aadhar");
			
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/Online_Anndaata","root","");
			String sql = "insert into f"+aadhar+" values(?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, productName);
			ps.setString(2, qty);
			ps.setString(3, price);
			
			ps.executeUpdate();
			
			response.sendRedirect("AddProduct_Succesfull.jsp");
			
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
