package web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
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

public class LoginServlet extends HttpServlet {
	String n;
	String p;

	ArrayList nameList = new ArrayList();
	ArrayList priceList = new ArrayList();



	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String name =req.getParameter("name");
		String Pw = req.getParameter("Pw");


		String url = "jdbc:mysql://localhost/ECsite";
		String id = "root";
		String pw = "password";

		Connection cnct = null;
		Statement st = null;
		ResultSet rs = null;
		ResultSet res = null;
		RequestDispatcher rd=null;
		System.out.println("ccccc");

		if(Pw.equals("")||name.equals("")) {
			System.out.println("xxxxxxxxx");
			rd = req.getRequestDispatcher("LoginError02.jsp");
			//rd.forward(req,resp);
			System.out.println("xxxxxxxxx");
			rd=null;


		}

		try {
			//JDBCドライバの登録
			Class.forName("com.mysql.jdbc.Driver");
			//MySQLへ接続（データベースまで）
			cnct =DriverManager.getConnection(url,id,pw);
			st =cnct.createStatement();
			//SQL文の発行
			rs = st.executeQuery("SELECT * from user where user_name ='"+name+"'");
			//while文にてitemテーブルのレコードをnextメソッドで回していく
			System.out.println("aaa");



			while(rs.next()) {
				//getxxxメソッドを使い.itemテーブル内のname.priceを

				n =rs.getString("user_name");
				p = rs.getString("login_pw");

				//データベースからとってきた情報とHTML側から入力された内容が
				//正しければ検索ページへ
				//正しくなければログインページへ


				if (name.equals(n)&&Pw.equals(p)) {
					HttpSession session =req.getSession(true);

					st = cnct.createStatement();

					res = st.executeQuery("SELECT * FROM product");

					while(res.next()) {
						String pro_name = res.getString("pro_name");
						String price = res.getString("pro_price");
						if(nameList.contains(pro_name)==false) {
							nameList.add(pro_name);
							priceList.add(price);
						}
					}

					System.out.println(nameList.size());
					session.setAttribute("searchResult1", nameList);
					session.setAttribute("searchResult2", priceList);

					 rd = req.getRequestDispatcher("SearchResult.jsp");
					rd.forward(req, resp);
					rd =null;
					session.setAttribute("count", null);

					session.removeAttribute("listName");
					session.removeAttribute("listNum");
					session.removeAttribute("listPrice");
					session.removeAttribute("ListDetail");


				}else {
					System.out.println(n+p);
					rd = req.getRequestDispatcher("LoginError.jsp");
					//rd.forward(req,resp);
					n=null;
					p=null;
				}



			}

				rd = req.getRequestDispatcher("LoginError.jsp");
				//rd.forward(req,resp);
				rd=null;


		}catch (ClassNotFoundException ex) {
			ex.printStackTrace();

		}catch(SQLException ex) {
			ex.printStackTrace();
		}finally {
			try {
				//JDBC接続の解除
				if (rs!=null) rs.close();
				if (st!=null) cnct.close();
				if(res!=null) res.close();
			}catch(Exception ex) {
			}
		}

	}
}


