<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js" ></script>
<script>
$(function(){
	var btn =$('#btn-checkemail');
	btn.click(function(){
		var id = $('#blog-id').val();
		$.ajax({
				url:"/jblog03/user/api/checkid?id="+id,
				type:"get",
				dataType:"json",
				error:function(xhr, status, e){
					//xml http request
					//status
					//error
					console.error(status,e);
					return;
				},
				success:function(response){	
					if(response.result != "success"){ //통신 실패
						console.error(response.message);
						return;
					}				
					if(response.data){
						alert('존재하는 아이디 입니다. 다른 아이디를 사용해 주세요.');
						$('#blog-id').val("");
						$('#blog-id').focus();
						return;
					}
					
					$('#btn-checkemail').hide();
					$('#img-checkemail').show();
					
				}
		});
	});
});

</script>
</head>
<body>
	<div class="center-content">
		<jsp:include page="/WEB-INF/views/include/header.jsp" />
		<form:form 
		modelAttribute="userVo"
		class="join-form" 
		id="join-form" 
		method="post" 
		action="${pageContext.request.contextPath }/user/join">
			<label class="block-label" for="name">이름</label>
			<form:input path="name"/>
			<spring:hasBindErrors name="userVo">
						<c:if test='${errors.hasFieldErrors("name") }'>
							<spring:message code='${errors.getFieldError("name").codes[0] }' /> 
						</c:if>
			</spring:hasBindErrors>
			<label class="block-label" for="blog-id">아이디</label>
			<form:input id="blog-id" path="id"/>
			<spring:hasBindErrors name="userVo">
						<c:if test='${errors.hasFieldErrors("id") }'>
							<spring:message code='${errors.getFieldError("id").codes[0] }' /> 
						</c:if>
			</spring:hasBindErrors> 
			<input id="btn-checkemail" type="button" value="id 중복체크">
			<img id="img-checkemail" style="display: none;" src="${pageContext.request.contextPath}/assets/images/check.png">

			<label class="block-label" for="password">패스워드</label>
			<form:input path="password"/>
			<spring:hasBindErrors name="userVo">
						<c:if test='${errors.hasFieldErrors("password") }'>
							<spring:message code='${errors.getFieldError("password").codes[0] }' /> 
						</c:if>
			</spring:hasBindErrors> 

			<fieldset>
				<legend>약관동의</legend>
				<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
				<label class="l-float">서비스 약관에 동의합니다.</label>
			</fieldset>

			<input type="submit" value="가입하기">

		</form:form>
	</div>
</body>
</html>
