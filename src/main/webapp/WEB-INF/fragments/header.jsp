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
            <nav class="navbar navbar-expand-lg navbar-dark mb-3" style="background-color: #63b175;">
                <div class="container-fluid">
                    <ul class="navbar-nav mr-auto">
                        <li class="nav-item">
                            <a class="navbar-brand" href="${pageContext.request.contextPath}/">
                                <fmt:message key="html.text.brand"/>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link text-white" href="${pageContext.request.contextPath}/guest/conferences">
                                <fmt:message key="html.text.conferences"/>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link text-white" href="${pageContext.request.contextPath}/guest/past-conferences">
                                <fmt:message key="html.text.past.conferences"/>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link text-white" href="${pageContext.request.contextPath}/guest/future-conferences">
                                <fmt:message key="html.text.future.conferences"/>
                            </a>
                        </li>
                    </ul>
                    <ul class="navbar-nav navbar-right">
                        <li class="nav-item">
                            <a class="nav-link text-white" href="${pageContext.request.contextPath}/guest/login">
                                <fmt:message key="html.text.login"/>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link text-white" href="${pageContext.request.contextPath}/guest/registration">
                                <fmt:message key="html.text.registration"/>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link text-white" href="#">[${sessionScope.role}]</a>
                        </li>
                        <li class="nav-item">
                            <form method="post" class="form-horizontal mb-0"
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
            <nav class="navbar navbar-expand-lg navbar-dark mb-3" style="background-color: #63b175;">
                <div class="container-fluid">
                    <ul class="nav navbar-nav">
                        <li class="nav-item">
                            <a class="navbar-brand" href="${pageContext.request.contextPath}/">
                                <fmt:message key="html.text.brand"/>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link text-white" href="${pageContext.request.contextPath}/${sessionScope.role}/conferences">
                                <fmt:message key="html.text.conferences"/>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link text-white" href="${pageContext.request.contextPath}/${sessionScope.role}/past-conferences">
                                <fmt:message key="html.text.past.conferences"/>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link text-white" href="${pageContext.request.contextPath}/${sessionScope.role}/future-conferences">
                                <fmt:message key="html.text.future.conferences"/>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link text-white" href="${pageContext.request.contextPath}/${sessionScope.role}/rating">
                                <fmt:message key="html.text.rating"/>
                            </a>
                        </li>
                        <c:if test="${sessionScope.role == 'admin'}">
                            <li class="nav-item">
                                <a class="nav-link text-white" href="${pageContext.request.contextPath}/${sessionScope.role}/statistics">
                                    <fmt:message key="html.text.statistic"/>
                                </a>
                            </li>
                        </c:if>
                    </ul>
                    <ul class="nav navbar-right" >
                        <li class="nav-item">
                            <a class="nav-link text-white" href="${pageContext.request.contextPath}/${sessionScope.role}/logout">
                                <fmt:message key="html.text.logout"/>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link text-white" href="#">[${sessionScope.role}]</a>
                        </li>
                        <li>
                            <form method="post" class="form-horizontal mb-0"
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