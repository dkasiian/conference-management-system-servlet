<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <meta charset="UTF-8">
    <link href="<c:url value='/css/bootstrap-reboot.min.css' />" rel="stylesheet" type="text/css">
    <link href="<c:url value='/css/bootstrap.min.css' />" rel="stylesheet" type="text/css">
    <title>Conferences</title>
    <fmt:setLocale value="${ empty sessionScope.lang ? 'en_US' : sessionScope.lang}" scope="session"/>
    <fmt:bundle basename="messages">
</head>
<body>

<jsp:include page="/WEB-INF/fragments/header.jsp"/>

<div class="container">
    <div class="row">
        <h1 class="text-center">Available conferences:</h1>
    </div>

    <div class="row">

        <c:if test="${sessionScope.role == 'admin'}">
            <form action="${pageContext.request.contextPath}/${sessionScope.role}/add-conference" method="post">
                <button class="btn btn-info" type="submit">
                    <fmt:message key="html.add.conference"/>
                </button>
            </form>
        </c:if>

        <table class="table table-bordered table-striped">
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
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${requestScope.conferences}" var="conference" varStatus="status">
                <tr>
                    <td>
                        <form action="${pageContext.request.contextPath}/${sessionScope.role}/conferences/${conference.id}/reports"
                              method="post">
                            <input type="hidden" name="conferenceId" value="${conference.id}">
                            <button class="btn btn-dark" type="submit">${conference.name}</button>
                        </form>
                    </td>
                    <td>${conference.dateTime}</td>
                    <td>${conference.location}</td>
                    <c:if test="${sessionScope.role != 'guest'}">
                    <td>
                        <c:choose>
                            <c:when test="${requestScope.isRegister[status.index]}">
                                <form action="${pageContext.request.contextPath}/${sessionScope.role}/unregister"
                                      method="post">
                                    <input type="hidden" name="conferenceId" value="${conference.id}">
                                    <button type="submit" class="btn btn-warning"
                                            <c:if test="${conference.dateTime < now}">disabled</c:if>>
                                        Unregister
                                    </button>
                                </form>
                            </c:when>
                            <c:otherwise>
                                <form action="${pageContext.request.contextPath}/${sessionScope.role}/register"
                                      method="post">
                                    <input type="hidden" name="conferenceId" value="${conference.id}">
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
                        <td>
                            <form action="${pageContext.request.contextPath}/${sessionScope.role}/update-conference"
                                  method="post">
                                <input type="hidden" name="conferenceId" value="${conference.id}">
                                <button class="btn btn-info" type="submit">
                                    <fmt:message key="html.update"/>
                                </button>
                            </form>
                            <form action="${pageContext.request.contextPath}/${sessionScope.role}/delete-conference"
                                  method="post">
                                <input type="hidden" name="conferenceId" value="${conference.id}">
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
