<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="lang" value="${not empty param.lang ? param.lang : not empty lang ? lang: pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${lang}" scope="session" />
<html lang="${lang}">
<head>
    <meta charset="UTF-8" >
</head>
<body>

<fmt:bundle basename="messages">
    <c:choose>
        <c:when test="${sessionScope.role == 'guest'}">
            <nav class="navbar navbar-expand-lg navbar-light bg-light">
                <div class="container-fluid">
                    <ul class="nav navbar-nav">
                        <li>
                            <a class="navbar-brand" href="${pageContext.request.contextPath}/">
                                <fmt:message key="html.text.brand"/>
                            </a>
                        </li>
                        <li>
                            <a class="nav-link" href="${pageContext.request.contextPath}/guest/conferences">
                                <fmt:message key="html.text.conferences"/>
                            </a>
                        </li>
                        <li>
                            <a class="nav-link" href="${pageContext.request.contextPath}/guest/past-conferences">
                                <fmt:message key="html.text.past.conferences"/>
                            </a>
                        </li>
                        <li>
                            <a class="nav-link" href="${pageContext.request.contextPath}/guest/future-conferences">
                                <fmt:message key="html.text.future.conferences"/>
                            </a>
                        </li>
                    </ul>
                    <ul class="nav navbar-right" >
                        <li>
                            <a class="nav-link" href="${pageContext.request.contextPath}/guest/login">
                                <fmt:message key="html.text.login"/>
                            </a>
                        </li>
                        <li>
                            <a class="nav-link" href="${pageContext.request.contextPath}/guest/registration">
                                <fmt:message key="html.text.registration"/>
                            </a>
                        </li>
                        <li>
                            <a class="nav-link" href="#">[${sessionScope.role}]</a>
                        </li>
                        <li>
                            <form method="post" class="form-horizontal"
                                  action="${pageContext.request.contextPath}/${sessionScope.role}/set-language">
                                <select class="custom-select" name="lang" onchange="submit()">
                                    <option value="en_US" ${sessionScope.lang == 'en_US' ? 'selected' : ''}>English</option>
                                    <option value="uk_UA" ${sessionScope.lang == 'uk_UA' ? 'selected' : ''}>Ukrainian</option>
                                </select>
                            </form>
                        </li>
                    </ul>
                </div>
            </nav>
        </c:when>
        <c:otherwise>
            <nav class="navbar navbar-expand-lg navbar-light bg-light">
                <div class="container-fluid">
                    <ul class="nav navbar-nav">
                        <li>
                            <a class="navbar-brand" href="${pageContext.request.contextPath}/">
                                <fmt:message key="html.text.brand"/>
                            </a>
                        </li>
                        <li>
                            <a class="nav-link" href="${pageContext.request.contextPath}/${sessionScope.role}/conferences">
                                <fmt:message key="html.text.conferences"/>
                            </a>
                        </li>
                        <li>
                            <a class="nav-link" href="${pageContext.request.contextPath}/${sessionScope.role}/past-conferences">
                                <fmt:message key="html.text.past.conferences"/>
                            </a>
                        </li>
                        <li>
                            <a class="nav-link" href="${pageContext.request.contextPath}/${sessionScope.role}/future-conferences">
                                <fmt:message key="html.text.future.conferences"/>
                            </a>
                        </li>
                        <li>
                            <a class="nav-link" href="${pageContext.request.contextPath}/${sessionScope.role}/rating">
                                <fmt:message key="html.text.rating"/>
                            </a>
                        </li>
                        <c:if test="${sessionScope.role == 'admin'}">
                            <li class="nav-item">
                                <a class="nav-link" href="${pageContext.request.contextPath}/${sessionScope.role}/statistics">
                                    <fmt:message key="html.text.statistic"/>
                                </a>
                            </li>
                        </c:if>
                    </ul>
                    <ul class="nav navbar-right" >
                        <li>
                            <a class="nav-link" href="${pageContext.request.contextPath}/${sessionScope.role}/logout">
                                <fmt:message key="html.text.logout"/>
                            </a>
                        </li>
                        <li>
                            <a class="nav-link" href="#">[${sessionScope.role}]</a>
                        </li>
                        <li>
                            <form method="post" class="form-horizontal"
                                  action="${pageContext.request.contextPath}/${sessionScope.role}/set-language">
                                <label>
                                    <select class="custom-select form-control" name="lang" onchange="submit()">
                                        <option value="en_US" ${sessionScope.lang == 'en_US' ? 'selected' : ''}>English</option>
                                        <option value="uk_UA" ${sessionScope.lang == 'uk_UA' ? 'selected' : ''}>Ukrainian</option>
                                    </select>
                                </label>
                            </form>
                        </li>
                    </ul>
                </div>
            </nav>
        </c:otherwise>
    </c:choose>
</fmt:bundle>
</body>
</html>