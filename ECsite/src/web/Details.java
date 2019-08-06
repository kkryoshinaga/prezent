package web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Details extends HttpServlet {


	HashMap map = new HashMap();
	ArrayList<String> nameList = new ArrayList<String>();
	ArrayList<Integer>priceList = new ArrayList<Integer>();
	ArrayList<Integer>numList = new ArrayList<Integer>();
	int s;
	int Price;
	double Tax = 0.08;
	int TaxPrice;
	int SumPrice;
	SearchBean1 sb = new SearchBean1();
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		HttpSession session = req.getSession(false);
		if(session==null) {
			RequestDispatcher rd = req.getRequestDispatcher("Login.jsp");
			//rd.forward(req, resp);
		}else {



			req.setCharacterEncoding("UTF-8");
			//買う数をint型numに格納
			int num = Integer.parseInt(req.getParameter("num"));

			//Beanからの商品名と価格の情報を変数に格納
			String pro_name = (String)session.getAttribute("pro_name");


			Object pppprice = session.getAttribute("pro_price");
			String pprice = (String)pppprice;
			int price = Integer.parseInt(pprice);

			if((nameList.contains(pro_name))==true) {

				for(int i=0;i<nameList.size();i++) {

					String name =nameList.get(i);

					if(name==pro_name) {

						 s = i;
						 break;
					}
				}

				int u = numList.get(s);
				num+= u;
				nameList.set(s,pro_name);
				priceList.set(s,price);
				numList.set(s,num);

			}else {

			nameList.add(pro_name);
			priceList.add(price);
			numList.add(num);

			}

			Price = Price + (num * price);
			TaxPrice = (int)(Price * Tax);
			SumPrice = Price + TaxPrice;



			map.put("nameList", nameList);
			map.put("priceList", priceList);
			map.put("numList", numList);

			session.setAttribute("Taxprice", TaxPrice);
			session.setAttribute("sumPrice", SumPrice);
			session.setAttribute("namelist", nameList);
			session.setAttribute("pricelist", priceList);
			session.setAttribute("numlist", numList);
			session.setAttribute("map", map);

			RequestDispatcher rd = req.getRequestDispatcher("Cart.jsp");
			rd.forward(req, resp);
		}
	}
}
