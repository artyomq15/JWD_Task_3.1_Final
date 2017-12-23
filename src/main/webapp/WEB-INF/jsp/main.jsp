<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="localization.auth" var="auth"/>
<fmt:setBundle basename="localization.mainpage" var="main"/>

<fmt:message key="label.name" bundle="${main}" var="name"/>
<fmt:message key="label.themes" bundle="${main}" var="themes"/>
<fmt:message key="button.signIn" bundle="${auth}" var="signIn"/>
<fmt:message key="button.signUp" bundle="${auth}" var="signUp"/>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="../../css/main.css"/>
    <title>Main</title>
</head>
<body>
<header>
    <a href="/NetworkController?command=go_to_main_page">${name}</a>

    <c:if test="${sessionScope.role == null || sessionScope.role == 1}">

        <a href="/NetworkController?command=go_to_sign_in">${signIn}</a>
        <a href="/NetworkController?command=go_to_sign_up">${signUp}</a>

    </c:if>
    <c:if test="${sessionScope.role == 2}">
        <a href="/NetworkController?command=go_to_profile">${requestScope.user.name}</a>
    </c:if>
</header>

<nav class="themes">
    <div class="themes_opening_line" data-target="#themes_block">
        ${themes}
    </div>
    <div id="themes_block">
        <c:forEach var="theme" items="${requestScope.theme_list}">
            <a href="#">
                <div class="theme_item">${theme.name}</div>
            </a>
        </c:forEach>
    </div>
</nav>

<nav class="topics">
    <c:forEach var="topic" items="${requestScope.topic_list}">
            <div>
                <h2>${topic.header}</h2>
                <a href="#">${topic.theme.name}</a>
                <p>${topic.creatingDate} by <a href="#">${topic.user.name}</a></p>
                <p>${topic.context}</p>
                <a href="#">перейти</a>
            </div>
    </c:forEach>
</nav>

<hr/>
<c:import url="footer.jsp"/>

</body>
</html>
