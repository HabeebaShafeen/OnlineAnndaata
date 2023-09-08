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


@WebServlet("/Order_Products")
public class Order_Products extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String farmer_aadhar = request.getParameter("selected");
		
		try {
			HttpSession session2 = request.getSession(false);
			String customer_aadhar = (String) session2.getAttribute("aadhar");
			
			HttpSession session = request.getSession();
			session.setAttribute("farmer_aadhar", farmer_aadhar);
			

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
			out.print("<input type='submit' value='Order'>");
			out.print("<a href='Order_Products.jsp'>Back</a>");
			out.print("</form>");
			out.print("</body>");
			out.print("</html>");
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
