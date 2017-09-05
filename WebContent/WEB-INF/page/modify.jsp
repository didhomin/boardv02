<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath }" />
<c:choose>
<c:when test="${not empty article}">
	<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<script type="text/javascript">
	function modifyOk(){
		if(confirm("수정???")) {
			if(document.getElementById("subject").value  == "") {
				alert("제목을 입력하세요!");
				return;
			} else if(document.getElementById("subject").value.length >80) {
				alert("제목은 80글자 이내로 작성해 주세요!");
				return;
			} else if(document.getElementById("content").value == "") {
				alert("내용을 입력하세요!");
				return;
			} else {
				document.modifyForm.action = "${root}/modifySave.do";
				document.modifyForm.submit();
			}
		}
	}
	
	</script>
	</head>
	<body>
	<form method="post" action="" name="modifyForm">
	<input type="hidden" name="seq" value="${article.seq }">
	<table>
			<tr>
				<td>글번호</td>
				<td colspan="3">${article.seq }
				</td>
			</tr>
			<tr>
				<td>등록일</td>
				<td colspan="3">${article.insertdate }</td>
			</tr>
			<tr>
				<td>수정일</td>
				<td colspan="3">
					<c:choose>
					<c:when test="${article.updatedate eq article.insertdate}">
						미수정
					</c:when>
					<c:otherwise>
						${article.updatedate }
					</c:otherwise>
				</c:choose>
				</td>
			</tr>
			<tr>
				<td>제목</td>
				<td colspan="3"><input type="text" class="" id="subject" name="subject" value="${article.subject }">
				</td>
			</tr>
			<tr>
				<td>내용</td>
				<td colspan="3">
					<textarea id="content" name="content" rows="30px;" cols="30px;">${article.content }</textarea>
				</td>
			</tr>
				
		
		<tr style="text-align: center;">
			<td><input type="button" value="저장" onclick="javascript:modifyOk();"></td>
			<td><input type="button" value="취소" onclick="javascript:if(confirm('취소??')) {document.location.href= '${root}/view.do?seq=${article.seq }'};"></td>
		</tr>
	</table>
	</form>
	
	</body>
	</html>
</c:when>
<c:otherwise>
<% 
response.sendRedirect(request.getContextPath()+"/list.do");
%>
</c:otherwise>
</c:choose>
