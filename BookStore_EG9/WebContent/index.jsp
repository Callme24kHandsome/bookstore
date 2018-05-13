<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>书城首页</title>
<%@ include file="/WEB-INF/include/base.jsp" %>
</head>
<body>
	<jsp:forward page="client/BookClientServlet?method=findBook"></jsp:forward>
</body>
</html>