<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="localization.main" var="main"/>
<fmt:setBundle basename="localization.auth" var="auth"/>



<fmt:message key="label.name" bundle="${main}" var="name"/>


<fmt:message key="label.login" bundle="${auth}" var="login"/>
<fmt:message key="label.password" bundle="${auth}" var="password"/>
<fmt:message key="button.signIn" bundle="${auth}" var="signIn"/>
<fmt:message key="button.signUp" bundle="${auth}" var="signUp"/>
<fmt:message key="label.errorSignInMessage" bundle="${auth}" var="errMessage"/>

<html>
<head>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="../../css/index.css"/>
    <title>Like It | ${signIn}</title>
</head>
<body>
<header class="header_container">
    <div class="header_background"></div>
    <div class="header_menu">
        <a href="/NetworkController?command=go_to_main_page&page_number=1&count_topic=10">
            <div class="header_menu-item header_menu-name">
                ${name}
            </div>
        </a>
        <c:if test="${sessionScope.role == null || sessionScope.role == 1}">
            <a href="/NetworkController?command=go_to_sign_up">
                <div class="header_menu-item">
                        ${signUp}
                </div>
            </a>
            <a href="/NetworkController?command=go_to_sign_in">
                <div class="header_menu-item">
                        ${signIn}
                </div>
            </a>
        </c:if>
        <c:if test="${sessionScope.role == 2}">
            <a href="/NetworkController?command=go_to_profile">
                <div class="header_menu-item">
                        ${requestScope.user.name}
                </div>
            </a>
        </c:if>
    </div>
</header>

<main>
    <div class="card sign_up_container">
        <div class="sign_up_header">
            ${signIn}
        </div>
        <form action="/NetworkController" method="post">
            <input type="hidden" name="command" value="sign_in"/>

            <input type="text" id="login" name="login" value="" placeholder="${login}"
                   pattern="[a-zA-Z][A-Za-z_0-9]{4,}"
                   required>

            <input type="password" id="password" name="password" value="" placeholder="${password}"
                   pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\S+$).{6,}$" required>

            <input type="submit" value="${signIn}">

            <c:if test="${requestScope.message != null}">
                <br/>
                <label class="error_validation">${errMessage}</label>
            </c:if>

        </form>
    </div>
</main>

<c:import url="footer.jsp"/>

<script type="text/javascript" src="../../js/index.js"></script>
</body>
</html>
