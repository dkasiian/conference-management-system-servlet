<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <meta charset="UTF-8" >
    <link href="<c:url value='/css/bootstrap-reboot.min.css' />" rel="stylesheet" type="text/css">
    <link href="<c:url value='/css/bootstrap.min.css' />" rel="stylesheet" type="text/css">

    <!-- RATING -->
    <link href="<c:url value="/css/jquery.rating.css"/>" rel="stylesheet" type="text/css">
    <script src="<c:url value="/js/jquery.js"/>"></script>
    <script src="<c:url value="/js/jquery.rating.js"/>"></script>
    <!-- END RARING -->

    <fmt:setLocale value="${ empty sessionScope.lang ? 'en_US' : sessionScope.lang}" scope="session"/>
    <fmt:bundle basename="messages">
    <title><fmt:message key="html.title.index"/></title>
    </head>
<body>

<jsp:include page="fragments/header.jsp"/>

<div class="container">
    <div class="row">
        <h3 class="text-center">Speakers and their rating:</h3>
    </div>
    <div class="row">

        <table class="table table-bordered table-striped">
            <thead class="thead-dark">
            <tr>
                <th>Name</th>
                <th>Last Name</th>
                <th>email</th>
                <th>Reports</th>
                <th>User Rating</th>
                <th>Average Rating</th>
            </tr>
            </thead>
            <tbody>

            <c:forEach items="${requestScope.allSpeakers}" var="speaker" varStatus="status">
                <tr>
                    <td>${speaker.name}</td>
                    <td>${speaker.surname}</td>
                    <td>${speaker.email}</td>
                    <td>
                        <ul>
                            <c:forEach items="${requestScope.speakerIdToReports[speaker.id]}" var="report">
                                <li>${report.theme}</li>
                            </c:forEach>
                        </ul>
                    </td>
                    <td>
                        <c:if test="${requestScope.speakersIdsForRating.contains(speaker.id)}">
                            <c:if test="${speaker.id != userId}">
                            <form action="${pageContext.request.contextPath}/${sessionScope.role}/set-rating"
                                  method="post">
                                <input type="hidden" name="speakerId" value="${speaker.id}">
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
                    <td>
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
                </tr>
            </c:forEach>

            </tbody>
        </table>
    </div>

</div>


</fmt:bundle>
</body>
</html>
