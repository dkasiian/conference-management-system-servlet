<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <meta charset="UTF-8" >
    <link href="<c:url value='/css/bootstrap-reboot.min.css' />" rel="stylesheet" type="text/css">
    <link href="<c:url value='/css/bootstrap.min.css' />" rel="stylesheet" type="text/css">
    <link href="<c:url value='/css/main.css' />" rel="stylesheet" type="text/css">
    <fmt:setLocale value="${ empty sessionScope.lang ? 'en_US' : sessionScope.lang}" scope="session"/>
    <fmt:bundle basename="messages">
    <title><fmt:message key="html.title.index"/></title>
</head>
<body>

<jsp:include page="WEB-INF/fragments/header.jsp"/>
<div class="container">
    <div class="row justify-content-center">
        <h1><fmt:message key="html.text.index.welcome"/></h1>
    </div>
    <div class="row justify-content-center">
        <p><fmt:message key="html.text.index.main"/></p>
    </div>
</div>
</fmt:bundle>

</body>
</html>
