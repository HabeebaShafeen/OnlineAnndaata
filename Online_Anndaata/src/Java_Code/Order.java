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

@WebServlet("/Order")
public class Order extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String[] selected = request.getParameterValues("selected");
		int i = 0;
		String productName , price="", qty, ava_qty="";
		String farmer_phone="", customer_phone="";
		
		int qty1, ava_qty1, price1,price2, rem_qty, flag = 0, k=0;
		String price3="", rem_qty1="";
		
		try {
			HttpSession session2 = request.getSession(false);
			String customer_aadhar = (String) session2.getAttribute("aadhar");
			
			HttpSession session = request.getSession(false);
			String farmer_aadhar = (String) session.getAttribute("farmer_aadhar");
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/Online_Anndaata","root","");
			
			String sql3 = "select * from farmer_details where aadhar = ?";
			PreparedStatement ps3 = con.prepareStatement(sql3);
			ps3.setString(1, farmer_aadhar);
			ResultSet rs3 = ps3.executeQuery();
			
			while(rs3.next()){
				farmer_phone = rs3.getString("phoneNo");
			}
			
			String sql4 = "select * from customer_details where aadhar = ?";
			PreparedStatement ps4 = con.prepareStatement(sql4);
			ps4.setString(1, farmer_aadhar);
			ResultSet rs4 = ps3.executeQuery();
			
			while(rs4.next()){
				customer_phone = rs4.getString("phoneNo");
			}
			
			
			while(i < selected.length){
				productName = selected[i];
				qty = request.getParameter(selected[i]+"_qty");
				//price = request.getParameter(selected[i]+"_price");
				
				String sql5 = "select * from f"+farmer_aadhar+" where productName = ?";
				PreparedStatement ps5 = con.prepareStatement(sql5);
				ps5.setString(1, productName);
				ResultSet rs5 = ps5.executeQuery();
				
				while(rs5.next()) {
					price = rs5.getString("price");
					ava_qty = rs5.getString("qty");
				}
				
				qty1 = Integer.parseInt(qty);
				ava_qty1 = Integer.parseInt(ava_qty);
				
				if(ava_qty1 >= qty1) {
				price1 = Integer.parseInt(price);
				 price2 = price1 * qty1;
				price3 = Integer.toString(price2);
				
				rem_qty = ava_qty1 - qty1;
				rem_qty1 = Integer.toString(rem_qty);
				
				String sql = "insert into f"+farmer_aadhar+"_Orders values(?,?,?,?,?)";
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, productName);
				ps.setString(2, qty);
				ps.setString(3, price3);
				ps.setString(4, customer_aadhar);
				ps.setString(5, customer_phone);
				ps.executeUpdate();
				
				String sql1 = "insert into f"+customer_aadhar+"_Orders values(?,?,?,?,?)";
				PreparedStatement ps1 = con.prepareStatement(sql1);
				ps1.setString(1, productName);
				ps1.setString(2, qty);
				ps1.setString(3, price3);
				ps1.setString(4, farmer_aadhar);
				ps1.setString(5, farmer_phone);
				ps1.executeUpdate();
				
				String sql7 = "update f"+ farmer_aadhar +" set qty = ? where productName = ?";
				PreparedStatement ps7 = con.prepareStatement(sql7);
				ps7.setString(1, rem_qty1);
				ps7.setString(2, productName);
				ps7.executeUpdate();
				i = i+1;
				
				//response.sendRedirect("OrderSuccesfull.jsp");
				} else {
					flag = 1;
					k = i;
					i = i+1;
				}
			}
			
			if(flag == 0) {
				response.sendRedirect("OrderSuccesfull.jsp");
			} else {
				HttpSession session4 = request.getSession();
				session4.setAttribute("overqty", selected[k]);
				session4.setAttribute("farmer_aadhar", farmer_aadhar);
				response.sendRedirect("OverQty.jsp");
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
