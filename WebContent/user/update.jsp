<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="<c:url value = "/saveAction"/>" method="post">
		<input type="hidden" name="userId" value="${userDto.userId}">
		<table border="1">
			<tr>
				<td>用户名<input type="text" name="userName"
					value="${userDto.userName}"></td>
				<td>状态：<select name="status">
						<option value="01" <c:if test="${userDto.status eq '01'}">selected</c:if>>有效</option>
						<option value="02" <c:if test="${userDto.status eq '02'}">selected</c:if>>失效</option>
						<option value="03" <c:if test="${userDto.status eq '03'}">selected</c:if>>锁定</option>
				</select></td>
			</tr>
			<tr>
				<td><input type="submit" value="保存"></td>
			</tr>
		</table>
	</form>
</body>
</html>