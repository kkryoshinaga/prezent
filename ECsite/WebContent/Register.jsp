<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規登録画面</title>
</head>
<body>
<h1>新規登録画面</h1>
<p>以下の情報を入力してください。</p><br>
<form action="RegisterClear" method="POST">
		<table>
		<tr>
			<td>名前（id）:</td>
			<td><input type="text" name="id" required></td>
		</tr>
		<tr>
			<td>パスワード（password）:</td>
			<td><input type="password" name="password" required></td>
		</tr>
		</table>
		<br>
		<input type="submit" value="送信">
	</form>
</body>
</html>