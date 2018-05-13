<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>订单详情</title>
<%@ include file="/WEB-INF/include/base.jsp" %>
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
</style>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">订单详情</span>
			<%@ include file="/WEB-INF/include/user-info.jsp" %>
	</div>
	
	<div id="main">
		<table>
			<tr>
				<td>封面</td>
				<td>书名</td>
				<td>作者</td>
				<td>单价</td>
				<td>数量</td>
				<td>金额</td>
			</tr>
			<c:forEach items="${list }" var="orderItem">
				<tr>
					<td><img style="width: 100px ; height: 100px;" src="${pageContext.request.contextPath }${orderItem.imgPath}" /></td>
					<td>${orderItem.title }</td>
					<td>${orderItem.author }</td>
					<td>${orderItem.price }</td>
					<td>${orderItem.count }</td>
					<td>${orderItem.amount }</td>
				</tr>	
			</c:forEach>
		</table>
	</div>
	
	<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2015
		</span>
	</div>
</body>
</html>