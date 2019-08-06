
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "web.SerchServlet, java.util.ArrayList" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="NewFile.css">
<title>検索ページ</title>
</head>
<body>
<div class="header"></div>
<h1 align="center">検索</h1>
<form action = "search"method="POST">
<p align="center"><input id="box" type size="40"="text" name="name" placeholder="キーワードを入力">

<select name="category">
<option value="0">指定なし</option>
<option value="1">食品</option>
<option value="2">家電</option>
<option value="3">生活用品</option>
<option value="4">コスメ</option>
<option value="5">ゲーム</option>
</select>
<button class="button2" type="submit" name="act" value="検索" >Search</button></p>

<% ArrayList nameList = (ArrayList)session.getAttribute("searchResult1") ;%>
<% ArrayList priceList = (ArrayList)session.getAttribute("searchResult2") ;%>

<%if(nameList.size()==0){ %>
<p align="center">検索結果がありません。</p>
<% }else{ %>
<table border="1" width="500" cellspacing="0"align="center" cellpadding="5" bordercolor="black">

<tr>
<th  bgcolor="#CCCCCC">商品名</th>
<th bgcolor="#CCCCCC">価格</th>
<th bgcolor="#CCCCCC">詳細</th>
</tr>
<% for(int i=0;i<nameList.size();i++){%>
<tr>

<td align="left"><%= nameList.get(i)%></td>
<td align="right">&yen;<%= priceList.get(i) %></td>
<td align="center"><button  class="button2" type="submit" name="act" value=<%= nameList.get(i)%> >詳細</button></td>
</tr>
<%} %>
<%} %>
</table>

</form>

</body>
</html>