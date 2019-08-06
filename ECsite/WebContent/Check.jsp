<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="web.DetailsServlet" %>
<%@ page import="java.util.ArrayList" %>
<% int price = 0; %>
<% double sum = 0; %>
<% double Taxprice = 0; %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="NewFile.css">
<title>購入確認</title>
</head>
<div class="header"></div>
<div align="center">
<h1>購入してもよろしいでしょうか？？</h1><br>
</div>
<div align="center">
<% ArrayList namelist = new ArrayList(); %>
<% ArrayList pricelist = new ArrayList(); %>

<% namelist =  (ArrayList) session.getAttribute("namelist"); %>
<% pricelist = (ArrayList) session.getAttribute("pricelist"); %>
<% ArrayList numlist = (ArrayList)  session.getAttribute("numlist"); %>
<table border="1" width="400" "height="350" cellspacing="0" cellpadding="5" bordercolor="black">
<tr>
<th align="center"bgcolor="#CCCCCC">商品名</th>
<th align="center"bgcolor="#CCCCCC">単価</th>
<th align="center"bgcolor="#CCCCCC">数量</th>
</tr>
<% for(int i=0;i<namelist.size();i++){  %>
<tr>
<td align="center"><%= namelist.get(i) %></td>
<td align="right">&yen;<%= pricelist.get(i) %></td>
<td align="right"><%= numlist.get(i) %></td>
<% } %>
</tr>
<tr>
<th colspan="2"bgcolor="#CCCCCC">消費税</th>
<td align="right">&yen;<%= session.getAttribute("Taxprice") %></td>

</tr>
<tr>
<th colspan="2"bgcolor="#CCCCCC">合計金額</th>

<td align="right">&yen;<%= session.getAttribute("sumPrice") %></td>
</tr>

</table>
</div>
<div align="center">
<table><tr>
<td>
<form action = "search" method="post">
<button class="button2" name="act" type="submit" value="いいえ">いいえ</button>
</form>
</td>
<td>
<form action = "Buy" method="post">
<button class="button2" type="submit" value="はい">はい</button>
</form>
</td>
</tr></table>
</div>
</body>
</html>