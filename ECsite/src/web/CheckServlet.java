package web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CheckServlet extends HttpServlet {

	ArrayList<Integer> numlist1 = new ArrayList<Integer>();
	ArrayList namelist1= new ArrayList();
	Connection cnct = null;
	Statement st = null;
	PreparedStatement pst = null;
	RequestDispatcher Rd =null;
	ResultSet rs = null;


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		HttpSession session = req.getSession(false);
		if(session==null) {
			RequestDispatcher Rd = req.getRequestDispatcher("Login.jsp");
			Rd.forward(req,resp);
			Rd=null;

		}else {


			numlist1 = (ArrayList<Integer>) session.getAttribute("numlist" );
			namelist1=(ArrayList) session.getAttribute("namelist");

		String url = "jdbc:mysql://localhost/ECsite";
		String id = "root";
		String pw = "password";




		try {
		//JDBCドライバの登録
		Class.forName("com.mysql.jdbc.Driver");
		//MySQLへ接続（データベースまで）
		cnct =DriverManager.getConnection(url,id,pw);
		st =cnct.createStatement();
		//SQL文の発行


		for(int i= 0;i<namelist1.size();i++) {

		rs = st.executeQuery("SELECT stock_no  from product  where pro_name = '"+namelist1.get(i)+"';");

		while(rs.next()) {

				int stock = rs.getInt("stock_no");
			//String na1 =rs.getString("pro_name");
				stock-=numlist1.get(i);
				String stock1=String.valueOf(stock);

				String sql ="UPDATE product SET stock_no = ? where pro_name = ?";
		//pst = cnct.prepareStatement("update prodct set stock_no ='"+?+"'where pro_name ='"+?.get(i)+"'");

				pst = cnct.prepareStatement(sql);
				pst.setString(1,stock1);
				pst.setString(2,(String)namelist1.get(i));
				pst.executeUpdate();

		}

		}

		Rd = req.getRequestDispatcher("Finish.jsp");
		Rd.forward(req,resp);
		Rd=null;
	}
		catch (ClassNotFoundException ex) {
			ex.printStackTrace();





		} catch (SQLException e) {

			e.printStackTrace();
		}
	}
	}
	}
