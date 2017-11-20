<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="localization.error" var="error"/>

<fmt:message key="header.error" bundle="${error}" var="headerError"/>
<fmt:message key="label.error" bundle="${error}" var="label"/>
<html>
<head>
    <title>${headerError}</title>
</head>
<body>
<h1>${headerError}</h1>
<p>${label}</p>
<p>${requestScope.message}</p>
<hr/>
<footer>
    <a href="/NetworkController?command=change_locale&locale=ru">Русский</a>
    <a href="/NetworkController?command=change_locale&locale=en">English</a>
    <a href="/NetworkController?command=change_locale&locale=be">Беларуская</a>
</footer>
</body>
</html>
