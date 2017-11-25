<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="localization.auth" var="auth"/>
<fmt:setBundle basename="localization.mainpage" var="main"/>

<fmt:message key="label.name" bundle="${main}" var="name"/>
<fmt:message key="button.signIn" bundle="${auth}" var="signIn"/>
<fmt:message key="button.signUp" bundle="${auth}" var="signUp"/>
<html>
<head>
    <link rel="stylesheet" href="../../css/main.css"/>
    <title>Title</title>
</head>
<body>
<header>
    <a href="/NetworkController?command=go_to_main_page">${name}</a>
    <c:if test="${sessionScope.role == null}">
        <a href="/NetworkController?command=go_to_sign_in">${signIn}</a>
        <a href="/NetworkController?command=go_to_sign_up">${signUp}</a>
    </c:if>
    <c:if test="${sessionScope.role == 2}">
        <a href="/NetworkController?command=go_to_profile">${requestScope.user.name}</a>
    </c:if>
</header>
<h1>MAIN</h1>
<hr/>
<footer>
    <a href="/NetworkController?command=change_locale&locale=ru">Русский</a>
    <a href="/NetworkController?command=change_locale&locale=en">English</a>
    <a href="/NetworkController?command=change_locale&locale=be">Беларуская</a>
</footer>
</body>
</html>
