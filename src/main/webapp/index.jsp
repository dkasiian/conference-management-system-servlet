<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://example.com/functions" prefix="f" %>

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
    <c:if test="${sessionScope.role == 'guest'}">
        <div class="row justify-content-center">
            <h1><fmt:message key="html.text.index.welcome"/></h1>
        </div>
        <div class="row justify-content-center">
            <p><fmt:message key="html.text.index.main"/></p>
        </div>
    </c:if>

    <c:if test="${sessionScope.role != 'guest'}">

        <div class="row justify-content-center">
            <form action="${pageContext.request.contextPath}/" class="form-inline" onchange="submit()">
                <label class="ml-2 text-center" for="records">
                    <fmt:message key="html.text.index.days.to.announcements" />
                </label>
                <select class="custom-select ml-1" id="records" name="daysToAnnouncement">
                    <c:choose>
                        <c:when test="${daysToAnnouncement == null}">
                            <option value="1">1</option>
                            <option value="2">2</option>
                            <option value="3" selected>3</option>
                            <option value="4">4</option>
                            <option value="5">5</option>
                        </c:when>
                        <c:otherwise>
                            <option value="1" <c:if test="${daysToAnnouncement == 1}">selected</c:if>>1</option>
                            <option value="2" <c:if test="${daysToAnnouncement == 2}">selected</c:if>>2</option>
                            <option value="3" <c:if test="${daysToAnnouncement == 3}">selected</c:if>>3</option>
                            <option value="4" <c:if test="${daysToAnnouncement == 4}">selected</c:if>>4</option>
                            <option value="5" <c:if test="${daysToAnnouncement == 5}">selected</c:if>>5</option>
                        </c:otherwise>
                    </c:choose>
                </select>
            </form>
        </div>

        <c:choose>
            <c:when test="${requestScope.conferences.isEmpty()}">
                <h3 class="text-center"><fmt:message key="html.text.index.no.announcements" /></h3>
            </c:when>
            <c:otherwise>
                <h3 class="text-center mb-2"><fmt:message key="html.text.index.announcements" /></h3>

                <c:forEach items="${requestScope.conferences}" var="conference" varStatus="status">
                    <div class="card border-success mb-3 text-center">
                        <div class="card-header">
                            <strong class="text-success h3">${conference.name}</strong>
                        </div>
                        <div class="card-body">
                            <p class="card-text">
                                <fmt:message key="html.text.index.message.about.registration" />
                                <br>
                                <em>${f:formatLocalDateTime(conference.dateTime, 'dd.MM.yyyy HH:mm')}</em>
                                <br>
                                <em>${conference.location}</em>
                            </p>
                        </div>
                        <div class="card-footer text-muted">
                            <div><fmt:message key="html.text.index.remaining.time.placeholders" /></div>
                                ${requestScope.remainingTimes[status.index]}
                        </div>
                    </div>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </c:if>

</div>
</fmt:bundle>

</body>
</html>
