<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${blog.title }</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	<div id="container">
		<jsp:include page="/WEB-INF/views/blog/include/header.jsp" />
		<div id="wrapper">
			<div id="content">
				<div class="blog-content">
					<h4>${post.title }</h4>
					<p>
						${post.contents }
					<p>		
				</div>
				<ul class="blog-list">
					<c:forEach var="post" items="${postList }">
						<li><a href="${pageContext.request.contextPath }/${requestScope.id}/${post.categoryNo}/${post.no}">
								${post.title }</a> 
							<span>${post.regDate }</span>	
						</li>
					</c:forEach>
				</ul>
			</div>
		</div>

		<div id="extra">
			<div class="blog-logo">
				<img src="${pageContext.request.contextPath}${blog.logo}">
			</div>
		</div>

		<div id="navigation">
			<h2>카테고리</h2>
			<ul>
				<c:forEach var="list" items="${categoryList }">
					<li><a href="${pageContext.request.contextPath }/${requestScope.id}/${list.no}">${list.name }</a></li>
				</c:forEach>
			</ul>
		</div>
		<jsp:include page="/WEB-INF/views/blog/include/footer.jsp" />	
	</div>
</body>
</html>