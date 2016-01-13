<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<form action="<c:url value = "/queryAction"/>" method="post">
		<table border="1" bordercolor="blue">
			<tr>
				<td>用户名</td>
				<td><input type="text" name="userName"></td>
			</tr>
			<tr>
				<td colspan="6" align="center"><input type="submit" value="查询">
				</td>
			</tr>
		</table>
	</form>
	<table border="1" bordercolor="black">
		<tr>
			<td>用户id</td>
			<td>用户名</td>
			<td>密码</td>
			<td>生日</td>
			<td>职业</td>
			<td>状态</td>
			<td>操作</td>
		</tr>
		<c:if test="${empty result}">
			无您想要的数据
		</c:if>
		<c:if test="${not empty result}">
			<c:forEach var="userDto" items="${result}">
				<tr>
					<td>${userDto.userId}</td>
					<td>${userDto.userName}</td>
					<td>${userDto.password}</td>
					<td></td>
					<td></td>
					<td>${userDto.status}</td>
					<td><c:url var="url" value="/deleteAction">
							<c:param name="userId">${userDto.userId}</c:param>
						</c:url> 
						<a href="${url}">删除</a> 
						<c:url var="url" value="/queryForUpdateAction">
							<c:param name="userId">${userDto.userId}</c:param>
						</c:url>
						 <a href="${url}">修改</a>
				    </td>
				</tr>
			</c:forEach>
		</c:if>


	</table>

</body>
</html>