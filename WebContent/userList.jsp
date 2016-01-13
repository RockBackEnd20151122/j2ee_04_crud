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
	<table border="1" bordercolor="black">
		<tr>
			<td>userId</td>
			<td>userName</td>
			<td>password</td>
			<td>gender</td>
			<td>maintain</td>
		</tr>
		<tr><!-- get result key from session default -->
			<c:if test="${empty result}">Sorry, u don't have data!</c:if>
			<c:if test="${not empty result}">
				<c:forEach var="user" items="${result}">
					<tr>
						<td>${user.userId}</td>
						<td>${user.userName}</td>
						<td>${user.password}</td>
						<td>${user.gender}</td>
						<td>
							<c:url var="url" value="/userDeleteServlet">
								<c:param name="userId">${user.userId}</c:param>
							</c:url>
							<a href="${url}">delete</a>
							<c:url var="url" value="/UserUpdateServlet">
								<c:param name="userId">${user.userId}</c:param>
							</c:url> <a href="${url}">modify</a>
						</td>
					</tr>
				</c:forEach>
			</c:if>
		</tr>
	</table>
</body>
</html>