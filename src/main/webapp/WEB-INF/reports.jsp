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
    <title>Reports</title>
    <fmt:setLocale value="${ empty sessionScope.lang ? 'en_US' : sessionScope.lang}" scope="session"/>
    <fmt:bundle basename="messages">
</head>
<body>

<jsp:include page="/WEB-INF/fragments/header.jsp"/>

<div class="container">
    <div class="row justify-content-center">
        <h1 class="text-center">Available reports:</h1>
    </div>
    <div class="row justify-content-center">

        <c:if test="${sessionScope.role == 'admin' || sessionScope.role == 'speaker'}">
            <form action="${pageContext.request.contextPath}/${sessionScope.role}/add-report" method="post">
                <input type="hidden" name="conferenceId" value="${conferenceId}">
                <button class="btn btn-info" type="submit">
                    <fmt:message key="html.add.report"/>
                </button>
            </form>
        </c:if>

        <table class="table table-bordered table-striped text-center">
            <thead class="thead-dark">
            <tr>
                <th>Theme</th>
                <th>Date time</th>
                <th>Speaker Name</th>
                <th>Speaker Last Name</th>
                <th>Speaker email</th>
                <c:if test="${sessionScope.role == 'admin' || sessionScope.role == 'speaker'}">
                    <th>Action</th>
                </c:if>
            </tr>
            </thead>
            <tbody>

            <c:forEach items="${requestScope.reports}" var="report" varStatus="status">
                <tr>
                    <td class="align-middle">${report.theme}</td>
                    <td class="align-middle">${f:formatLocalDateTime(report.dateTime, 'dd.MM.yyyy HH:mm')}</td>
                    <td class="align-middle">${requestScope.speakers.get(status.index).name}</td>
                    <td class="align-middle">${requestScope.speakers.get(status.index).surname}</td>
                    <td class="align-middle">${requestScope.speakers.get(status.index).email}</td>
                    <c:if test="${sessionScope.role == 'admin' || (sessionScope.role == 'speaker' && report.speakerId == userId)}">
                        <td class="align-middle">
                            <form action="${pageContext.request.contextPath}/${sessionScope.role}/update-report"
                                  method="post">
                                <input type="hidden" name="conferenceId" value="${conferenceId}">
                                <input type="hidden" name="reportId" value="${report.id}">
                                <button class="btn btn-info" type="submit">
                                    <fmt:message key="html.update"/>
                                </button>
                            </form>
                            <form action="${pageContext.request.contextPath}/${sessionScope.role}/delete-report"
                                  method="post" class="mb-0">
                                <input type="hidden" name="conferenceId" value="${conferenceId}">
                                <input type="hidden" name="reportId" value="${report.id}">
                                <button class="btn btn-danger" type="submit">
                                    <fmt:message key="html.delete"/>
                                </button>
                            </form>
                        </td>
                    </c:if>
                </tr>
            </c:forEach>

            </tbody>
        </table>
    </div>

    <c:if test="${!requestScope.reports.isEmpty()}">
    <div class="row">
        <nav>
            <ul class="pagination">
                <c:if test="${paginationAttributes.currentPage != 1}">
                    <li class="page-item">
                        <a class="page-link" href="/${sessionScope.role}/conferences/${conferenceId}/reports?records-per-page=${paginationAttributes.recordsPerPage}&current-page=${paginationAttributes.currentPage-1}">Previous</a>
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
                                <a class="page-link" href="/${sessionScope.role}/conferences/${conferenceId}/reports?records-per-page=${paginationAttributes.recordsPerPage}&current-page=${i}">${i}</a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <c:if test="${paginationAttributes.currentPage lt paginationAttributes.nOfPages}">
                    <li class="page-item">
                        <a class="page-link" href="/${sessionScope.role}/conferences/${conferenceId}/reports?records-per-page=${paginationAttributes.recordsPerPage}&current-page=${paginationAttributes.currentPage+1}">Next</a>
                    </li>
                </c:if>
            </ul>
        </nav>
    </div>
    </c:if>

</div>

</fmt:bundle>
</body>
</html>
