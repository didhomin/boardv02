<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath }" />
<c:choose>
<c:when test="${not empty boardList}">
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<title>Insert title here</title>
<script type="text/javascript">

$(document).on('click','.view', function () {
	var seq = $(this).children().eq(0).text();
	document.location.href = "${root}/view.do?seq="+seq;
});

function page(pg) {
	document.location.href = "${root}/list.do?pg="+pg;
}
</script>
</head>
<body>
<table>
	<thead>
		<tr>
			<th>글번호</th>
			<th>제목</th>
			<th>등록일</th>
			<th>수정일</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="board" items="${boardList}">
		<tr align="center" class="view">
			<td>${board.seq }
			</td>
			<td>${board.subject }
			</td>
			<td>${board.insertdate}
			</td>
			<td>
				<c:choose>
					<c:when test="${board.updatedate eq board.insertdate}">
						미수정
					</c:when>
					<c:otherwise>
						${board.updatedate }
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
		</c:forEach>
	<tr>
	<td colspan="4" style="text-align: center;">${page }</td>
	<tr>
	<tr>
	<td colspan="4" style="text-align: center;"><a href="${root }/write.do">글쓰기</a></td>
	<tr>
	</tbody>
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