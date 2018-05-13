<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
<%@ include file="/WEB-INF/include/base.jsp" %>
<script type="text/javascript">
	$(function(){
		//
		$("#clearCart").click(function(){
			if(!confirm("确认清空购物车吗！？？？"))
			return false;
		});
		//
		$(".delA").click(function(){
		var title = $(this).parents("tr").find("td:eq(0)").html();
		if(!confirm("确认删除该《"+title+"》吗"))
		return false;
		});
		
		
		
		//为修改购物项的数量绑定函数
		$(".count_input").change(function(){			
			//获取修改后的购物项数量
			var count = this.value;
			//判断count是否为正整数
			var reg = /^\+?[1-9][0-9]*$/;
			if(!reg.test(count)){
				this.value = this.defaultValue;
				alert("请输入正确的数量");
				return;
			}
			//获取数量文本节点
			var $td = $(this).parents("tr").find("td:eq(3)");
			
			//获取图书bookId
			 var bookId = this.name;
			 var url = "${pageContext.request.contextPath}/client/CartServlet";
			 var params = {
			 "method":"updateCount",
			 "bookId":bookId,
			 "count":count
			 };
			 $.post(url,params,function(data){
			 	$(".b_count").html(data.totalCount);
			 	$(".b_price").html(data.totalAmount);
			 	$td.html(data.amount);
			 	
			 },"JSON");
			/*  window.location = "${pageContext.request.contextPath}/client/CartServlet?method=updateCount&bookId="+bookId+"&count="+count; */
		
		});
	});
</script>
</head>
<body>
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">购物车</span>
			<%@ include file="/WEB-INF/include/user-info.jsp" %>
	</div>
	
	<div id="main">
		<c:choose>
			<c:when test="${empty cart.cartItems }">
			<br><br><br><br><br><br><br>
				<h1 align="center">购物车里还没有商品，尽情添加把！</h1>
			</c:when>
			<c:otherwise>
				<table>
					<tr>
						<td>商品名称</td>
						<td>数量</td>
						<td>单价</td>
						<td>金额</td>
						<td>操作</td>
					</tr>		
					<c:forEach items="${cart.cartItems }" var="item">
						<tr>
							<td>${item.book.title }</td>
							<td><input name="${item.book.id }" class="count_input"type="text" value="${item.count }" style="width:20px;text-align:center;"/></td>
							<td>${item.book.price }</td>
							<td>${item.amount }</td>
							<td><a class="delA" href="client/CartServlet?method=delCartItem&bookId=${item.book.id }">删除</a></td>
						</tr>		
					</c:forEach>
				</table>
				<div class="cart_info">
					<span class="cart_span">购物车中共有<span class="b_count">${cart.totalCount }</span>件商品</span>
					<span class="cart_span">总金额<span class="b_price">${cart.totalAmount }</span>元</span>
					<span class="cart_span"><a id="clearCart" href="client/CartServlet?method=clear">清空购物车</a></span>
					<span class="cart_span"><a href="client/OrderClientServlet?method=checkOut">去结账</a></span>
				</div>
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