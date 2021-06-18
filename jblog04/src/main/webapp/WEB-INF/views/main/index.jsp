<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	<div class="center-content">
		<jsp:include page="/WEB-INF/views/include/header.jsp" />
		<form class="search-form" method="get" action="${pageContext.request.contextPath }/search">
			<fieldset>
				<input type="text" name="keyword" />
				<input type="submit" value="검색" />
			</fieldset>
			<fieldset>
				<input type="radio" name="which" value="blogTitle"> <label>블로그 제목</label>
				<input type="radio" name="which" value="blogUser"> <label>블로거</label>
			</fieldset>
		</form>
		<table>
		<c:forEach var="blog" items="${list }">			
			<tr>
				<td><img style='width:50px'
						src="${pageContext.request.contextPath }/${blog.logo }" /></td>
				<td><a href="${pageContext.request.contextPath }/${blog.id }">${blog.title }</a></td>
			</tr>				
		</c:forEach>
		</table>
	</div>
</body>
</html>