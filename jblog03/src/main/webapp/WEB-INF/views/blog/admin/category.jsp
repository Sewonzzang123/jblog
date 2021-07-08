<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>JBlog</title>
<Link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/jblog.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/assets/js/ejs/ejs.js"></script>
<script>
	var listEJS = new EJS(
			{
				url : "${pageContext.request.contextPath }/assets/js/ejs/list-template.ejs",
			});

	var listItemEJS = new EJS(
			{
				url : "${pageContext.request.contextPath }/assets/js/ejs/listitem-template.ejs",
			});
	var fetch = function() {
		$
				.ajax({
					url : "${pageContext.request.contextPath }/${requestScope.id}/admin/category/api/list",
					dataType : "json", // 받을 때 포멧
					type : "get", // 요청 메서드
					success : function(response) {
						categoryNo = response.data.length;
						let html = listEJS.render(response);
						$(".admin-cat tbody").append(html);
					},
				});
	};

	var add = function(event) {
		event.preventDefault();
		var vo = {};
		vo.name = $("#name").val();
		if (vo.name == "") {
			alert('카테고리 이름을 입력하세요.');
			$("#name").focus();
			return;
		}
		vo.description = $("#description").val();
		if (vo.description == "") {
			alert('설명을 입력하세요.');
			$("#description").focus();
			return;
		}

		$
				.ajax({
					url : "${pageContext.request.contextPath }/${requestScope.id}/admin/category/api/add",
					dataType : "json", // 받을 때 포멧
					type : "post", // 요청 메서드
					contentType : "application/json",
					data : JSON.stringify(vo),
					success : function(response) {
						categoryNo += 1;
						response.data.categoryNo = categoryNo;
						let html = listItemEJS.render(response.data);
						$(".admin-cat tbody").prepend(html);

						$("#name").val("");
						$("#description").val("");
					},
				});
	};
	var categoryNo = 0;
	$(function() {
		fetch();

		$("#category-add").submit(function() {
			add(event);
		});

		// live event : 존재하지 않는 element의 이벤트 핸들러를 미리 등록
		// delegation(위임) -> document
		$(document).on('click', '#delete', function(event) {
			event.preventDefault();
			var $this = $(this);
			let no = $(this).data("no");
			deleteCategory(no, $this);
		});

		var deleteCategory = function(no, $this) {
			$.ajax({
						url : "${pageContext.request.contextPath }/${requestScope.id}/admin/category/api/delete/"
								+ no,
						dataType : "json", // 받을 때 포멧
						type : "post", // 요청 메서드,
						success : function(response) {
							if (response.result != 'success') {
								response.error(response.message);
								return;
							}
							$this.parent().parent().remove();
							categoryNo -= 1;
							numberformat();
						},
					});
		};

	});
	
	var numberformat = function(){
		var length = $(".admin-cat tbody tr").length+1;

		for(var i=1; i<length; i++){
			let html = "<td>"+(--length)+"</td>";
			$(".admin-cat tbody tr:nth-child("+i+") td").first().remove();			
			$(".admin-cat tbody tr:nth-child("+i+")").prepend(html);
		}
		
		};
</script>
</head>
<body>
	<div id="container">
		<jsp:include page="/WEB-INF/views/blog/include/header.jsp" />
		<div id="wrapper">
			<div id="content" class="full-screen">
				<ul class="admin-menu">
					<li><a
						href="${pageContext.request.contextPath }/${requestScope.id}/admin/basic">기본설정</a>
					</li>
					<li class="selected">카테고리</li>
					<li><a
						href="${pageContext.request.contextPath }/${requestScope.id}/admin/write">글작성</a>
					</li>
				</ul>
				<table class="admin-cat">
					<thead>
					<tr>
						<th>번호</th>
						<th>카테고리명</th>
						<th>포스트 수</th>
						<th>설명</th>
						<th>삭제</th>
					</tr>
					</thead>
					<tbody>
					
					</tbody>
				</table>

				<h4 class="n-c">새로운 카테고리 추가</h4>
				<form id="category-add" action="" method="POST">
					<table id="admin-cat-add">
						<tr>
							<td class="t">카테고리명</td>
							<td><input type="text" id="name" /></td>
						</tr>
						<tr>
							<td class="t">설명</td>
							<td><input type="text" id="description" /></td>
						</tr>
						<tr>
							<td class="s">&nbsp;</td>
							<td><input type="submit" value="카테고리 추가" /></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<jsp:include page="/WEB-INF/views/blog/include/footer.jsp" />
	</div>
</body>
</html>
