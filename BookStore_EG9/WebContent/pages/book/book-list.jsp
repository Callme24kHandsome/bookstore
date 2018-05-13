<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>书城首页</title>
<%@ include file="/WEB-INF/include/base.jsp" %>
</head>
<script type="text/javascript">
	$(function(){
		$(".addCart").click(function(){
			//向cartservlet发送请求
			var url = "${pageContext.request.contextPath}/client/CartServlet";
			var params = {
			"method":"add2Cart",
			"bookId":this.id
			};
			$.post(url,params,function(data){
				var title = data.title;
				var totalCount = data.totalCount;
				$("#count_span").html("您的购物车中有  "+totalCount+" 件商品");
				$("#title_info").html('您刚刚将<span id="title_span" style="color: red"> '+title+' </span>加入到了购物车中');
				
			},"JSON");
		});
	});
</script>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">网上书城</span>
			<%@ include file="/WEB-INF/include/user-info.jsp" %>
	</div>
	
	<div id="main">
		<div id="book">
			<div class="book_cond">
				<form action="client/BookClientServlet" method="get">
					价格：
					<input type="hidden" name="method" value="findBookByPrice">
					<input type="text" name="min" value="${param.min }"> 元 - 
					<input type="text" name="max" value="${param.max }"> 元
					<input type="submit" value="查询">
				</form>
			</div>
			
			<div style="text-align: center">
				<c:choose>
					<c:when test="${empty cart.cartItems }">
						<span id="count_span">您的购物车中还没有商品</span>
					</c:when>
					<c:otherwise>
						<span id="count_span">您的购物车中有 ${cart.totalCount } 件商品</span>
					</c:otherwise>
				</c:choose>
				<%-- <c:if test="${!empty title }">
					<div>
						您刚刚将<span id="title_span" style="color: red">${title }</span>加入到了购物车中
					</div>
					<!-- 将title属性从session中移除 -->
					<c:remove var="title" scope="session"/>
				</c:if> --%>
				<div id="title_info">
				
				</div>
				
			</div>
			<c:choose>
				<c:when test="${empty page.data }">
					<h1 style="text-align:center">当前条件下没有找到图书！</h1>
				</c:when>
				<c:otherwise>
					<c:forEach items="${page.data }" var="book">
						<div class="b_list">
							<div class="img_div">
								<img class="book_img" alt="" src="${pageContext.request.contextPath }${book.imgPath}" />
							</div>
							<div class="book_info">
								<div class="book_name">
									<span class="sp1">书名:</span> <span class="sp2">${book.title }</span>
								</div>
								<div class="book_author">
									<span class="sp1">作者:</span> <span class="sp2">${book.author }</span>
								</div>
								<div class="book_price">
									<span class="sp1">价格:</span> <span class="sp2">${book.price }</span>
								</div>
								<div class="book_sales">
									<span class="sp1">销量:</span> <span class="sp2">${book.sales }</span>
								</div>
								<div class="book_amount">
									<span class="sp1">库存:</span> <span class="sp2">${book.stock }</span>
								</div>
								<div class="book_add">
									<button id="${book.id }" class="addCart">加入购物车</button>
									<%-- <a href="client/CartServlet?method=add2Cart&bookId=${book.id }">加入购物车</a> --%>
								</div>
							</div>
						</div>
					</c:forEach>
				</c:otherwise>
			</c:choose>

		</div>
		
		<%@ include file="/WEB-INF/include/page.jsp" %>
	
	</div>
	
	<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2015
		</span>
	</div>
</body>
</html>