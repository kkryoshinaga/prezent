package web;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DetailsServlet extends HttpServlet {
	ArrayList listName = new ArrayList();
	ArrayList listPrice = new ArrayList();
	ArrayList<Integer> listNum = new ArrayList<Integer>();
	ArrayList listDetail = new ArrayList();
	HashMap hm = new HashMap();
	int price = 0;
	int Taxprice = 0;
	int sumPrice = 0;
	final double tax = 0.08;
	int num2 =0;
	int price2 =0;
	int count;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		//受け取る変数の文字コードを指定
		req.setCharacterEncoding("UTF-8");

		int num =  Integer.parseInt(req.getParameter("num"));

		HttpSession session = req.getSession(false);
		if(session == null) {
			RequestDispatcher rd = req.getRequestDispatcher("Login.jsp");
			//rd.forward(req, resp);
		}else {

			listNum.add(num);
			session.setAttribute("numlist",listNum);

			listDetail = (ArrayList) session.getAttribute("ListDetail");
			listDetail = (ArrayList) session.getAttribute("ListDetail");
			listName = (ArrayList) session.getAttribute("listName");
			listPrice = (ArrayList) session.getAttribute("listPrice");
			count = ((Integer)(session.getAttribute("count"))).intValue();
			System.out.println(count);
			System.out.println(listDetail.size());
			if((listDetail.size())!=1) {

			}else {
				price = 0;
				price2 =0;
				Taxprice=0;
				sumPrice=0;
			}

			for(int i=0;i+count<listDetail.size();i++){

				String url = "jdbc:mysql://localhost/ECsite";
				String id = "root";
				String pass = "password";

				Connection cnct = null;
				java.sql.Statement st = null;
				ResultSet rs = null;

				try {
					//System.out.println("bbb");
					Class.forName("com.mysql.jdbc.Driver");
					cnct = DriverManager.getConnection(url, id, pass);
					//System.out.println("a");
					String quely=("SELECT pro_name,pro_price FROM PRODUCT WHERE pro_name ='"+listDetail.get(i+count)+"'");
					st = cnct.createStatement();
					//System.out.println("b");
					rs = st.executeQuery(quely);

					while(rs.next()) {
						String pname = rs.getString("pro_name");
						int pprice = rs.getInt("pro_price");
						listName.add(pname);
						//System.out.println(pname);
						listPrice.add(pprice);
						//System.out.println(listNum.get(i));//listNum.get(i);
						//hm.put(pname,listNum.get(i+count));
						//if(hm.containsKey(pname)==true) {
						//	String a = (String)hm.get(pname);
						//	int b = Integer.parseInt(a);

						//}

						num2 = listNum.get(i+count);
						price +=  num2*pprice;
						session.setAttribute("namelist", listName);
						session.setAttribute("pricelist", listPrice);

						}

					//String price1 = (String)listPrice.get(i);
					//int num2 = Integer.parseInt(num1);
					//price2 = Integer.parseInt(price1);



					Taxprice = (int) (price*tax);
					sumPrice = price + Taxprice;
					session.setAttribute("Taxprice", Taxprice);
					session.setAttribute("sumPrice", sumPrice);

					if(!(session==null)) {
				session.setAttribute("count", count);
					RequestDispatcher rd = req.getRequestDispatcher("Cart.jsp");
					rd.forward(req, resp);}


				}catch(ClassNotFoundException ex) {
					ex.printStackTrace();
				}catch(SQLException ex) {
					ex.printStackTrace();
				}finally {
					try {
						if(rs != null) {
							rs.close();
						}
						if(st != null) {
							st.close();
						}
						if( cnct != null) {
							cnct.close();
						}
					}catch(Exception ex){

						}

				}
				} //後で消す


		}
	}
}
