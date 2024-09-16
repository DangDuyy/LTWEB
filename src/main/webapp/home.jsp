<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri = "jakarta.tags.fmt"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>
<%@taglib prefix="fn" uri="jakarta.tags.functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<body>
<c:if test="${msg==false}">Đăng nhập sai</c:if>
<c:if test="${msg==true}"> HELLO ${uname} ${pwd} </c:if>
</body>
</html>