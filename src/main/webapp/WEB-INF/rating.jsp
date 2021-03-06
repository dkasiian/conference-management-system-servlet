<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <meta charset="UTF-8" >
    <link href="<c:url value='/css/bootstrap-reboot.min.css' />" rel="stylesheet" type="text/css">
    <link href="<c:url value='/css/bootstrap.min.css' />" rel="stylesheet" type="text/css">
    <link href="<c:url value='/css/main.css' />" rel="stylesheet" type="text/css">

    <!-- RATING -->
    <link href="<c:url value="/css/jquery.rating.css"/>" rel="stylesheet" type="text/css">
    <script src="<c:url value="/js/jquery.js"/>"></script>
    <script src="<c:url value="/js/jquery.rating.js"/>"></script>
    <!-- END RARING -->

    <fmt:setLocale value="${ empty sessionScope.lang ? 'en_US' : sessionScope.lang}" scope="session"/>
    <fmt:bundle basename="messages">
    <title><fmt:message key="html.title.rating"/></title>
    </head>
<body>

<jsp:include page="fragments/header.jsp"/>

<div class="container">
    <div class="row justify-content-center">
        <h2 class="text-center"><fmt:message key="html.text.rating.heading"/></h2>
    </div>
    <div class="row justify-content-center">

        <table class="table table-bordered table-striped text-center">
            <thead class="thead-dark">
            <tr>
                <th><fmt:message key="html.text.rating.speaker.name"/></th>
                <th><fmt:message key="html.text.rating.speaker.surname"/></th>
                <th><fmt:message key="html.text.rating.speaker.email"/></th>
                <th><fmt:message key="html.text.rating.speaker.reports"/></th>
                <th><fmt:message key="html.text.rating.my.rating"/></th>
                <th><fmt:message key="html.text.rating.average.rating"/></th>
                <th><fmt:message key="html.text.rating.average.bonuses"/></th>
            </tr>
            </thead>
            <tbody>

            <c:forEach items="${requestScope.speakers}" var="speaker" varStatus="status">
                <tr>
                    <td class="align-middle">${speaker.name}</td>
                    <td class="align-middle">${speaker.surname}</td>
                    <td class="align-middle">${speaker.email}</td>
                    <td class="align-middle">
                        <ul class="mb-0 pl-0" style="list-style: none;">
                            <c:forEach items="${requestScope.speakerIdToReports[speaker.id]}" var="report">
                                <li>${report.theme}</li>
                            </c:forEach>
                        </ul>
                    </td>
                    <td class="align-middle">
                        <c:if test="${requestScope.speakersIdsForRating.contains(speaker.id)}">
                            <c:if test="${speaker.id != userId}">
                            <form action="${pageContext.request.contextPath}/${sessionScope.role}/set-rating"
                                  method="post">
                                <input type="hidden" name="speakerId" value="${speaker.id}">
                                <input type="hidden" name="current-page" value="${paginationAttributes.currentPage}">
                                <input type="hidden" name="records-per-page" value="${paginationAttributes.recordsPerPage}">
                                <input type="radio" name="rating" value="1" class="star" onchange="submit()"
                                       <c:if test="${requestScope.speakerIdToUserRating[speaker.id] == 1}">checked</c:if>>
                                <input type="radio" name="rating" value="2" class="star" onchange="submit()"
                                       <c:if test="${requestScope.speakerIdToUserRating[speaker.id] == 2}">checked</c:if>>
                                <input type="radio" name="rating" value="3" class="star" onchange="submit()"
                                       <c:if test="${requestScope.speakerIdToUserRating[speaker.id] == 3}">checked</c:if>>
                                <input type="radio" name="rating" value="4" class="star" onchange="submit()"
                                       <c:if test="${requestScope.speakerIdToUserRating[speaker.id] == 4}">checked</c:if>>
                                <input type="radio" name="rating" value="5" class="star" onchange="submit()"
                                       <c:if test="${requestScope.speakerIdToUserRating[speaker.id] == 5}">checked</c:if>>
                            </form>
                            </c:if>
                        </c:if>
                    </td>
                    <td class="align-middle">
                        <form>
                            <input type="radio" name="rating" value="1" class="star" disabled
                                   <c:if test="${requestScope.speakerIdToRating[speaker.id] == 1}">checked</c:if>>
                            <input type="radio" name="rating" value="2" class="star" disabled
                                   <c:if test="${requestScope.speakerIdToRating[speaker.id] == 2}">checked</c:if>>
                            <input type="radio" name="rating" value="3" class="star" disabled
                                   <c:if test="${requestScope.speakerIdToRating[speaker.id] == 3}">checked</c:if>>
                            <input type="radio" name="rating" value="4" class="star" disabled
                                   <c:if test="${requestScope.speakerIdToRating[speaker.id] == 4}">checked</c:if>>
                            <input type="radio" name="rating" value="5" class="star" disabled
                                   <c:if test="${requestScope.speakerIdToRating[speaker.id] == 5}">checked</c:if>>
                        </form>
                    </td>
                    <td class="align-middle">${requestScope.speakerIdToBonuses[speaker.id]}</td>
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
                        <a class="page-link" href="${pageContext.request.contextPath}/${sessionScope.role}/rating?records-per-page=${paginationAttributes.recordsPerPage}&current-page=${paginationAttributes.currentPage-1}"><fmt:message key="html.text.pagination.previous" /></a>
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
                                <a class="page-link" href="${pageContext.request.contextPath}/${sessionScope.role}/rating?records-per-page=${paginationAttributes.recordsPerPage}&current-page=${i}">${i}</a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <c:if test="${paginationAttributes.currentPage lt paginationAttributes.nOfPages}">
                    <li class="page-item">
                        <a class="page-link" href="${pageContext.request.contextPath}/${sessionScope.role}/rating?records-per-page=${paginationAttributes.recordsPerPage}&current-page=${paginationAttributes.currentPage+1}"><fmt:message key="html.text.pagination.next" /></a>
                    </li>
                </c:if>
            </ul>
        </nav>

        <div>
            <form action="${pageContext.request.contextPath}/${sessionScope.role}/rating"
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
