<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/index.css">
<title>Insert title here</title>
</head>
<body>
<form action="StudentServlet" method="post">
	<input type="hidden" name="router" value="add"/>
	
	<div><span>name:</span><input type="text" name="name" /></div>
	<div><span>age:</span><input type="text" name="age" /></div>
	<div><span>address:</span><input type="text" name="address" /></div>
	<div><input type="submit" value="add"/></div>
</form>
</body>
</html>