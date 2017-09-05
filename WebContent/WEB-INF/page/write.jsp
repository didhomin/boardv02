<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath }" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function ok(){
	if(confirm("저장???")) {
		if(document.getElementById("subject").value == "") {
			alert("제목을 입력하세요!");
			return;
		} else if(document.getElementById("subject").value.length >80) {
			alert("제목은 80글자 이내로 작성해 주세요!");
			return;
		} else if(document.getElementById("content").value == "") {
			alert("내용을 입력하세요!");
			return;
		} else {
			document.writeForm.action = "${root}/writeSave.do";
			document.writeForm.submit();
		}
	}
}
function cancel(){
	if(confirm("취소???")) {
		document.location.href= "${root}/list.do";
	}
}
</script>
</head>
<body>
<form method="post" action="" name="writeForm">
	<div>
		<label class="">제목</label>
		<input type="text" class="" id="subject" name="subject">
	</div>
	<div>
		<label>내용</label>
		<textarea id="content" name="content" rows="20px;" cols="30px;"></textarea>
	</div>
	<div>
		<input type="button" value="저장" onclick="javascript:ok();">
		<input type="button" value="취소" onclick="javascript:cancel();">
	</div>
</form>
</body>
</html>