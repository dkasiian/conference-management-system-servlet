<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <meta charset="UTF-8" >
    <link href="<c:url value='/css/bootstrap-reboot.min.css' />" rel="stylesheet" type="text/css">
    <link href="<c:url value='/css/bootstrap.min.css' />" rel="stylesheet" type="text/css">
    <title>Statistics</title>
    <fmt:setLocale value="${ empty sessionScope.lang ? 'en_US' : sessionScope.lang}" scope="session"/>
    <fmt:bundle basename="messages">
</head>
<body>

<jsp:include page="/WEB-INF/fragments/header.jsp"/>

<div class="container">
    <div class="row">
        <h1 class="text-center">Statistics:</h1>
    </div>
    <div class="row">
        <table class="table table-bordered table-striped">
            <thead class="thead-dark">
                <tr>
                    <th>Conference name</th>
                    <th>Number of speakers</th>
                    <th>Number of reports</th>
                    <th>Number of registrations</th>
                    <th>Number of visitors</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${requestScope.conferences}" var="conference" varStatus="status">
                    <tr>
                        <td>${conference.name}</td>
                        <td>${requestScope.conferenceIdSpeakersCount[conference.id]}</td>
                        <td>
                            <c:choose>
                                <c:when test="${requestScope.conferenceIdReportsCount[conference.id] == null}">
                                    0
                                </c:when>
                                <c:otherwise>
                                    ${requestScope.conferenceIdReportsCount[conference.id]}
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${requestScope.conferenceIdUsersCount[conference.id] == null}">
                                    0
                                </c:when>
                                <c:otherwise>
                                    ${requestScope.conferenceIdUsersCount[conference.id]}
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
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
                        <a class="page-link" href="${pageContext.request.contextPath}/${sessionScope.role}/statistics?records-per-page=${paginationAttributes.recordsPerPage}&current-page=${paginationAttributes.currentPage-1}">Previous</a>
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
                        <a class="page-link" href="${pageContext.request.contextPath}/${sessionScope.role}/statistics?records-per-page=${paginationAttributes.recordsPerPage}&current-page=${paginationAttributes.currentPage+1}">Next</a>
                    </li>
                </c:if>
            </ul>
        </nav>
    </div>

</div>

</fmt:bundle>
</body>
</html>
