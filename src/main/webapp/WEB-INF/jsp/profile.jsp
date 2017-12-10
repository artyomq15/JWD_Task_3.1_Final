<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>

<fmt:setBundle basename="localization.mainpage" var="main"/>
<fmt:setBundle basename="localization.profile" var="profile"/>
<fmt:setBundle basename="localization.auth" var="auth"/>


<fmt:message key="label.name" bundle="${main}" var="nameSite"/>

<fmt:message key="label.name" bundle="${profile}" var="name"/>
<fmt:message key="label.rating" bundle="${profile}" var="rating"/>
<fmt:message key="label.email" bundle="${profile}" var="email"/>
<fmt:message key="label.about" bundle="${profile}" var="about"/>

<fmt:message key="label.exit" bundle="${auth}" var="exit"/>
<html>
<head>
    <link rel="stylesheet" href="../../css/main.css"/>
    <title>Title</title>
</head>
<body>
<a href="/NetworkController?command=go_to_main_page">${nameSite}</a>

<c:if test="${requestScope.user != null}">
    <p>${name}:<c:out value="${requestScope.user.name}"/></p>
    <p>${rating}:<c:out value="${requestScope.user.rating}"/></p>
    <p>${email}:<c:out value="${requestScope.user.email}"/></p>
    <p>${about}:<c:out value="${requestScope.user.about}"/></p>
</c:if>

<a href="/NetworkController?command=exit">${exit}</a>
<hr/>
<footer>
    <a href="/NetworkController?command=change_locale&locale=ru">Русский</a>
    <a href="/NetworkController?command=change_locale&locale=en">English</a>
    <a href="/NetworkController?command=change_locale&locale=be">Беларуская</a>
</footer>
</body>
</html>
