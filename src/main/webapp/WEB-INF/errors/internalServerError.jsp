<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <meta charset="UTF-8">
    <link href="<c:url value='/css/bootstrap-reboot.min.css' />" rel="stylesheet" type="text/css">
    <link href="<c:url value='/css/bootstrap.min.css' />" rel="stylesheet" type="text/css">
    <link href="<c:url value='/css/main.css' />" rel="stylesheet" type="text/css">
    <fmt:setLocale value="${ empty sessionScope.lang ? 'en_US' : sessionScope.lang}" scope="session"/>
    <fmt:bundle basename="messages">
    <title><fmt:message key="html.title.internal.server"/></title>
</head>

<body>
<jsp:include page="../fragments/header.jsp"/>
<div class="container justify-content-center vertical-center">
    <div class="row justify-content-center">
        <img src="<c:url value="/images/500.png"/>" width="1000" alt="Internal Server Error"/>
        <br />
        <p class="lead text-center">
            <fmt:message key="html.internal.server.warning"/>
        </p>
    </div>
    <div class="row justify-content-center">
        <a class="btn btn-success" href="<c:url value="/"/>" role="button">
            <fmt:message key="html.text.back.home"/>
        </a>
    </div>
</div>
</body>
</fmt:bundle>
</html>
