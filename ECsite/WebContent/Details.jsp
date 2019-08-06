<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList"%>
    <jsp:useBean id ="sb" scope="session" class="web.SearchBean1"/>
<% int stockn = ((Integer)(session.getAttribute("S"))).intValue(); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="NewFile.css">
<title>商品紹介</title>
</head>
<body>
<div class="header"></div>
<div align="center">
	<h1>商品詳細</h1>
	<table  align="center">
	<tr><td><img src="<jsp:getProperty name="sb" property="pro_img"/>"height="200" width="300"></td></tr>
	</table>
	<table border="1" align="center" width="400" height="150" cellspacing="0" cellpadding="5" bordercolor="black">
		<tr>
			<td align="center"bgcolor="#CCCCCC">商品名</td>
			<td><jsp:getProperty name="sb" property="pro_name"/></td>
		</tr>
		<tr>
			<td align="center"bgcolor="#CCCCCC">カテゴリ</td>
			<td><jsp:getProperty name="sb" property="cat_name"/></td>
		</tr>
		<tr>
			<td align="center"bgcolor="#CCCCCC">価格</td>
			<td><jsp:getProperty name="sb" property="pro_price"/></td>
		</tr>
		<tr>
			<td align="center"bgcolor="#CCCCCC">在庫</td>
			<td><jsp:getProperty name="sb" property="stock_no"/></td>
		</tr>
		<tr>
			<td align="center"bgcolor="#CCCCCC">商品紹介</td>
			<td><jsp:getProperty name="sb" property="pro_msg"/></td>
		</tr>

	</table>

	<br>
</div>
<div class="carthe" align="center">
<form action="cart"method="post">
	<table style="float:center;">
	<%if(stockn==0){ %>
	<tr>
		<td>在庫がありません</td>
	</tr>
	<% }else{ %>
		<tr>
			<td><select name="num">
			<% for(int i=1;i<=stockn;i++){ %>
			<option value="<%= i %>"><%= i %><% } %></option>
			</select></td>
			<td><button class="button2" type="submit" name="cart" value="カートへ">カートへ</button></td>
		</tr>
		<%} %>
	</table>
</form>
<form action="search" method="post">
<table style="float:center;">
		<tr>
			<td><button class="button2" type="submit" name="act" value="戻る">戻る</button></td>
		</tr>
</table>

</form>
</div>

</body>
</html>