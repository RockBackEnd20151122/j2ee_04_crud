<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.List,com.pojo.Student" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/index.css">
<script type="text/javascript" src="js/jquery-1.8.1.js"></script>
<script type="text/javascript" src="js/index.js"></script>
<title>Insert title here</title>
</head>
<body>
	<%
		List<Student> listStudent = (List<Student>) request.getSession().getAttribute("listStudent");
		String str = "";
		for (int i = 0; listStudent != null && i < listStudent.size(); i++) {
			Student student = listStudent.get(i);
			str += String.format(
					"<tr>\n\t<td>%s</td>\n\t<td>%s</td>\n\t<td>%d</td>\n\t<td>%s</td>\n\t<td><a href='StudentServlet?router=delete&id=%s'>delete</a><input type='checkbox' name='checkboxid' value='%s' /></td><td><a href='StudentServlet?router=modify&id=%s'>update</a></td></tr>\n",
					student.getId(), student.getName(), student.getAge(), student.getAddress(), student.getId(), student.getId(), student.getId());
		}
	%>
	<table>
		<tr>
			<td>id</td>
			<td>name</td>
			<td>age</td>
			<td>address</td>
			<td>operation</td>
			<td>update</td>
		</tr>
		<%=str%>
	</table>
	<div class= "deleteSelected">deleteselected</div>
</body>
</html>