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
    <title><fmt:message key="html.title.statistics"/></title>
</head>
<body>

<jsp:include page="/WEB-INF/fragments/header.jsp"/>

<div class="container">
    <div class="row justify-content-center">
        <h2 class="text-center"><fmt:message key="html.text.statistics.heading"/></h2>
    </div>
    <div class="row justify-content-center">
        <table class="table table-bordered table-striped text-center">
            <thead class="thead-dark">
                <tr>
                    <th><fmt:message key="html.text.statistics.conference.name"/></th>
                    <th><fmt:message key="html.text.statistics.num.of.speakers"/></th>
                    <th><fmt:message key="html.text.statistics.num.of.reports"/></th>
                    <th><fmt:message key="html.text.statistics.num.of.registrations"/></th>
                    <th><fmt:message key="html.text.statistics.num.of.visitors"/></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${requestScope.conferences}" var="conference" varStatus="status">
                    <tr>
                        <td class="align-middle">${conference.name}</td>
                        <td class="align-middle">${requestScope.conferenceIdSpeakersCount[conference.id]}</td>
                        <td class="align-middle">
                            <c:choose>
                                <c:when test="${requestScope.conferenceIdReportsCount[conference.id] == null}">
                                    0
                                </c:when>
                                <c:otherwise>
                                    ${requestScope.conferenceIdReportsCount[conference.id]}
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td class="align-middle">
                            <c:choose>
                                <c:when test="${requestScope.conferenceIdUsersCount[conference.id] == null}">
                                    0
                                </c:when>
                                <c:otherwise>
                                    ${requestScope.conferenceIdUsersCount[conference.id]}
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td class="align-middle">
                            <c:choose>
                                <c:when test="${requestScope.conferenceIdVisitors[conference.id] == null}">
                                    0
                                </c:when>
                                <c:otherwise>
                                    ${requestScope.conferenceIdVisitors[conference.id]}
                                </c:otherwise>
                            </c:choose>

                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <div class="row">
        <nav>
            <ul class="pagination">
                <c:if test="${paginationAttributes.currentPage != 1}">
                    <li class="page-item">
                        <a class="page-link" href="${pageContext.request.contextPath}/${sessionScope.role}/statistics?records-per-page=${paginationAttributes.recordsPerPage}&current-page=${paginationAttributes.currentPage-1}"><fmt:message key="html.text.pagination.previous" /></a>
                    </li>
                </c:if>
                <c:forEach begin="1" end="${paginationAttributes.nOfPages}" var="i">
                    <c:choose>
                        <c:when test="${paginationAttributes.currentPage eq i}">
                            <li class="page-item active">
                                <a class="page-link">${i}<span class="sr-only">(current)</span></a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li class="page-item">
                                <a class="page-link" href="${pageContext.request.contextPath}/${sessionScope.role}/statistics?records-per-page=${paginationAttributes.recordsPerPage}&current-page=${i}">${i}</a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <c:if test="${paginationAttributes.currentPage lt paginationAttributes.nOfPages}">
                    <li class="page-item">
                        <a class="page-link" href="${pageContext.request.contextPath}/${sessionScope.role}/statistics?records-per-page=${paginationAttributes.recordsPerPage}&current-page=${paginationAttributes.currentPage+1}"><fmt:message key="html.text.pagination.next" /></a>
                    </li>
                </c:if>
            </ul>
        </nav>

        <div>
            <form action="${pageContext.request.contextPath}/${sessionScope.role}/statistics"
                  class="form-inline" onchange="submit()">
                <input type="hidden" name="current-page" value="${paginationAttributes.currentPage}">
                <label class="ml-2" for="records"><fmt:message key="html.text.pagination.records.per.page" /></label>
                <select class="custom-select ml-1" id="records" name="records-per-page">
                    <option value="5" <c:if test="${paginationAttributes.recordsPerPage == 5}">selected</c:if>>5</option>
                    <option value="10" <c:if test="${paginationAttributes.recordsPerPage == 10}">selected</c:if>>10</option>
                    <option value="15" <c:if test="${paginationAttributes.recordsPerPage == 15}">selected</c:if>>15</option>
                </select>
            </form>
        </div>
    </div>

</div>

</fmt:bundle>
</body>
</html>
