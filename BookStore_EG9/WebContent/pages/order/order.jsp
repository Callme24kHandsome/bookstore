<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的订单</title>
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
			<span class="wel_word">我的订单</span>
			<%@ include file="/WEB-INF/include/user-info.jsp" %>
	</div>
	
	<div id="main">
		<c:choose>
			<c:when test="${empty list }">
				<h1>当前用户还没有订单</h1>
			</c:when>
			<c:otherwise>
				<table>
					<tr>
						<td>日期</td>
						<td>数量</td>
						<td>金额</td>
						<td>状态</td>
						<td>详情</td>
					</tr>	
					<c:forEach items="${list }" var="order">
						<tr>
							<!-- 格式化时间 -->
							<td><fmt:formatDate value="${order.orderTime }" type="both"/></td>	
							<td>${order.totalCount }</td>
							<td>${order.totalAmount }</td>
							<c:choose>
								<c:when test="${order.state==0 }">
									<td><span>未发货</span></td>
								</c:when>
								<c:when test="${order.state==1 }">
									<td><a href="client/OrderClientServlet?method=takeBook&orderId=${order.id }">确认收货</a></td>
								</c:when>
								<c:when test="${order.state==2 }">
									<td><span>交易完成</span></td>
								</c:when>
							</c:choose>
							
							<td><a href="client/OrderClientServlet?method=orderInfo&orderId=${order.id }">查看详情</a></td>
						</tr>
					</c:forEach>		
				</table>
			</c:otherwise>
		</c:choose>	
		
	
	</div>
	
	<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2015
		</span>
	</div>
</body>
</html>