<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <meta charset="UTF-8">
    <link href="<c:url value='/css/bootstrap-reboot.min.css' />" rel="stylesheet" type="text/css">
    <link href="<c:url value='/css/bootstrap.min.css' />" rel="stylesheet" type="text/css">
    <link href="<c:url value='/css/main.css' />" rel="stylesheet" type="text/css">
<%--    DATE TIME PICKER--%>
    <link href="<c:url value='/css/datetimepicker.css' />" rel="stylesheet" type="text/css"/>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <script src="<c:url value='/js/jquery-3.4.1.min.js' />" type="text/javascript"></script>
    <script src="<c:url value='/js/moment-with-locales.min.js' />" type="text/javascript"></script>
    <script src="<c:url value='/js/datetimepicker.js' />" type="text/javascript"></script>
<%--    END DATE TIME PICKER --%>
    <fmt:setLocale value="${ empty sessionScope.lang ? 'en_US' : sessionScope.lang}" scope="session"/>
    <fmt:bundle basename="messages">
    <title>TITLE</title>
</head>
<body>

<jsp:include page="../fragments/header.jsp"/>

<div class="container">
    <div class="row justify-content-center">
        <div class="col col-lg-4">
            <div class="card">
                <article class="card-body">
                    <h4 class="card-title">Please, fill in:</h4>

                    <form method="post" id="add-conference"
                          action="${pageContext.request.contextPath}/${sessionScope.role}/${requestScope.conferencesLink}/add-conference">

                        <div class="form-group">
                            <label for="conferenceNameEn"><fmt:message key="html.conference.name.en"/></label>
                            <input type="text" id="conferenceNameEn" name="conferenceNameEn"
                                   class="form-control" value="${conferenceNameEn}">
                            <div class="text-danger"> ${incorrect_conferenceNameEn}</div>
                        </div>
                        <div class="form-group">
                            <label for="conferenceNameUa"><fmt:message key="html.conference.name.ua"/></label>
                            <input type="text" id="conferenceNameUa" name="conferenceNameUa"
                                   class="form-control" value="${conferenceNameUa}">
                            <div class="text-danger"> ${incorrect_conferenceNameUa}</div>
                        </div>
                        <div class="form-group">
                            <label for="datetime"><fmt:message key="html.conference.date"/></label>
                            <div id="datetime"></div>
                            <input type="hidden" id="datetime" name="datetime" class="form-control" value="" />
                            <div class="text-danger"> ${incorrect_datetime}</div>
                                <%----%>
                            <div id="conference-date-time-textarea"></div>
                            <input type="hidden" name="conference-date-time-textarea" id="datetime" disabled/>

                        </div>
                        <div class="form-group">
                            <label for="locationEn"><fmt:message key="html.conference.location.en"/></label>
                            <input type="text" id="locationEn" name="locationEn" class="form-control" value="${locationEn}">
                            <div class="text-danger"> ${incorrect_locationEn}</div>
                        </div>
                        <div class="form-group">
                            <label for="locationUa"><fmt:message key="html.conference.location.ua"/></label>
                            <input type="text" id="locationUa" name="locationUa" class="form-control" value="${locationUa}">
                            <div class="text-danger"> ${incorrect_locationUa}</div>
                        </div>

                        <div class="form-group">
                            <input type="hidden" name="conferenceId" value="${conferenceId}">
                            <input type="hidden" name="current-page" value="${paginationAttributes.currentPage}">
                            <input type="hidden" name="records-per-page" value="${paginationAttributes.recordsPerPage}">
                            <input type="hidden" name="conferencesLink" value="${requestScope.conferencesLink}">
                            <button type="submit" class="btn btn-primary btn-block">
                                <fmt:message key="html.conference.save"/>
                            </button>
                        </div>

                    </form>
                </article>
            </div>
        </div>
    </div>

</div>

</fmt:bundle>


<!-- DATETIME -->
<script type="text/javascript">
    setInterval(function () {
        document.getElementById("datetime").value = document.getElementById("conference-date-time-textarea").innerHTML;
    }, 5);
</script>
<script type="text/javascript">
    $(document).ready( function () {
        $('#datetime').dateTimePicker();
    })
</script>
<!-- END DATETIME -->
</body>
</html>
