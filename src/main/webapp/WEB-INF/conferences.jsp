<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://example.com/functions" prefix="f" %>

<html>
<head>
    <meta charset="UTF-8">
    <link href="<c:url value='/css/bootstrap-reboot.min.css' />" rel="stylesheet" type="text/css">
    <link href="<c:url value='/css/bootstrap.min.css' />" rel="stylesheet" type="text/css">
    <link href="<c:url value='/css/main.css' />" rel="stylesheet" type="text/css">
    <title>Conferences</title>
    <fmt:setLocale value="${ empty sessionScope.lang ? 'en_US' : sessionScope.lang}" scope="session"/>
    <fmt:bundle basename="messages">
</head>
<body>

<jsp:include page="/WEB-INF/fragments/header.jsp"/>

<div class="container">
    <div class="row justify-content-center">
        <h1 class="text-center">Here you can find all conferences:</h1>
    </div>

    <div class="row justify-content-center">

        <c:if test="${sessionScope.role == 'admin' && requestScope.conferencesLink != 'past-conferences'}">
            <form action="${pageContext.request.contextPath}/${sessionScope.role}/${requestScope.conferencesLink}/add-conference" method="post">
                <input type="hidden" name="current-page" value="${paginationAttributes.currentPage}">
                <input type="hidden" name="records-per-page" value="${paginationAttributes.recordsPerPage}">
                <input type="hidden" name="conferencesLink" value="${requestScope.conferencesLink}">
                <input type="hidden" name="isGetForm" value="true">
                <button class="btn btn-info" type="submit">
                    <fmt:message key="html.add.conference"/>
                </button>
            </form>
        </c:if>

        <table class="table table-bordered table-striped text-center">
            <thead class="thead-dark">
            <tr>
                <th><fmt:message key="html.conference"/></th>
                <th><fmt:message key="html.datetime"/></th>
                <th><fmt:message key="html.location"/></th>
                <c:if test="${sessionScope.role != 'guest'}">
                    <th>Registration for attendance</th>
                </c:if>
                <c:if test="${sessionScope.role == 'admin'}">
                    <th>Action</th>
                </c:if>
<%--                <c:if test="${sessionScope.role != 'guest'}">--%>
<%--                    <th>Announcement</th>--%>
<%--                </c:if>--%>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${requestScope.conferences}" var="conference" varStatus="status">
                <tr>
                    <td class="align-middle">
                        <form action="${pageContext.request.contextPath}/${sessionScope.role}/conferences/${conference.id}/reports"
                              method="post" class="mb-0">
                            <input type="hidden" name="conferenceId" value="${conference.id}">
                            <button class="btn btn-dark" type="submit">${conference.name}</button>
                        </form>
                    </td>
                    <td class="align-middle">${f:formatLocalDateTime(conference.dateTime, 'dd.MM.yyyy HH:mm')}</td>
                    <td class="align-middle">${conference.location}</td>
                    <c:if test="${sessionScope.role != 'guest'}">
                    <td class="align-middle">
                        <c:choose>
                            <c:when test="${requestScope.isRegister[status.index]}">
                                <form action="${pageContext.request.contextPath}/${sessionScope.role}/register-unregister"
                                      method="post" class="mb-0">
                                    <input type="hidden" name="conferenceId" value="${conference.id}">
                                    <input type="hidden" name="current-page" value="${paginationAttributes.currentPage}">
                                    <input type="hidden" name="records-per-page" value="${paginationAttributes.recordsPerPage}">
                                    <input type="hidden" name="conferencesLink" value="${requestScope.conferencesLink}">
                                    <input type="hidden" name="command" value="unregister">
                                    <button type="submit" <c:if test="${conference.dateTime < now}">disabled</c:if>
                                            class="btn btn-warning">
                                        Unregister
                                    </button>
                                </form>
                            </c:when>
                            <c:otherwise>
                                <form action="${pageContext.request.contextPath}/${sessionScope.role}/register-unregister"
                                      method="post" class="mb-0">
                                    <input type="hidden" name="conferenceId" value="${conference.id}">
                                    <input type="hidden" name="current-page" value="${paginationAttributes.currentPage}">
                                    <input type="hidden" name="records-per-page" value="${paginationAttributes.recordsPerPage}">
                                    <input type="hidden" name="conferencesLink" value="${requestScope.conferencesLink}">
                                    <input type="hidden" name="command" value="register">
                                    <button type="submit" class="btn btn-primary"
                                            <c:if test="${conference.dateTime < now}">disabled</c:if>>
                                        Register
                                    </button>
                                </form>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    </c:if>
                    <c:if test="${sessionScope.role == 'admin'}">
                        <td class="align-middle">
                            <form action="${pageContext.request.contextPath}/${sessionScope.role}/${requestScope.conferencesLink}/update-conference?records-per-page=${paginationAttributes.recordsPerPage}&current-page=${paginationAttributes.currentPage}"
                                  method="post">
                                <input type="hidden" name="conferenceId" value="${conference.id}">
<%--                                <input type="hidden" name="current-page" value="${paginationAttributes.currentPage}">--%>
<%--                                <input type="hidden" name="records-per-page" value="${paginationAttributes.recordsPerPage}">--%>
                                <input type="hidden" name="conferencesLink" value="${requestScope.conferencesLink}">
                                <button class="btn btn-info" type="submit">
                                    <fmt:message key="html.update"/>
                                </button>
                            </form>
                            <form action="${pageContext.request.contextPath}/${sessionScope.role}/${requestScope.conferencesLink}/delete-conference?records-per-page=${paginationAttributes.recordsPerPage}&current-page=${paginationAttributes.currentPage}"
                                  method="post" class="mb-0">
                                <input type="hidden" name="conferenceId" value="${conference.id}">
<%--                                <input type="hidden" name="current-page" value="${paginationAttributes.currentPage}">--%>
<%--                                <input type="hidden" name="records-per-page" value="${paginationAttributes.recordsPerPage}">--%>
                                <input type="hidden" name="conferencesLink" value="${requestScope.conferencesLink}">
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

    <div class="row">
        <nav>
            <ul class="pagination">
                <c:if test="${paginationAttributes.currentPage != 1}">
                    <li class="page-item">
                        <a class="page-link" href="/${sessionScope.role}/${requestScope.conferencesLink}?records-per-page=${paginationAttributes.recordsPerPage}&current-page=${paginationAttributes.currentPage-1}">Previous</a>
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
                                <a class="page-link" href="/${sessionScope.role}/${requestScope.conferencesLink}?records-per-page=${paginationAttributes.recordsPerPage}&current-page=${i}">${i}</a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <c:if test="${paginationAttributes.currentPage lt paginationAttributes.nOfPages}">
                    <li class="page-item">
                        <a class="page-link" href="/${sessionScope.role}/${requestScope.conferencesLink}?records-per-page=${paginationAttributes.recordsPerPage}&current-page=${paginationAttributes.currentPage+1}">Next</a>
                    </li>
                </c:if>
            </ul>
        </nav>
    </div>

</div>

</fmt:bundle>
</body>
</html>
