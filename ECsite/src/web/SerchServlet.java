package web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SerchServlet extends HttpServlet {
	List<String> nameList = new ArrayList<String>();
	List<String> priceList = new ArrayList<String>();
	ArrayList<String> ListDetail = new ArrayList<String>();
	SearchBean1 sb = new SearchBean1();
	ResultSet rsst = null;
	ResultSet rsst1 = null;
	RequestDispatcher rd =null;
	ArrayList listName = new ArrayList();
	ArrayList listPrice = new ArrayList();
	ArrayList<Integer> listNum = new ArrayList<Integer>();
	//int count ;
	Object n;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		HttpSession session = req.getSession(false); //後で消す
		if(session==null) {

			rd = req.getRequestDispatcher("Login.jsp");
			rd.forward(req, resp);
			rd =null;
		}else {
		String url ="jdbc:mysql://localhost/ecsite";
		String id ="root";
		String pw = "password";

		Connection cnct = null;
		Statement st = null;
		ResultSet rs = null;


		String act = req.getParameter("act");


		if(act.equals("検索")) {

			nameList.clear();
			priceList.clear();
			String name=req.getParameter("name");
			int cate = Integer.parseInt(req.getParameter("category"));

			try{
				Class.forName("com.mysql.jdbc.Driver");
				cnct =DriverManager.getConnection(url,id,pw);
				st = cnct.createStatement();
				if(name.isEmpty())  {
					if(cate==0) {
						rs = st.executeQuery("SELECT * FROM product");
					}else {
						rs = st.executeQuery("SELECT * FROM product WHERE cat_id ='"+cate+"';");
					}
				}else {
					if(cate==0) {
						rs = st.executeQuery("SELECT * FROM product WHERE pro_name LIKE '%"+name+"%';");
					}else {
						rs = st.executeQuery("SELECT * FROM product WHERE cat_id ='"+cate+"' AND pro_name LIKE '%"+name+"%';");
					}
				}

				while(rs.next()) {
					String pro_name = rs.getString("pro_name");
					String price = rs.getString("pro_price");

						nameList.add(pro_name);
						priceList.add(price);


				}

				session.setAttribute("searchResult1", nameList);
				session.setAttribute("searchResult2", priceList);

				rd = req.getRequestDispatcher("SearchResult.jsp");
				rd.forward(req, resp);
				rd =null;

			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				try {
					if (rs!=null) rs.close();
					if (st!=null) st.close();
					if (cnct!=null) cnct.close();
				} catch(Exception ex) { }
			}
		}else if(act.equals("戻る")||act.equals("買い物を続ける")||act.equals("いいえ")||act.equals("continue")){
			try{
				if(act.equals("戻る")) {
				//	ListDetail.remove(count);
				//	count--;
					//session.setAttribute("count", count);
				}

				if(act.equals("continue")) {
					listName.clear();
					listPrice.clear();
					listNum.clear();
					//count=-1;
					ListDetail.clear();
					//session.setAttribute("count", count);
					session.setAttribute("listName",listName);
					session.setAttribute("listNum",listNum);
					session.setAttribute("listPrice",listPrice);

				}

				Class.forName("com.mysql.jdbc.Driver");
				cnct =DriverManager.getConnection(url,id,pw);
				st = cnct.createStatement();
				rs = st.executeQuery("SELECT * FROM product");
				while(rs.next()) {
					String pro_name = rs.getString("pro_name");
					String price = rs.getString("pro_price");

					if(nameList.contains(pro_name)==false) {
						nameList.add(pro_name);
						priceList.add(price);
					}

				}

				session.setAttribute("searchResult1", nameList);
				session.setAttribute("searchResult2", priceList);

				 rd = req.getRequestDispatcher("SearchResult.jsp");
				rd.forward(req, resp);
				rd =null;


			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				try {
					if (rs!=null) rs.close();
					if (st!=null) st.close();
					if (cnct!=null) cnct.close();
				} catch(Exception ex) { }
			}

		}else {
			String name = req.getParameter("act");
			System.out.println(name);
			n = session.getAttribute("count");

			if(n==null||n.equals(-1)) {
		//		count=0;
				//listName.clear();
				//listPrice.clear();
				//listNum.clear();
				//ListDetail.clear();

			}else {
			//	count++;
			}

			System.out.println(ListDetail.size());
		//	System.out.println(count);


			ListDetail.add(name);
		/*	if((ListDetail.size()-count)>=2 || (count-ListDetail.size())>=2) {
				listName.clear();
				listPrice.clear();
				listNum.clear();
				ListDetail.clear();
				count=0;
				ListDetail.add(name);
			}*/
			session.setAttribute("ListDetail",ListDetail);
			System.out.println(ListDetail.size());
			//System.out.println(count);
			Connection cnct1 = null;
			java.sql.Statement stmt = null;


			try {
				Class.forName("com.mysql.jdbc.Driver");
				cnct1 = DriverManager.getConnection(url, id, pw);

				String quely1=("SELECT pro_name,stock_no,pro_price,pro_img,pro_msg from product where pro_name = '"+name+"';");
				String quely2 =("SELECT cat_name from category where cat_id = (select cat_id from product where pro_name = '"+name+"');");
				stmt = cnct1.createStatement();
				rsst = stmt.executeQuery(quely1);


				while(rsst.next()){
					String pro_name = rsst.getString("pro_name");
					int stock_no	= rsst.getInt("stock_no");
					session.setAttribute("S", stock_no);

					String pro_price = rsst.getString("pro_price");
					String pro_img = rsst.getString("pro_img");
					String pro_msg = rsst.getString("pro_msg");
					sb.setPro_name(pro_name);
					sb.setStock_no(stock_no);
					sb.setPro_price(pro_price);
					sb.setPro_img(pro_img);
					sb.setPro_msg(pro_msg);
					session.setAttribute("pro_name", pro_name);
					session.setAttribute("pro_price", pro_price);
				}

				rsst1 = stmt.executeQuery(quely2);

				while(rsst1.next()) {
					String cat_name = rsst1.getString("cat_name");
					sb.setCat_name(cat_name);

				}
				session.setAttribute("sb", sb);


				session.setAttribute("listName",listName);
				session.setAttribute("listNum",listNum);
				session.setAttribute("listPrice",listPrice);


		//		session.setAttribute("count", count);
				rd = req.getRequestDispatcher("Details.jsp");
				rd.forward(req, resp);
				rd =null;

			}catch(ClassNotFoundException ex) {
				ex.printStackTrace();
			}catch(SQLException ex) {
				ex.printStackTrace();
			}finally {
				try {
					if(rsst != null) {
						rsst.close();
					}
					if(rsst1 != null) {
						rsst1.close();
					}
					if(stmt != null) {
						stmt.close();
					}
					if( cnct1 != null) {
						cnct1.close();
					}
				}catch(Exception ex){

					}

			}
		}

		}
	}

}

