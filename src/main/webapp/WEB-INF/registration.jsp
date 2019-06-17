<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>
    <meta charset="UTF-8" >
    <link href="<c:url value='/css/bootstrap-reboot.min.css' />" rel="stylesheet" type="text/css">
    <link href="<c:url value='/css/bootstrap.min.css' />" rel="stylesheet" type="text/css">
    <fmt:setLocale value="${ empty sessionScope.lang ? 'en_US' : sessionScope.lang}" scope="session"/>
    <fmt:bundle basename="messages">
    <title><fmt:message key="html.title.registration"/></title>
</head>

<body>

<jsp:include page="fragments/header.jsp"/>

<div class="container">
    <div class="row justify-content-center">
        <div class="col col-lg-4">
            <div class="card">
                <article class="card-body">
                    <h4 class="card-title"><fmt:message key="html.text.registration.form.head"/></h4>

                    <form method="post">
                        <div class="form-group">
                            <label for="login"><fmt:message key="html.text.registration.form.login"/></label>
                            <input id="login" name="login" class="form-control" type="text" value="${login}"/>
                            <span class="text-danger">${incorrect_login}</span>
                        </div>
                        <div class="form-group">
                            <label for="password"><fmt:message key="html.text.registration.form.password"/></label>
                            <input id="password" name="password" class="form-control" type="password"/>
                            <div class="text-danger">${incorrect_password}</div>
                        </div>
                        <div class="form-group">
                            <label for="nameEn"><fmt:message key="html.text.registration.form.name.en"/></label>
                            <input id="nameEn" name="nameEn" class="form-control" type="text" value="${nameEn}">
                            <div class="text-danger">${incorrect_nameEn}</div>
                        </div>
                        <div class="form-group">
                            <label for="nameUa"><fmt:message key="html.text.registration.form.name.ua"/></label>
                            <input id="nameUa" name="nameUa" class="form-control" type="text" value="${nameUa}">
                            <div class="text-danger">${incorrect_nameUa}</div>
                        </div>
                        <div class="form-group">
                            <label for="surnameEn"><fmt:message key="html.text.registration.form.surname.en"/> </label>
                            <input id="surnameEn" name="surnameEn" class="form-control" type="text" value="${surnameEn}">
                            <div class="text-danger">${incorrect_surnameEn}</div>
                        </div>
                        <div class="form-group">
                            <label for="surnameUa"><fmt:message key="html.text.registration.form.surname.ua"/> </label>
                            <input id="surnameUa" name="surnameUa" class="form-control" type="text" value="${surnameUa}">
                            <div class="text-danger">${incorrect_surnameUa}</div>
                        </div>
                        <div class="form-group">
                            <label for="email"><fmt:message key="html.text.registration.form.email" /></label>
                            <input id="email" name="email" class="form-control" type="email" value="${email}" }>
                            <div class="text-danger">${incorrect_email}</div>
                        </div>
                        <div class="form-group">
                            <label for="role"><fmt:message key="html.text.registration.form.role" /></label>
                            <select id="role" class="custom-select form-control" name="role" >
                                <option value="USER" selected>USER</option>
                                <option value="SPEAKER">SPEAKER</option>
                            </select>
                            <div class="text-danger">${incorrect_role}</div>
                        </div>

                        <div class="form-group">
                            <button type="submit" class="btn btn-success btn-block">
                                <fmt:message key="html.text.registration.form.sign.up"/>
                            </button>
                        </div>
                    </form>
                </article>
            </div>
        </div>
    </div>
</div>

</fmt:bundle>

</body>
</html>
