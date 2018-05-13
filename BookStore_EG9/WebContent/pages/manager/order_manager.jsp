<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>订单管理</title>
<%@ include file="/WEB-INF/include/base.jsp"%>
</head>
<body>
	<div id="header">
		<img class="logo_img" alt="" src="static/img/logo.gif"> <span
			class="wel_word">订单管理系统</span>
		<%@ include file="/WEB-INF/include/manager-info.jsp"%>
	</div>

	<div id="main">
		<c:choose>
			<c:when test="${empty list }">
				<h1>现在还没有订单</h1>
			</c:when>
			<c:otherwise>
				<table>
					<tr>
						<td>日期</td>
						<td>金额</td>
						<td>详情</td>
						<td>发货</td>

					</tr>
					<c:forEach items="${list }" var="order">
						<tr>
							<td><fmt:formatDate value="${order.orderTime}" type="both" /></td>
							<td>${order.totalAmount }</td>
							<td><a href="#">查看详情</a></td>
							<td><c:choose>
									<c:when test="${order.state==0 }">
										<a href="manager/OrderManagerServlet?method=sendBook&orderId=${order.id }">点击发货</a>
									</c:when>
									<c:when test="${order.state==1 }">
										<span>已发货</span>
									</c:when>
									<c:when test="${order.state==2 }">
										<span>交易结束</span>
									</c:when>
								</c:choose></td>
						</tr>
					</c:forEach>
				</table>
			</c:otherwise>
		</c:choose>
	</div>

	<div id="bottom">
		<span> 尚硅谷书城.Copyright &copy;2015 </span>
	</div>
</body>
</html>