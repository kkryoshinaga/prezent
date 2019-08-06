<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="NewFile.css">
<title>ログイン画面</title>
<script type="text/javascript">

function check(){
	var flag = 0;

if(document.form1.name.value==""){
	flag=1;
}
else if (document.form1.Pw.value==""){
	flag=1;
}

if(flag){
	window.alert('名前またはパスワードが未記入力があります。');
	return false;
}
else{
	return true;
}
}

</script>
</head>
<div class="header1"></div>
<body>
<form action="LoginError" method="post" name="form1" onSubmit="return check()">

<h1 align="center">LOGIN</h1>

<p align="center" class="in">NAME　　　　　<input type= "text"  name="name" ></p>
<p align="center" class="in">PASSWORD　<input type="password" name="Pw" ></p>
<p align="center" class="in"><font color="#ff0000">※名前またはパスワードが間違っています。</font></p>
<p align="center"><button class="button3" type="submit" value="ログイン">Login</button></p>


</form>

</body>
</html>