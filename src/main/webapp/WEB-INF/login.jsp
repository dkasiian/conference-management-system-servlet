<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:bundle basename="messages">
<html>
<head>
    <meta charset="UTF-8">
    <link href="<c:url value='/css/bootstrap-reboot.min.css' />" rel="stylesheet">
    <link href="<c:url value='/css/bootstrap.min.css' />" rel="stylesheet" type="text/css">
    <link href="<c:url value='/css/main.css' />" rel="stylesheet" type="text/css">
    <title><fmt:message key="html.title.login"/></title>
</head>
<body>

<jsp:include page="/WEB-INF/fragments/header.jsp"/>
<div class="container">
    <div class="row justify-content-center">
        <div class="col col-lg-4">
            <div class="card">
                <article class="card-body">
                    <h4 class="card-title"><fmt:message key="html.text.login.form.head"/></h4>

                    <form method="post">
                        <div class="form-group">
                            <label for="login"><fmt:message key="html.text.login.form.login"/></label>
                            <input id="login" class="form-control" type="text" name="login"/>
                            <div class="text-danger">${incorrect_login}</div>
                        </div>
                        <div class="form-group">
                            <label for="password"><fmt:message key="html.text.login.form.password"/></label>
                            <input id="password" class="form-control" type="password" name="password"/>
                            <div class="text-danger">${incorrect_password}</div>
                        </div>
                        <div class="form-group">
                            <button type="submit" class="btn btn-success btn-block">
                                <fmt:message key="html.text.log.in"/>
                            </button>
                        </div>
                    </form>
                </article>
            </div>
        </div>
    </div>
</div>
</fmt:bundle>
</body>
</html>
