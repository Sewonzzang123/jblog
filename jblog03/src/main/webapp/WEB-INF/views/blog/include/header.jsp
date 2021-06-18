<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div id="header">
			<h1>${blog.title }</h1>
			<ul>
			<li><a href="${pageContext.request.contextPath }">메인으로 가기</a></li>
			<c:if test='${empty authUser }'><li><a href="${pageContext.request.contextPath }/user/login">로그인</a></li></c:if>
			<c:if test='${not empty authUser }'>
			<li><a href="${pageContext.request.contextPath }/user/logout">로그아웃</a></li>
			<li><a href="${pageContext.request.contextPath }/${authUser.id }">내 블로그</a></li>
			</c:if>
			<c:if test='${not empty authUser && authUser.id==requestScope.id }'>
			<li><a href="${pageContext.request.contextPath }/${requestScope.id }/admin/basic">블로그 관리</a></li>	
			</c:if>			
			</ul>
		</div>