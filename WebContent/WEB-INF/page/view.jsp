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
</head>
<body>
<table>

		
		<tr>
			<td>글번호</td>
			<td colspan="3">${article.seq }
			</td>
		</tr>
		<tr>
			<td>등록일</td>
			<td colspan="3">${article.insertdate}</td>
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
			<td colspan="3">${article.subject }
			</td>
		</tr>
		<tr>
			<td>내용</td>
			<td colspan="3">${article.content }
			</td>
		</tr>
			
	
	<tr style="text-align: center;">
		<td><input type="button" value="수정"  onclick="javascript:document.location.href= '${root }/modify.do?seq=${article.seq }';"></td>
		<td><input type="button" value="삭제" onclick="javascript:if(confirm('삭제??')) {document.location.href= '${root }/delete.do?seq=${article.seq }'};"></td>
		<td><input type="button" value="목록" onclick="javascript:document.location.href= '${root}/list.do';"></td>
	</tr>
</table>
</body>
</html>
</c:when>
<c:otherwise>
<% 
response.sendRedirect(request.getContextPath()+"/list.do");
%>
</c:otherwise>
</c:choose>
